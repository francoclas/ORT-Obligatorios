using Cliente.Models;
using Microsoft.AspNetCore.Mvc;
using RestSharp;
using System.Text.Json;
using Newtonsoft.Json;
using Cliente.Views.Filtro;

namespace Cliente.Controllers
{
    [Filtro]
    public class TipoCabController : Controller
    {
        [HttpGet]
        public IActionResult Alta() { return View(); }
        [HttpPost]
        public IActionResult Alta(TipoCab T) {
            try
            {
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab", Method.Post);
                request.AddHeader("Content-Type", "application/json");
                request.AddBody(System.Text.Json.JsonSerializer.Serialize(T));
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecutePost(request);
                return RedirectToAction("ListaTipos");
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                return View(T);
            }


        }
        [HttpGet]
        public IActionResult BuscaNombre() { return View(); }
        [HttpPost]
        public IActionResult BuscaNombre(string Nombre) {
            try
            {
                List<TipoCab> Salida = new List<TipoCab>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/BusquedaTipo?Nom="+Nombre, Method.Get);
                var body = @"";
                request.AddParameter("text/plain", body, ParameterType.RequestBody);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse Rest = client.ExecuteGet(request);
                Salida = JsonConvert.DeserializeObject<List<TipoCab>>(Rest.Content);
                return View("ListaTiposNombre",Salida);
            }
            catch (Exception)
            {

                throw;
            }
        }
        [HttpGet]
        public IActionResult ListaTiposNombre(List<TipoCab> listaTipos) { return View(listaTipos); }
        [HttpGet]
        public IActionResult ListaTipos() {
            try
            {
                List<TipoCab> Salida= new List<TipoCab>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/ListarTodos", Method.Get);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);
                Salida = JsonConvert.DeserializeObject<List<TipoCab>>(response.Content);
                
                return View(Salida);
            }
            catch (Exception)
            {

                throw;
         
            }
        }

        [HttpGet]
        public IActionResult EliminarTipo(int Id)
        {
            try
            {
                TipoCab T = new TipoCab();
                var options = new RestClientOptions("https://localhost:7202")   
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/id?ID="+Id, Method.Get);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);
                T = JsonConvert.DeserializeObject<TipoCab>(response.Content);
                return View(T);
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return RedirectToAction("ListaTipos");
            }
            
        }
        [HttpPost]
        public IActionResult EliminarTipoID(int Id) {
            try
            {
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/EliminarTipo?Id="+Id, Method.Delete);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.Execute(request);
                if (!response.IsSuccessStatusCode) {
                    throw new Exception(response.Content);
                }
                return View();
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return Redirect("ListaTipos");
            }
        
        }
        [HttpGet]
        public IActionResult ModificarTipo(int Id) {
            try
            {
                TipoCab T = new TipoCab();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/id?ID=" + Id, Method.Get);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);
                T = JsonConvert.DeserializeObject<TipoCab>(response.Content);
                return View(T);
            }
            catch (Exception)
            {

                throw;
            }
        
        }
        [HttpPost]
        public IActionResult ModificarTipo (TipoCab C)
        {
            try
            {
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/TipoCab/ModificarTipo", Method.Put);
                request.AddHeader("Content-Type", "application/json");
                request.AddBody(System.Text.Json.JsonSerializer.Serialize(C));
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecutePut(request);
                ViewBag.Error = "Se modifico la informacion";
                return View(C);

            }
            catch (Exception E) 
            {
                ViewBag.Error = E.Message;
                return View(C.Id);
            }
        }
        [HttpPost]
        public IActionResult BuscarMonto(int IdTipo, int Monto) {
            try
            {
                List<CabanaFiltro> salida = new List<CabanaFiltro>();
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Cabana/BuscarCapacidad?IdTipo=" + IdTipo + "&Monto=" + Monto, Method.Get);
                request.AddHeader("Authorization", "Bearer " + HttpContext.Session.GetString("Token"));
                RestResponse response = client.ExecuteGet(request);
                
                
                salida = JsonConvert.DeserializeObject<List<CabanaFiltro>>(response.Content);
                if (response == null || salida.Count() == 0)
                {
                    throw new Exception("Lista vacia");
                }
                return View("ListaMonto",salida);
            }
            catch (Exception e)
            {
                TempData["Error"] = e.Message;
                return Redirect("ListaTipos");
            }
        
        }
    }
}
