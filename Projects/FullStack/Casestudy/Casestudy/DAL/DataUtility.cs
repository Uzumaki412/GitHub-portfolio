using Casestudy.DAL.DomainClasses;
using System.Text.Json;

namespace Casestudy.DAL
{
    public class DataUtility
    {
        private readonly AppDbContext _db;
        public DataUtility(AppDbContext context)
        {
            _db = context;
        }
        public async Task<bool> LoadProductInfoFromWebToDb(string stringJson)
        {
            bool brandsLoaded = false;
            bool productsLoaded = false;
            try
            {
                // an element that is typed as dynamic is assumed to support any operation
                dynamic? objectJson = JsonSerializer.Deserialize<Object>(stringJson);
                brandsLoaded = await LoadBrands(objectJson);
                productsLoaded = await LoadProducts(objectJson);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

            return brandsLoaded && productsLoaded;


        }

        private async Task<bool> LoadBrands(dynamic jsonObjectArray)
        {
            bool loadedBrands = false;
            try
            {
                // clear out the old rows
                _db.Brands?.RemoveRange(_db.Brands);
                await _db.SaveChangesAsync();
                List<String> allBrands = new();
                foreach (JsonElement element in jsonObjectArray.EnumerateArray())
                {
                    if (element.TryGetProperty("Brand", out JsonElement productJson))
                    {
                        allBrands.Add(productJson.GetString()!);
                    }
                }

                IEnumerable<String> brands = allBrands.Distinct<String>();
                foreach (string catname in brands)
                {
                    Brand cat = new();
                    cat.Name = catname;
                    Microsoft.EntityFrameworkCore.ChangeTracking.EntityEntry<Brand> entityEntry = await _db.Brands!.AddAsync(cat);
                    int a = await _db.SaveChangesAsync();
                }
                loadedBrands = true;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errorh - " + ex.Message);
            }
            return loadedBrands;
        }

        private async Task<bool> LoadProducts(dynamic jsonObjectArray)
        {
            bool loadedProducts = false;
            try
            {
                List<Brand> brands = _db.Brands!.ToList();
                // clear outthe old
                _db.Products?.RemoveRange(_db.Products);
                await _db.SaveChangesAsync();
                foreach (JsonElement element in jsonObjectArray.EnumerateArray())
                {
                    Product item = new();
                    item.Id = element.GetProperty("id").GetString();
                    item.ProductName = element.GetProperty("name").GetString();
                    item.GraphicName = element.GetProperty("graphicName").GetString();
                    item.CostPrice = (element.GetProperty("costPrice").GetDecimal());
                    item.MSRP = (element.GetProperty("MSRP").GetDecimal());
                    item.QtyOnHand = (element.GetProperty("QtyOnHand").GetInt32());
                    item.QtyOnBackOrder = (element.GetProperty("QtyOnBackOrder").GetInt32());
                    item.Description = element.GetProperty("Description").GetString();
                    string? bran = element.GetProperty("Brand").GetString();
                    // add the FK here
                    foreach (Brand brand in brands)
                    {
                        if (brand.Name == bran)
                        {
                            item.Brand = brand;
                            break;
                        }
                    }

                    await _db.Products!.AddAsync(item);
                    await _db.SaveChangesAsync();
                }
                loadedProducts = true;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Errorx - " + ex.Message);
            }
            return loadedProducts;
        }




    }
}
