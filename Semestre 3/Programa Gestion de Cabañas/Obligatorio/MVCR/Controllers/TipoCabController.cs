
using MVCR.Models;
using LogicaNegocio.Entidades;
using LogicaApp.CasoUso;
using LogicaApp.CasoUso.TipoCabCU;
using Microsoft.CodeAnalysis.FlowAnalysis.DataFlow;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using MVCR.Filtro;
using LogicaNegocio.VO;

namespace MVCR.Controllers
{
    [VerificarSesion]
    public class TipoCabController : Controller
    {
        private AltaTipoCab _altatipocab;
        private BusquedaTipo _busquedatipo;
        private BajaTipo _bajaTipo;
        private EditarTipo _editartipo;
        public TipoCabController(AltaTipoCab altatipocab, BusquedaTipo busquedatipo, EditarTipo editarTipo, BajaTipo bajaTipo)
        {
            _altatipocab = altatipocab;
            _busquedatipo = busquedatipo;
            _editartipo = editarTipo;
            _bajaTipo = bajaTipo;
        }
        [HttpGet]
        public IActionResult Index()
        {
            return View(_busquedatipo.ListarTodos());
        }
        [HttpGet]
        public IActionResult Alta()
        {
            return View();
        }
        [HttpPost]
        public IActionResult Alta(ModelTipoCab M)
        {
            TipoCab nuevo = new TipoCab();
            nuevo.Nombre = M.Nombre;
            nuevo.CostoP = M.PrecioP;
            nuevo.Desc = new DescTipo(M.Desc);
            try
            {
                _altatipocab.Nuevo(nuevo);
                ViewBag.Error = "Se genero nueva alta de tipo";
                return View("Index",_busquedatipo.ListarTodos());
            }
            catch (Exception e)
            {
                ViewBag.Error = e;
                return View();
            }

        }

        [HttpGet]
        public IActionResult BuscarNombre() {
            return View();
        }
        [HttpGet]
        public IActionResult Baja()
        {
            ViewBag.Lista = _busquedatipo.ListarTodos();
            return View();
        }
        [HttpPost]
        public IActionResult Baja(int IdTipo) {
            return View("Baja_Confirmada", _busquedatipo.BuscarId(IdTipo));
        
        }
        [HttpGet]
        public IActionResult Baja_Confirmada(int IdTipo) {
            return View("Baja_Confirmada", _busquedatipo.BuscarId(IdTipo));    
        }
        [HttpPost]
        public IActionResult BajaConfirmada(int IdTipo)
        {
            try
            {
                _bajaTipo.Baja(IdTipo);
                ViewBag.Error = "Se elimino correctamente el tipo con ID: " + IdTipo;
                return View("ListarTIpos", _busquedatipo.ListarTodos());
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                ViewBag.Lista = _busquedatipo.ListarTodos();
                return Baja_Confirmada(IdTipo);
            }
        }
        [HttpPost]
        public IActionResult BuscarNombre(string Nombre)
        {
            try
            {
                
                IEnumerable<TipoCab> lista = _busquedatipo.BuscarNombre(Nombre);
                return View("ListarTipos", lista);
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                return View();
            }
            
        }
        [HttpGet]
        public IActionResult listarTipos() {
            var s= _busquedatipo.ListarTodos();
            if (s.Count() == 0)
                ViewBag.Error = "No se encontraron tipos";
            return View(s);
        }

        [HttpGet]
        public IActionResult Editar(int Id) { 
            return View(_busquedatipo.BuscarId(Id));
        
        }
        [HttpPost]
        public IActionResult Editar (TipoCab T)
        {
            try
            {
                _editartipo.Editar(T);
                return View("ListarTipos",_busquedatipo.ListarTodos());  
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                return View() ;
            }
        }
    }
}
