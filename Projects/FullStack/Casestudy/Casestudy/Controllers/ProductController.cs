using Casestudy.DAL;
using Casestudy.DAL.DomainClasses;
using Casestudy.DAL.DAO;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Casestudy.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductController : ControllerBase
    {

        private readonly AppDbContext _ctx;

        public ProductController(AppDbContext context)
        {
            _ctx = context;
        }

        [HttpGet]
        [Route("{brandid}")]
        public async Task<ActionResult<List<Product>>> Index(int brandid)
        {
            ProductDAO dao = new(_ctx!);
            List<Product> itemsForProduct = await dao.GetAllByBrand(brandid);
            return itemsForProduct;
        }
    }
}
