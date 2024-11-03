using Microsoft.AspNetCore.Mvc;

namespace MVC.Controllers
{
    public class CabanaController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
