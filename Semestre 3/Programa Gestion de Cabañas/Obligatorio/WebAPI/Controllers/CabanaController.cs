using LogicaApp.CasoUso.CabanaCU;
using LogicaApp.CasoUso.TipoCabCU;
using Microsoft.AspNetCore.Mvc;
using Azure;
using Swashbuckle.AspNetCore;
using Swashbuckle.AspNetCore.Annotations;
using Swashbuckle.Swagger.Annotations;
using Microsoft.AspNetCore.Mvc.Filters;
using System.ComponentModel;
using WebAPI.DTOS;
using LogicaNegocio.Entidades;
using System.Collections.Generic;
using LogicaAccesoDatos.Excepciones;
using LogicaNegocio.Excepciones;
using LogicaNegocio.VO;
using System.IO;
using LogicaApp.CasoUso.MantenimientoCU;
using Microsoft.AspNetCore.Authorization;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
   
    public class CabanaController : Controller
    {
        private AltaCabana _altacabana;
        private BusquedaCabana _busquedaCabana;
        private BusquedaTipo _busquedatipo;
        private BusquedaMantenimiento _busquedaMant;
        private IWebHostEnvironment _webHostEnvironment;

        public CabanaController(BusquedaTipo busquedatipo, AltaCabana altacabana, IWebHostEnvironment webHostEnvironment, BusquedaCabana busquedaCabana,BusquedaMantenimiento B)
        {
            _busquedatipo = busquedatipo;
            _altacabana = altacabana;
            _webHostEnvironment = webHostEnvironment;
            _busquedaCabana = busquedaCabana;
            _busquedaMant = B;
        }
        [HttpPost("Alta")]
        [ProducesResponseType(StatusCodes.Status201Created)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult Alta([FromForm] CabanaSalidaDTO C)
        {
            try
            {
                Cabana CN = new Cabana()
                {
                    Nombre = new Nombre(C.Nombre),
                    Desc = C.Desc,
                    HayJacuzzi = C.HayJacuzzi,
                    HayReserva = C.HayReserva,
                    NumHabitacion = C.NumHabitacion,
                    CantPersMax = C.CantPersMax,
                    Foto = C.Foto,
                    TipoCabId = C.TipoCabId,
                };
                _altacabana.Nuevo(CN);
                return Ok();
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);

            }
            catch (ADException A)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
            catch (Exception E)
            {

                return BadRequest(E.Message);
            }
        }
        [HttpGet ("ObtenerCabanas")]
        public IActionResult ObtenerCabanas()
        {
           
            return Ok(TransformarLista(_busquedaCabana.ListarTodas()));
        }
        [HttpGet("BuscarCabanas")]
        /// <Description>
        /// Valor Busqueda refiere a lo que se va a buscar
        /// OpcionBuscar a que se desea 1=Nombre,2=Tipo,3=Habilitadas,4=Capacidad
        /// IdTipo unicamente cuando se utiliza el OpcionBuscar = 2
        /// </Description>
        public IActionResult BuscarCabanas(string ValorBusqueda, string OpcionBuscar, string idTipo)
        {
            List<CabanaDTO> Salida = new List<CabanaDTO>();
            try
            {

                switch (OpcionBuscar)
                {
                    case "1":
                        //Nombre
                        if (string.IsNullOrEmpty(ValorBusqueda))
                            throw new Exception("El valor a buscar no puede estar vacio");
                        Salida = TransformarLista(_busquedaCabana.BuscarNombre(ValorBusqueda));
                        break;
                    case "2":
                        //Tipo
                        int id;
                        if (!(int.TryParse(idTipo, out id)))
                            throw new Exception("El valor ingresado para la capacidad no es un numero.");
                        Salida = TransformarLista(_busquedaCabana.BuscarTipo(id));
                        break;
                    case "3":
                        //Habilitadas
                        Salida = TransformarLista(_busquedaCabana.BuscarHabilitadas());
                        return Ok(Salida);

                    case "4":
                        //Capacidad
                        int cap;
                        if (!(int.TryParse(ValorBusqueda, out cap)))
                            throw new Exception("El valor ingresado para la capacidad no es un numero.");
                        Salida = TransformarLista(_busquedaCabana.BuscarCantMax(cap));
                        break; 

                    default:
                        throw new Exception("No se recibio el correcto valor en el menu, seleccione e intente nuevamente.");
                        break;
                }

                return Ok(Salida);
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);

            }
            catch (ADException A)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
            catch (Exception E)
            {

                return BadRequest(E.Message);
            }
        }
        List<CabanaDTO> TransformarLista(IEnumerable<Cabana> C)
        {
            List<CabanaDTO> Salida = new List<CabanaDTO>();
            foreach (var item in C)
            {
                CabanaDTO CD = new CabanaDTO()
                {
                    Id = item.Id,
                    TipoCabId = item.TipoCabId,
                    Nombre = item.Nombre.Valor,
                    Desc = item.Desc,
                    HayJacuzzi = item.HayJacuzzi,
                    HayReserva = item.HayReserva,
                    CantPersMax = item.CantPersMax,
                    Foto = item.Foto,
                    

                };
                Salida.Add(CD);
            }
            return Salida;
        }
        [HttpGet("BuscarMants")]
        /// <Description>
        /// Filtra segun el margen ingresado los tecnicos y su monto total
        /// </Description>
        public IActionResult ObtenerEn2(int n1, int n2)
        {
            try
            {
                List<Cabana> cabanas = _busquedaCabana.ListarTodas().ToList();
                List<Mantenimiento> mantenimientos = _busquedaMant.ListarTodo().ToList();

                var resultado = from c in cabanas
                                where c.CantPersMax >= n1 && c.CantPersMax <= n2
                                join m in mantenimientos on c.Id equals m.CabanaId
                                group m by m.Tecnico.Valor into g
                                select new TecnicoMontoDTO
                                {
                                    Tecnico = g.Key,
                                    MontoTotal = g.Sum(m => m.Costo)
                                };
                return Ok(resultado);
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);

            }
            catch (ADException A)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
            catch (Exception E)
            {

                return BadRequest(E.Message);
            }
        }
        [HttpGet("BuscarCapacidad")]
        /// <Description>
        /// Filtra segun el id ingresado y utiliza el monto para mostrar lo demas. 
        /// IMPORTANTE
        /// Profe en esta consulta desde la letra uno la clase CAbana no tiene un atributo costo.
        /// </Description>
        /// 

        public IActionResult BuscarCapacidad(int IdTipo,int Monto)
        {
            try
            {
                List<TipoCab> tipoCabanias = _busquedatipo.ListarTodos().ToList();
                var resultado = from t in tipoCabanias
                                where t.Id == IdTipo
                                from c in t.cabanas
                                where t.CostoP < Monto && c.HayJacuzzi && c.HayReserva
                                select new CabanaFiltradaDTO
                                {
                                    Nombre = c.Nombre.Valor,
                                    CantPersonas = c.CantPersMax
                                };
                return Ok(resultado);
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);

            }
            catch (ADException A)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
            catch (Exception E)
            {

                return BadRequest(E.Message);
            }
        }

        [HttpGet("BuscarID")]
        public IActionResult BuscarID(int Id)
        {
            try
            {
                Cabana c = _busquedaCabana.BuscarCabana(Id);
                CabanaDTO CN = new CabanaDTO() {
                    Id = c.Id,
                    TipoCabId = c.TipoCabId,
                    Nombre = c.Nombre.Valor,
                    Desc = c.Desc,
                    HayJacuzzi = c.HayJacuzzi,
                    HayReserva = c.HayReserva,
                    NumHabitacion = c.NumHabitacion,
                    CantPersMax = c.CantPersMax,
                    Foto = c.Foto
                };
                return Ok(CN);
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);

            }
            catch (ADException A)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
            catch (Exception E)
            {

                return BadRequest(E.Message);
            }
        }
    }
}
    
