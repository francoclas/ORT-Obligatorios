using Microsoft.AspNetCore.Mvc;

namespace MVC.Controllers
{
    public class TCController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
        public IActionResult Alta() {
            return View();
                }
    }
}
