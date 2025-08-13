using Casestudy.DAL;
using Casestudy.DAL.DAO;
using Casestudy.DAL.DomainClasses;
using Casestudy.Helper;
using Castle.Components.DictionaryAdapter.Xml;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Razor.TagHelpers;

namespace Casestudy.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : ControllerBase
    {
        private readonly AppDbContext _ctx;
        public OrderController(AppDbContext context)
        {
            _ctx = context;
        }

        [HttpPost]
        [Produces("application/json")]
        public async Task<ActionResult<string>> Index(OrderHelper helper)
        {
            string retVal;
            try
            {
                CustomerDAO uDao = new(_ctx);
                Customer? orderOwner = await uDao.GetByEmail(helper.Email);
                OrderDAO tDao = new(_ctx);
                int orderId = await tDao.AddOrder(orderOwner!.Id, helper.Selections!);
                retVal = orderId > 0
                ? "Bag " + orderId + " saved!"
               : "Bag not saved";
            }
            catch (Exception ex)
            {
                retVal = "Bag not saved " + ex.Message;
            }
            return retVal;
        }

        [Route("{email}")]
        [HttpGet]
        [AllowAnonymous]
        public async Task<ActionResult<List<Order>>> List(string email)
        {
            List<Order> orders;
            CustomerDAO cDao = new(_ctx!);
            Customer? orderOwner = await cDao.GetByEmail(email);
            OrderDAO oDao = new(_ctx!);
            orders = await oDao.GetAll(orderOwner!.Id);
            return orders;
        }

        [Route("{orderId}/{email}")]
        [HttpGet]
        [AllowAnonymous]
        public async Task<ActionResult<List<OrderDetails>>> GetTrayDetails(int orderId, string email)
        {
            OrderDAO dao = new(_ctx!);
            return await dao.GetOrderDetails(orderId, email);
        }



    }
}
