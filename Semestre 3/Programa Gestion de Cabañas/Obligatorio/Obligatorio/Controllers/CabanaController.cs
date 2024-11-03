using Microsoft.AspNetCore.Mvc;

namespace Obligatorio.Controllers
{
    public class CabanaController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
