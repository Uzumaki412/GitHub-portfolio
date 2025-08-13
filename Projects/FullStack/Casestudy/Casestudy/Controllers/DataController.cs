using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

using Casestudy.DAL;
using System.Text.Json;
using Casestudy.DAL.DAO;

namespace Casestudy.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DataController : ControllerBase
    {
        readonly AppDbContext? _ctx;
        readonly IWebHostEnvironment? _env;
        public DataController(AppDbContext context, IWebHostEnvironment env) // injected here
        {
            _ctx = context;
            _env = env;
        }

        private static async Task<String> GetProductsJsonFromWebAsync()
        {
            string url = "https://raw.githubusercontent.com/Uzumaki412/CasestudyDB/main/casestudyDB.json";
            var httpClient = new HttpClient();
            var response = await httpClient.GetAsync(url);
            var result = await response.Content.ReadAsStringAsync();
            return result;
        }

        [HttpGet]
        public async Task<ActionResult<String>> Index()
        {
            DataUtility util = new(_ctx!);
            string payload = "";
            var json = await GetProductsJsonFromWebAsync();

            try
            {
                payload = (await util.LoadProductInfoFromWebToDb(json)) ? "tables loaded" : "problem loading tables";
            }
            catch (Exception ex)
            {
                payload = ex.Message;
            }
            return JsonSerializer.Serialize(payload);
        }

    }
}
