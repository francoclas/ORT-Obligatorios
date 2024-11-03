using Cliente.Models;
using Cliente.Views.Filtro;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using RestSharp;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Cliente.Controllers
{
    [Filtro]
    public class CabanaController : Controller
    {
        [HttpGet]
        public IActionResult Alta() { return View(); }

        [HttpGet]
        public IActionResult BuscarCabana()
        {
            try
            {
                List<TipoCab> Salida = new List<TipoCab>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/ListarTodos", Method.Get);
                RestResponse response = client.ExecuteGet(request);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                Salida = JsonConvert.DeserializeObject<List<TipoCab>>(response.Content);
                ViewBag.Lista = Salida;
                ViewBag.Error = "";
                return View("BuscarCabana");
            
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return View();
            }
        }
            
        [HttpPost]
        public IActionResult BuscarCabana(string OpcionBuscar,string idtipo,string ValorBusqueda) {
            try
            {
                List<Cabana> Salida = new List<Cabana>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Cabana/BuscarCabanas?ValorBusqueda="+ValorBusqueda+"&OpcionBuscar="+OpcionBuscar+"&idTipo="+idtipo, Method.Get);
                RestResponse response = client.ExecuteGet(request);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                Salida = JsonConvert.DeserializeObject<List<Cabana>>(response.Content);
                @ViewBag.Error = "Resultados encontrados";
                return View("ListaCabana",Salida);
            }
            catch (Exception e)
            {

                TempData["Error"] = e.Message;
                return Redirect("ListaCabana");
            }
        }

        [HttpGet]
        public IActionResult ListaCabana()
        {
            try
            {
                               return View(Devolverlista());
            }
            catch (Exception)
            {

                throw;
            }
            
        }

        //Falta terminar
        [HttpPost]
        public IActionResult Alta(Cabana cabana)
        {
            try
            {
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Cabana/Alta", Method.Post);
                request.AlwaysMultipartFormData = true;
                request.AddParameter("Nombre", cabana.Nombre);
                request.AddParameter("Desc", cabana.Desc);
                request.AddParameter("HayJacuzzi", cabana.HayJacuzzi);
                request.AddParameter("HayReserva", cabana.HayReserva);
                request.AddParameter("NumHabitacion", cabana.NumHabitacion);
                request.AddParameter("CantPersMax", cabana.CantPersMax);
                request.AddParameter("Foto", "No");
                request.AddParameter("TipoCabId", cabana.tipoCabId);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecutePost(request);

                return Redirect("ListaCAbana");
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return View(cabana);
            }
        }
        [HttpGet]
        public IActionResult AltaMant(int Id) {
            ViewBag.Id = Id;    
            return View();
        }
        [HttpPost]
        public IActionResult AltaMant(string Tecnico,string Desc,string Precio,DateTime Fecha,int IDCab)
        {
            try
            {
                Mant M = new Mant()
                {
                    Tecnico = Tecnico,
                    Desc = Desc,
                    Fecha = Fecha,
                    PrecioP = int.Parse(Precio),

                };
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Mantenimiento/Alta?IDCab="+IDCab, Method.Post);
                request.AddHeader("Content-Type", "application/json");
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                request.AddBody(System.Text.Json.JsonSerializer.Serialize(M));
                var response = client.ExecutePost(request);
                return View();
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return View(IDCab);
            }
        }

        [HttpGet]
        public IActionResult BuscarCapacidad()
        {
            return View();
        }
        [HttpGet]
        public IActionResult BuscarDos()
        {
            return View();
        }
        [HttpPost]
        
        public IActionResult BuscarDos(int n1,int n2)
        {
            try
            {
                List<TecDTO> Salida = new List<TecDTO>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Cabana/BuscarMants?n1="+n1+"&n2="+n2, Method.Get);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);

                Salida = JsonConvert.DeserializeObject<List<TecDTO>>(response.Content);
                return View("ListaTecnico",Salida);
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return View();
                            }
        }

        private List<Cabana> Devolverlista()
        {
            try
            {
                List<Cabana> Salida = new List<Cabana>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Cabana/ObtenerCabanas", Method.Get);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);
                Salida = JsonConvert.DeserializeObject<List<Cabana>>(response.Content);
                return Salida;
            }
            catch (Exception e)
            {

                throw e;
            }
        }
    }
}
