using Biblioteca;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Obligatorio_2da_Entrega.Models.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Obligatorio_2da_Entrega.Controllers
{
    public class OperadorController : Controller
    {
        private Sistema sistema;

        public OperadorController()
        {
            sistema = Sistema.GetInstancia();
        }
        [HttpGet]
        public IActionResult VistaPartidos()
        {
            return View(sistema.getListaPart());
        }
        [HttpGet]
        public IActionResult VistaSelecciones()
        {
            return View(sistema.getListaSele());
        }
        [HttpGet]
        public IActionResult VistaPeriodistas() {
            return View(sistema.ObtenerPeriodistas());
        }

        [HttpGet]

        public IActionResult VistaListaResena(int id)
        {
            ViewBag.Lista = sistema.ObtenerReseñasDPeriodista(id);
            return View();
        }
        //VistaDetallesPartidoCerrado
        [HttpGet]
        public IActionResult VistaDetallesPartidoCerrado(int idpartido)
        {
            Partido p = sistema.ObtenerPartido(idpartido);
            return View(p);
        }

        //VistaPartidosFecha

        [HttpGet]
        public IActionResult VistaPartidosFecha()
        {
            return View();
        }
        //Buscar partidos por fecha

        [HttpGet]
        public IActionResult BuscarFechas()
        {
            return View();
        }
        [HttpPost]
        public IActionResult BuscarFechas(DateTime fechaIni, DateTime fechaFin)
        {
            try
            {
                HttpContext.Session.SetString("fi",fechaIni.ToString());
                HttpContext.Session.SetString("ff", fechaFin.ToString());
                List<Partido> partidos = sistema.ObtenerPartidosFechas(fechaIni, fechaFin);
                return View("VistaPartidosFecha", partidos);
            }
            catch (Exception)
            {

                throw;
            }
        }
        //SeleccionMasGoles
        [HttpGet]
        public IActionResult VistaSeleccionGoles()
        {
            return View(sistema.BuscarSelecGoleadora());
        }


        //Busqueda por correo
        [HttpGet]
        public IActionResult VistaBuscarPorCorreo()
        {
            return View();
        }
        [HttpPost]
        public IActionResult VistaBuscarPorCorreo(string correoBuscar)
        {
            try
            {
                List <Partido> l = sistema.BuscarPartidosCorreo(correoBuscar);
                if (!l.Any())
                {
                    throw new Exception("No se encontraron resultados con ese correo.");
                }
                else
                {
                    ViewBag.Mensaje = "Se han encontrado resultados";
                    ViewBag.TipoMensaje = "Exito";
                    return View("VistaResultadoCorreo", l);
                }
            }
            catch (Exception e)
            {
                ViewBag.Mensaje = e.Message;
                ViewBag.TipoMensaje = "Error";
                return View();
            }
        }
        //FinalizarPartido
        [HttpGet]
        public IActionResult FinalizarPartido(int idPartido)
        {
            sistema.ObtenerPartido(idPartido).FinalizarPartido();
            return View(sistema.ObtenerPartido(idPartido));
        }
        //VistaResultadoCorreo
        [HttpGet]
        public IActionResult VistaResultadoCorreo(List<Partido> lista)
        {
            return View(lista);
        }
        public IActionResult VistaFinalizarPartidos()
        {
            return View(sistema.ObtenerPartidosAFinalizar());
        }
        // GET: OperadorController
        public ActionResult Index()
        {
            return View(sistema.SelParaIndex());
        }

        // GET: OperadorController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: OperadorController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: OperadorController/Create
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

        // GET: OperadorController/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: OperadorController/Edit/5
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

        // GET: OperadorController/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: OperadorController/Delete/5
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
