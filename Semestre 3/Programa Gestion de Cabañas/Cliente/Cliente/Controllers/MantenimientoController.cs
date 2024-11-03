using Cliente.Models;
using Cliente.Views.Filtro;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using RestSharp;

namespace Cliente.Controllers
{
    [Filtro]
    public class MantenimientoController : Controller
    {
        [HttpGet]
        public IActionResult BuscarMantFecha(int IDCab) {
			try
			{
                
                return View(ObtenerCabanID(IDCab));

			}
			catch (Exception)
			{

				throw;
			}
        }
        [HttpPost]
        public IActionResult BuscarMantFecha(int IDCab,DateTime F1, DateTime F2)
        {
            try
            {
                List<Mant> Salida = new List<Mant>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Mantenimiento/Listar?IDCab="+ IDCab + "&F1=" + CortarFecha(F1)+ "&F2=" + CortarFecha(F2), Method.Get);
                request.AddHeader("Authorization", "Bearer "+ HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);
                Salida = JsonConvert.DeserializeObject<List<Mant>>(response.Content);
                @ViewBag.Error = "Resultados encontrados";
                return View("ListaMantenimientos",Salida);
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return RedirectToAction("BuscarMantFecha");
                
            }
        }
        [HttpGet]
        public IActionResult ListaMantenimientos()
        {

            return View();
        }
        private Cabana ObtenerCabanID(int Id)
        {
            Cabana Salida = new Cabana();
            var options = new RestClientOptions("https://localhost:7202")
            {
                MaxTimeout = -1,
            };
            var client = new RestClient(options);
            var request = new RestRequest("/api/Cabana/BuscarID?Id=" + Id, Method.Get);
            request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
            RestResponse response = client.ExecuteGet(request);
            Salida = JsonConvert.DeserializeObject<Cabana>(response.Content);
            return Salida;
        }
        private string CortarFecha(DateTime F)
        {
            string S = F.Month.ToString();
            S += "/" + F.Day.ToString();
            S += "/" + F.Year.ToString();
            return S;
        }
    }
}
