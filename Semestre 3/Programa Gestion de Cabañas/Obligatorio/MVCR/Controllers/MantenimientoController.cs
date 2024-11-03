using LogicaApp.CasoUso.CabanaCU;
using LogicaApp.CasoUso.MantenimientoCU;
using LogicaNegocio.Entidades;
using MVCR.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using MVCR.Filtro;
using LogicaNegocio.VO;

namespace MVCR.Controllers
{
    [VerificarSesion]
    public class MantenimientoController : Controller
    {
        private AltaMantenimiento _altaMantenimiento;
        private BusquedaMantenimiento _busquedaMantenimiento;
        private BusquedaCabana _busquedaCabana;


        public MantenimientoController(BusquedaCabana b, AltaMantenimiento A, BusquedaMantenimiento busquedaMantenimiento)
        {
            _busquedaCabana = b;
            _altaMantenimiento = A;
            _busquedaMantenimiento = busquedaMantenimiento;
        }
        public IActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public IActionResult Alta(int Id) {
            Cabana C = _busquedaCabana.BuscarCabana(Id);
            ModelMantCab M = new ModelMantCab()
            {
                IdCabana = C.TipoCabId,
                NombreCabana = C.Nombre.Valor,
                DescripcionCabana = C.Desc,
                HayJacuzzi = C.HayJacuzzi,
                HayReserva = C.HayReserva,
                CantPersMax = C.CantPersMax,
                Foto = C.Foto
            };
            return View(M);
        
        }
        [HttpPost]
        public IActionResult Alta(DateTime Fecha,string Desc,int Costo,string Tecnico,int IdCab) {
            try
            {
                Mantenimiento mantenimiento = new Mantenimiento()
                {
                    Fecha = Fecha,
                    Desc = Desc,
                    Costo = Costo,
                    Tecnico = new Tecnico(Tecnico),
                    CabanaId = IdCab
                };
                _altaMantenimiento.Nuevo(mantenimiento);
                ViewBag.Cabana = _busquedaCabana.BuscarCabana(IdCab);
                return View("MantenimientosDeCabana",_busquedaMantenimiento.ListarMantenimientosDeCabana(IdCab));
            }
            catch (Exception e)
            {
                ViewBag.Error = e.Message;
                Cabana C = _busquedaCabana.BuscarCabana(IdCab);
                ModelMantCab M = new ModelMantCab()
                {
                    IdCabana = C.TipoCabId,
                    NombreCabana = C.Nombre.Valor,
                    DescripcionCabana = C.Desc,
                    HayJacuzzi = C.HayJacuzzi,
                    HayReserva = C.HayReserva,
                    CantPersMax = C.CantPersMax,
                    Foto = C.Foto
                    };
                return View(M);
            }
         
            
        }
        [HttpGet]
        public IActionResult MantenimientosDeCabana(int IdCabana)
        {
            ViewBag.Cabana = _busquedaCabana.BuscarCabana(IdCabana);
            return View(_busquedaMantenimiento.ListarMantenimientosDeCabana(IdCabana));
        }
        [HttpPost]
        public IActionResult MantenimientosDeCabana(DateTime Fecha1,DateTime Fecha2,int IdCabana) {
            ViewBag.Cabana = _busquedaCabana.BuscarCabana(IdCabana);
            return View(_busquedaMantenimiento.ListarMantenimientosFechas(Fecha1,Fecha2,IdCabana));
        }
    }
}
