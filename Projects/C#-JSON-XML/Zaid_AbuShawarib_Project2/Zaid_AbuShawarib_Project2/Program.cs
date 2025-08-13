using System;
using System.Xml;
using System.Xml.XPath;

namespace Zaid_AbuShawarib_Project2
{
    public class Program
    {
        static XmlDocument? xmlDoc;
        static XPathNavigator? nav;
        const string XmlFile = "global_economies.xml";
        const string saveFile = "Save.xml";
        static XPathNodeIterator? regionNodes;

        const int minYear = 1970;
        const int maxYear = 2021;
        static int startYear = 2017;
        static int endYear = 2021;
        const int consecutiveYears = 5;

        static void Main(string[] args)
        {
            try
            {
                loadSave();
                loadDataFile();
                while (true)
                {
                    Console.WriteLine("World Economic Data");
                    Console.WriteLine("===================");
                    Console.WriteLine();
                    Console.WriteLine($"'Y' to adjust the range of years (currently {startYear} to {endYear})");
                    Console.WriteLine("'R' to print a regional summary");
                    Console.WriteLine("'M' to print a specific metric for all regions");
                    Console.WriteLine("'X' to exit the program");
                    Console.Write("\nYour selection: ");
                    string input = Console.ReadLine()!.Trim().ToUpper();
                    switch (input)
                    {
                        case "Y":
                            adjustYearRange();
                            break;
                        case "R":
                            selectRegion();
                            break;
                        case "M":
                            MetricReportForAll();
                            break;
                        case "X":
                            Console.WriteLine("All done!");
                            return;
                        default:
                            Console.WriteLine("Invalid selection. Please try again.\n");
                            break;
                    }
                }
            }
            catch (IOException ex)
            {
                Console.WriteLine("An error occurred while accessing the file: " + ex.Message);
            }
            catch (XmlException ex)
            {
                Console.WriteLine("An error occurred while processing the XML: " + ex.Message);
            }
            catch (XPathException ex)
            {
                Console.WriteLine("XPath Error: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("An unexpected error occurred: " + ex.Message);
            }
        }
        static void loadSave()
        {
            try
            {
                if(File.Exists(saveFile))
                {
                    XmlDocument saveDoc = new XmlDocument();
                    saveDoc.Load(saveFile);
                    XPathNavigator saveNav = saveDoc.CreateNavigator()!;

                    XPathNodeIterator startYearNode = saveNav!.Select("//startYear");
                    XPathNodeIterator endYearNode = saveNav.Select("//endYear");


                    if (startYearNode.MoveNext())
                        int.TryParse(startYearNode.Current!.Value, out startYear);

                    if (endYearNode.MoveNext())
                        int.TryParse(endYearNode.Current!.Value, out endYear);
                }
                else
                {
                    saveSaveFile();
                }
            }
            catch(Exception ex)
            {
                Console.WriteLine("Error" + ex.Message);
                startYear = 2017;
                endYear = 2021;
            }
        }

        static void saveSaveFile()
        {
            try
            {
                XmlDocument saveDoc = new XmlDocument();

                XmlElement root = saveDoc.CreateElement("reportRange");
                saveDoc.AppendChild(root);

                XmlElement startYearElement = saveDoc.CreateElement("startYear");
                startYearElement.InnerText = startYear.ToString();
                root.AppendChild(startYearElement);

                XmlElement endYearElement = saveDoc.CreateElement("endYear");
                endYearElement.InnerText = endYear.ToString();
                root.AppendChild(endYearElement);

                saveDoc.Save(saveFile);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Warning: Could not save configuration: {ex.Message}");
            }
        }

        static void loadDataFile()
        {
            try
            {
                xmlDoc = new XmlDocument();
                xmlDoc.Load(XmlFile);
                nav = xmlDoc.CreateNavigator()!;
            }
            catch (IOException ex)
            {
                Console.WriteLine("Error loading data file: " + ex.Message);

            }
            catch (XmlException ex)
            {
                Console.WriteLine("XML error: " + ex.Message);
            }

        }

        static List<string> GetAllRegions()
        {
            List<string> regions = new List<string>();
            regionNodes = nav!.Select("//region/@rname");
            while (regionNodes.MoveNext())
            {
                regions.Add(regionNodes.Current!.Value);
            }
            return regions;
        }

        private static List<string> GetAllMetrics()
        {
            List<string> metrics = new List<string>();

            XPathNodeIterator inflationNodes = nav!.Select("//year/inflation/@*");
            XPathNodeIterator interestNodes = nav.Select("//year/interest_rates/@*");
            XPathNodeIterator unemploymentNodes = nav.Select("//year/unemployment_rates/@*");

            
            while (inflationNodes.MoveNext())
            {
                string metricName = $"inflation.{inflationNodes.Current!.LocalName}";
                if (!metrics.Contains(metricName))
                {
                    metrics.Add(metricName);
                }
            }

            while (interestNodes.MoveNext())
            {
                string metricName = $"interest_rates.{interestNodes.Current!.LocalName}";
                if (!metrics.Contains(metricName))
                {
                    metrics.Add(metricName);
                }
            }

            while (unemploymentNodes.MoveNext())
            {
                string metricName = $"unemployment_rates.{unemploymentNodes.Current!.LocalName}";
                if (!metrics.Contains(metricName))
                {
                    metrics.Add(metricName);
                }
            }
            return metrics;
        }
        static void adjustYearRange()
        {
            int newStartYear = startYear;
            int newEndYear = endYear;

            while (true)
            {
                Console.Write($"\nStarting year ({startYear} to {endYear}): ");
                if (!int.TryParse(Console.ReadLine(), out newStartYear)
                    || newStartYear < minYear || newStartYear > maxYear)
                {
                    Console.WriteLine($"Error: Please enter a year between {minYear} and {maxYear}.");
                    continue;
                }
                break;
            }

            while (true)
            {
                int maxEndYear = Math.Min(maxYear, newStartYear + consecutiveYears - 1);
                Console.Write($"Ending year ({minYear} to {maxYear}): ");
                if (!int.TryParse(Console.ReadLine(), out newEndYear)
                    || newEndYear < newStartYear || newEndYear > maxYear ||
                    newEndYear > maxEndYear)
                {
                    Console.WriteLine($"\nError: Please enter a year between {newStartYear} and {maxEndYear}.");
                    continue;
                }
                break;
            }
            startYear = newStartYear;
            endYear = newEndYear;
            saveSaveFile();
            Console.WriteLine();
        }
        static void printRegionalList(List<string> regions)
        {
            Console.WriteLine("Select a region by number as shown below...");
            Console.WriteLine();

            for (int i = 0; i < regions.Count; i++)
            {
                Console.WriteLine($"{i + 1,3}. {regions[i]}");
            }
            Console.WriteLine();
        }
        static void selectRegion()
        {
            Console.WriteLine();

            List<string> regions = GetAllRegions();
            printRegionalList(regions);

            int regionIndex = -1;
            while (regionIndex < 0 || regionIndex >= regions.Count)
            {
                Console.Write("Enter a region #: ");
                if (!int.TryParse(Console.ReadLine(), out regionIndex) || regionIndex < 1 || regionIndex > regions.Count)
                {
                    Console.WriteLine("Invalid selection. Please try again.");
                    regionIndex = -1;
                }
                else
                {
                    regionIndex--;
                }
            }

            string selectedRegion = regions[regionIndex];

            MetricReportForRegion(selectedRegion);
        }
        static void MetricReportForRegion(string regionName)
        {
            Console.WriteLine();
            Console.WriteLine($"Economic Information for {regionName}");
            Console.WriteLine("------------------------------------------");
            Console.WriteLine();

            // Print header
            Console.Write($"{"Economic Metric",25}");
            for (int year = startYear; year <= endYear; year++)
            {
                Console.Write($"{year,9}");
            }
            Console.WriteLine();
            Console.WriteLine();

            List<string> metrics = GetAllMetrics();

            foreach (string metric in metrics)
            {

                string displayName = metric;

                if (metric == "inflation.consumer_prices_percent")
                    displayName = "Inflation CPI";
                else if (metric == "inflation.gdp_deflator_percent")
                    displayName = "Inflation GDP";
                else if (metric == "interest_rates.real")
                    displayName = "Real Interest %";
                else if (metric == "interest_rates.lending")
                    displayName = "Lending Interest %";
                else if (metric == "interest_rates.deposit")
                    displayName = "Deposit Interest %";
                else if (metric == "unemployment_rates.modeled_ILO_estimate")
                    displayName = "Unemployment NTL %";
                else if (metric == "unemployment_rates.national_estimate")
                    displayName = "Unemployment IPO %";

                Console.Write($"{displayName,25}");

                for (int year = startYear; year <= endYear; year++)
                {
                    string value = GetMatricData(regionName, metric, year);
                    Console.Write($"{value,9}");
                }
                Console.WriteLine();  
            }
            Console.WriteLine();
        }
        static void MetricReportForAll()
        {
            Console.WriteLine();
            Console.WriteLine("Select a metric by number as shown below...");
            Console.WriteLine();
            Console.WriteLine("1. Inflation CPI");
            Console.WriteLine("2. Inflation GDP");
            Console.WriteLine("3. Real Interest %");
            Console.WriteLine("4. Lending Interest %");
            Console.WriteLine("5. Deposit Interest %");
            Console.WriteLine("6. Unemployment NTL %");
            Console.WriteLine("7. Unemployment IPO %");
            Console.WriteLine();
            Console.Write("Enter a metric #: ");
            string input = Console.ReadLine()!.Trim();
            switch(input)
            {
                case "1":
                    MetricReportForAllRegions("inflation.consumer_prices_percent");
                    break;
                case "2":
                    MetricReportForAllRegions("inflation.gdp_deflator_percent");
                    break;
                case "3":
                    MetricReportForAllRegions("interest_rates.real");
                    break;
                case "4":
                    MetricReportForAllRegions("interest_rates.lending");
                    break;
                case "5":
                    MetricReportForAllRegions("interest_rates.deposit");
                    break;
                case "6":
                    MetricReportForAllRegions("unemployment_rates.modeled_ILO_estimate");
                    break;
                case "7":
                    MetricReportForAllRegions("unemployment_rates.national_estimate");
                    break;
                default:
                    Console.WriteLine("Invalid selection. Please try again.");
                    return;
            }

        }
        static void MetricReportForAllRegions(string metric)
        {
            Console.WriteLine();
            Console.WriteLine($"{metric} By Region");
            Console.WriteLine("--------------------------------------------------");
            Console.WriteLine();
            Console.Write($"{"Region",25}");
            for (int year = startYear; year <= endYear; year++)
            {
                Console.Write($"{year,9}");
            }
            Console.WriteLine();
            Console.WriteLine();
            List<string> regions = GetAllRegions();
            foreach (string region in regions)
            {
                string truncatedcountry = region.Length > 20 ? region.Substring(0, 20) + "..." : region;


                Console.Write($"{truncatedcountry,25}");
                for (int year = startYear; year <= endYear; year++)
                {
                    string value = GetMatricData(region, metric, year);
                    Console.Write($"{value,9}");
                }
                Console.WriteLine();  
            }
            Console.WriteLine();
        }
        static string GetMatricData(string regionName, string metric, int year)
        {
            try
            {
                // Check for empty or null metric name
                if (string.IsNullOrEmpty(metric) || string.IsNullOrWhiteSpace(metric))
                    return "-";

                // Parse the metric name to get category and attribute
                string[] parts = metric.Split('.');
                if (parts.Length != 2)
                    return "-";

                string category = parts[0];  
                string attribute = parts[1]; 

                
                XPathNavigator? regionNode = nav!.SelectSingleNode($"//region[@rname='{regionName}']");
                if (regionNode == null)
                    return "-";

               
                XPathNavigator? yearNode = regionNode!.SelectSingleNode($"year[@yid='{year}']");
                if (yearNode == null)
                    return "-";

               
                XPathNavigator? categoryNode = yearNode!.SelectSingleNode(category);
                if (categoryNode != null)
                {
                    string attributeValue = categoryNode.GetAttribute(attribute, "");
                    if (!string.IsNullOrEmpty(attributeValue))
                    {
                        if (double.TryParse(attributeValue, out double value))
                        {
                            return value.ToString("F2");
                        }
                    }
                }
                return "-";
            }
            catch
            {
                return "-";
            }
        }
    }
}

