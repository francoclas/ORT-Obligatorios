using Microsoft.AspNetCore.Mvc;
using MVCR.Models;
using System.Diagnostics;
using LogicaApp.CasoUso;
using LogicaNegocio.Entidades;
using LogicaApp.CasoUso.UsuarioCU;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using MVCR.Filtro;

namespace MVCR.Controllers
{
    public class HomeController : Controller {
        private Registro _registro;
        private InicioSesion _inicioSesion;
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger,Registro registro, InicioSesion inicioSesion)
        {
            _logger = logger;
            _registro = registro;
            _inicioSesion = inicioSesion;
        }
        public IActionResult Index()
        {
            return View();
        }
        [HttpGet]
        public IActionResult Login()
        {
           

            return View();
        }
        public IActionResult Logout()
        {
                HttpContext.Session.Clear();
                return View("Login");
        }
        [HttpPost]
        public IActionResult Login(string Correo,string contrasena) {
            try
            {
                if (string.IsNullOrEmpty(Correo))
                    throw new Exception("El correo no puede ser vacio");
                if (string.IsNullOrEmpty(contrasena))
                    throw new Exception("La contrasena no puede ser vacia");
                
                _inicioSesion.IniciarSesion(Correo, contrasena);

                //Despues de validar credenciales
                HttpContext.Session.SetString("CorreoUsuario",Correo);
                return View("Index");
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                return View();
            }
        }

        [HttpGet]
        public IActionResult Registro() {
            return View();
        }
        [HttpPost]
        public IActionResult Registro(string Nombre,string Correo,string User,string Contrasena,string ContrasenaVerificada)
        {
            try
            {
                if (string.IsNullOrEmpty(Nombre))
                    throw new Exception("El nombre no puede ser vacio");
                if (string.IsNullOrEmpty(Correo))
                    throw new Exception("El correo no puede ser vacio");
                if (string.IsNullOrEmpty(User))
                    throw new Exception("El usuario no puede ser vacio");
                if (string.IsNullOrEmpty(Contrasena))
                    throw new Exception("La contrasena no puede ser vacia");
                if (string.IsNullOrEmpty(ContrasenaVerificada))
                    throw new Exception("La verificacion de contrasena no puede ser vacia");
                if (Contrasena != ContrasenaVerificada)
                    throw new Exception("Las contrasenas ingresadas no coinciden");
                Usuario usuario = new Usuario()
                {
                    Nombre = Nombre,
                    Correo = Correo,
                    User = new LogicaNegocio.VO.User(User),
                    Pass = Contrasena
                };
                _registro.Nuevo(usuario);
                ViewBag.Error = "Su usuario se registro exitosamente";
                return View("Login");
            }
            
            catch (Exception e)
            {
                ViewBag.Error = e.Message; 
                return View();
                
            }
        }
        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}