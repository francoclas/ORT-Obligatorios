
using LogicaApp.CasoUso.TipoCabCU;
using LogicaNegocio.Entidades;
using Microsoft.AspNetCore.Mvc;
using Obligatorio.Properties.Models;
namespace Obligatorio.Controllers
{
    public class TipoCabController : Controller
    {
        //Instanciar constructores de casos de uso
        private AltaTipoCab _altatipocab;
        public IActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public IActionResult AltaTipoCabView() {
            return View();
        }

        [HttpPost]
        public IActionResult AltaTipoCabView(ViewModelTC Tc) {
            try
            {
                var ntc = new TipoCab();
                ntc.Nombre = Tc.Nombre;
                ntc.Desc = Tc.Descr;
                ntc.CostoP = Tc.CostoPer;
                ntc.Validar();
                _altatipocab.Nuevo(ntc);
                return RedirectToAction("Index");
            }
            catch (Exception e)
            {

                ViewBag.Error = e;
            }
            return View(Tc);
        
        }
    }
}
