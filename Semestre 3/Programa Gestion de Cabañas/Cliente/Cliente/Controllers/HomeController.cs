using Cliente.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using RestSharp;
using System.Diagnostics;
using System.Text.Json;

namespace Cliente.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;


        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            return View();
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
        [HttpPost]
        public IActionResult Login(string Email, string Password)
        {
            try
            {
                var options = new RestClientOptions("https://localhost:7202")
                {
                    MaxTimeout = -1,
                };
                var client = new RestClient(options);
                var request = new RestRequest("/api/Usuario/login?email=" + Email + "&password=" + Password, Method.Post);
                request.AddHeader("Content-Type", "application/json");
                Login Lo = new Login()
                {
                    Email = Email,
                    Password = Password
                };
                request.AddBody(System.Text.Json.JsonSerializer.Serialize(Lo));
                RestResponse response = client.ExecutePost(request);
                TokenDTO T = JsonConvert.DeserializeObject<TokenDTO>(response.Content);
                HttpContext.Session.SetString("Token", T.Token);
                HttpContext.Session.SetString("CorreoUsuario", Email);
                return View("index");
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                return View("index");
            }
        }
        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return View("index");
        }
        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }
    }
}