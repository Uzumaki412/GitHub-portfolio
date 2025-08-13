using CaseStudyAPI.DAL;
using Casestudy.DAL.DAO;
using CaseStudyAPI.DAL.DomainClasses;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Casestudy.DAL;
namespace ExercisesAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
    public class BranchController : ControllerBase
    {
        readonly AppDbContext _db;
        public BranchController(AppDbContext context)
        {
            _db = context;
        }
        [HttpGet("{lat}/{lon}")]
        [AllowAnonymous]
        public async Task<ActionResult<List<Branch>?>> Index(float lat, float lon)
        {
            BranchDAO dao = new(_db);
            return await dao.GetThreeClosestStores(lat, lon);
        }
    }
}
