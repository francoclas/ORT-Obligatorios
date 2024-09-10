using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Obligatorio_2da_Entrega.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Biblioteca;
using Biblioteca.Clases;
using Microsoft.AspNetCore.Http;

namespace Obligatorio_2da_Entrega.Controllers
{
    public class HomeController : Controller
    {
        private Sistema sistema = Sistema.GetInstancia();

        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public IActionResult Index()
        {
            return View(sistema.SelParaIndex());
        }

        public IActionResult Privacy()
        {
            return View();
        }
        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }
        
        //Inicio de sesion
        [HttpPost]
        public IActionResult Login(string txtUser,string txtPassword)
        {
            try
            {
                if (string.IsNullOrEmpty(txtUser) || string.IsNullOrEmpty(txtPassword))
                    throw new Exception("Revisar credenciales utilizadas.");
                else {
                    Usuario P = sistema.ObtenerUsuario(txtUser, txtPassword);
                    if(P != null) {
                        HttpContext.Session.SetString("USER", P.usuario);
                        HttpContext.Session.SetString("ROL", P.rol);
                        HttpContext.Session.SetString("ID", P.id.ToString());

                        if (P.rol == "Periodista")
                            return RedirectToAction("Index", "Periodista");
                        else
                            return View("Index",sistema.SelParaIndex());
                    }
                    else
                    {
                        throw new Exception("Revisar credenciales");
                    }
                }
            }
            catch (Exception e)
            {

                ViewBag.Mensaje = e.Message;
                ViewBag.TipoMensaje = "ERROR";
                return View();
            }
        }
        //Cierre de sesion
        public IActionResult Logout()
        {
            try
            {
                HttpContext.Session.Clear();
                return View("Login");
            }
            catch (Exception e)
            {
                throw e;
            }
        }
        [HttpGet]
        public IActionResult Registro()
        {
            return View();
        }
        [HttpPost]
        public IActionResult Registro(string nom, string ape, string  correo, string user, string pass)
        {
            try
            {
                sistema.AltaPeriodista(new Periodista(nom, ape, correo, pass));
                int idult = sistema.ObtenerUltimoid();
                sistema.AltaUsuario(new Usuario(idult, user, pass, "Periodista"));
                ViewBag.TipoMensaje = "Exito";
                ViewBag.Mensaje = "Se registro su usuario con exito, inicie sesion :)";
                return View("Index",sistema.SelParaIndex());

            }
            catch (Exception e)
            {
                //terminar
                ViewBag.Mensaje = e.Message;
                ViewBag.TipoMensaje = "Error";
                return View();
            }
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
