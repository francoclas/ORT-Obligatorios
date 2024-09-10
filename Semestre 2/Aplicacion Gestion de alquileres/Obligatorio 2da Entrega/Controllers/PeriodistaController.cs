using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Biblioteca;

namespace Obligatorio_2da_Entrega.Controllers
{
    public class PeriodistaController : Controller
    {
        private Sistema sistema;

        public PeriodistaController()
        {
            sistema = Sistema.GetInstancia();
        }
        //Vista resenas hechas por el usuario

        [HttpGet]
        public IActionResult VistaReseñasHechas() {
            int idPer = int.Parse(HttpContext.Session.GetString("ID"));
            ViewBag.TieneRes = sistema.ObtenerReseñasPeriodistaSesion(idPer).Any();  
            return View(sistema.ObtenerReseñasPeriodistaSesion(idPer));
        }
        //Seccion para hacer reseñas.
        [HttpGet]
        public ActionResult VistaHacerResena(int idpartido)
        {
            ViewBag.Partido = sistema.ObtenerPartido(idpartido).SalResena();
            ViewBag.id = idpartido;
            return View();
        }
        [HttpPost]
        public IActionResult VistaHacerResena(int idPartido,string TituloRes,string DescRes) {
            int idp = int.Parse(HttpContext.Session.GetString("ID"));
            try
            {
                sistema.AltaReseña(new Reseña(sistema.ObtenerPeriodista(idp),sistema.ObtenerPartido(idPartido),DateTime.Now,TituloRes,DescRes));
                ViewBag.Mensaje = "Se cargo su nueva reseña";
                ViewBag.TipoMensaje = "Exito";
                string s = sistema.ObtenerPartido(idPartido).SalResena();
                ViewBag.salida = s;
                ViewBag.loc = sistema.ObtenerPartido(idPartido).SeleccionLocal.Pais.Nombre;
                ViewBag.vis = sistema.ObtenerPartido(idPartido).SeleccionVisitante.Pais.Nombre;
            
                return View("VistaResenaHecha", sistema.ObtenerUltReseña(idp));
            }
            catch (Exception e)
            {
                ViewBag.Mensaje = e.Message;
                ViewBag.TipoMensaje = "Error";
                return View("Index", sistema.ObtenerPartidosPerFin());
            }
        }

        // GET: PeriodistaController
        public ActionResult Index()
        {
            return View(sistema.ObtenerPartidosPerFin());
        }

        // GET: PeriodistaController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: PeriodistaController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: PeriodistaController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: PeriodistaController/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: PeriodistaController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: PeriodistaController/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: PeriodistaController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}
