using LogicaApp.CasoUso.CabanaCU;
using LogicaApp.CasoUso.TipoCabCU;
using LogicaNegocio.Entidades;
using MVCR.Models;
using MVCR.Filtro;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using LogicaNegocio.VO;

namespace MVCR.Controllers
{
    [VerificarSesion]
    public class CabanaController : Controller
    {
        private AltaCabana _altacabana;
        private BusquedaCabana _busquedaCabana;
        private BusquedaTipo _busquedatipo;
        private IWebHostEnvironment _webHostEnvironment;

        private List<ModelCabCnTipo> AListaModelo(IEnumerable<Cabana> lista)
        {
            List<ModelCabCnTipo> salida = new List<ModelCabCnTipo>();
            foreach (var item in lista)
            {
                var tipo = _busquedatipo.BuscarId(item.TipoCabId);
                ModelCabCnTipo Mod = new ModelCabCnTipo()
                {
                    Id = item.Id,
                    Nombre = item.Nombre.Valor,
                    Desc = item.Desc,
                    HayJacuzzi = item.HayJacuzzi,
                    HayReserva = item.HayReserva,
                    NumHabitacion = item.NumHabitacion,
                    CantPersMax = item.CantPersMax,
                    Foto = item.Foto,
                    IdTipo = tipo.Id,
                    NombreTipo = tipo.Nombre,
                    CostoP = tipo.CostoP
                };
                salida.Add(Mod);
            }
            return salida.ToList();
        }
        public CabanaController(BusquedaTipo busquedatipo,AltaCabana altacabana, IWebHostEnvironment webHostEnvironment, BusquedaCabana busquedaCabana)
        {
            _busquedatipo = busquedatipo;
            _altacabana = altacabana;
            _webHostEnvironment = webHostEnvironment;
            _busquedaCabana = busquedaCabana;
        }
        
        public IActionResult Index()
        {
            return View(AListaModelo(_busquedaCabana.ListarTodas()));
        }

        [HttpGet]
        public IActionResult Alta()
        {
            ViewBag.Tipos = _busquedatipo.ListarTodos();
            return View();
        }
        [HttpPost]
        public IActionResult Alta(ModelCabana Mod,int IdTipo) {

            try
            {
                var Tipo = _busquedatipo.BuscarId(IdTipo);
                if (Tipo == null) {
                    throw new Exception("No existe el tipo de cabaña");
                }
                string NomFoto = Mod.Nombre + "_001" + Mod.Img.FileName.Substring(Mod.Img.FileName.Length - 4);
                string rutaImagenes = Path.Combine(_webHostEnvironment.WebRootPath + "/imgs", NomFoto);
                Cabana cabana = new Cabana()
                {
                    Nombre = new Nombre(Mod.Nombre),
                    Desc = Mod.Desc,
                    HayJacuzzi = Mod.HayJacuzzi,
                    HayReserva = Mod.HayReserva,
                    NumHabitacion = Mod.NumHabitacion,
                    CantPersMax = Mod.CantPersMax,
                    Foto = NomFoto,
                    TipoCabId = IdTipo,
                    TipoCab = Tipo,
                };
                _altacabana.Nuevo(cabana);
                FileStream Foto = new FileStream(rutaImagenes, FileMode.Create);
                Mod.Img.CopyTo(Foto);
                ViewBag.Error = "Se genero nueva cabaña con exito";
                return RedirectToAction("Index",_busquedaCabana.ListarTodas());
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                return View(Mod);
            }



           
        }

        [HttpGet]
        public IActionResult BuscarCabanas()
        {
            ViewBag.Lista = _busquedatipo.ListarTodos();
            return View();
        }
        [HttpPost]
        public IActionResult BuscarCabanas(string OpcionBuscar,string ValorBusqueda,string idtipo)
        {
            try
            {
                switch (OpcionBuscar)
                {
                    case "1":
                        //Nombre
                        if (string.IsNullOrEmpty(ValorBusqueda))
                            throw new Exception("El valor a buscar no puede estar vacio");
                        return View("index", AListaModelo(_busquedaCabana.BuscarNombre(ValorBusqueda)));

                    case "2":
                        //Tipo
                        int id;
                        if (!(int.TryParse(idtipo, out id)))
                            throw new Exception("El valor ingresado para la capacidad no es un numero.");
                        return View("index", AListaModelo(_busquedaCabana.BuscarTipo(id)));

                    case "3":
                        //Habilitadas
                        return View("index",AListaModelo(_busquedaCabana.BuscarHabilitadas()));

                    case "4":
                        //Capacidad
                        int cap;
                        if (!(int.TryParse(ValorBusqueda, out cap)))
                            throw new Exception("El valor ingresado para la capacidad no es un numero.");
                        return View("Index",AListaModelo(_busquedaCabana.BuscarCantMax(cap)));

                    default:
                        throw new Exception("No se recibio el correcto valor en el menu, seleccione e intente nuevamente.");
                }
            }
            catch (Exception e)
            {
                ViewBag.Lista = _busquedatipo.ListarTodos();
                ViewBag.Error = e.Message;
                return View();
            }
            
        }
    }

}
