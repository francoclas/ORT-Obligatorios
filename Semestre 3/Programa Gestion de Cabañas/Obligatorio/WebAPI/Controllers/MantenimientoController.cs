using LogicaAccesoDatos.Excepciones;
using LogicaApp.CasoUso.CabanaCU;
using LogicaApp.CasoUso.MantenimientoCU;
using LogicaNegocio.Entidades;
using LogicaNegocio.Excepciones;
using LogicaNegocio.VO;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Hosting;
using WebAPI.DTOS;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
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
        [HttpPost("Alta")]
        [ProducesResponseType(StatusCodes.Status201Created)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult Alta([FromBody] MantDTO M, int IDCab)
        {
            try
            {
                Mantenimiento mantenimiento = new Mantenimiento()
                {
                    Fecha = M.Fecha,
                    Desc = M.Desc,
                    Costo = M.PrecioP,
                    Tecnico = new Tecnico(M.Tecnico),
                    CabanaId = IDCab
                };
                _altaMantenimiento.Nuevo(mantenimiento);
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

        [HttpGet("Listar", Name = "Elpapu")]
        public IActionResult ListarMantenimientos(int IdCab, DateTime F1, DateTime F2) {
            try
            {
                List<MantDTO> Salida = TransformarLista(_busquedaMantenimiento.ListarMantenimientosFechas(F1,F2,IdCab));
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

        List<MantDTO> TransformarLista(IEnumerable<Mantenimiento> L) {
            List<MantDTO> Salida = new List<MantDTO>();

            foreach (var item in L)
            {
                MantDTO O = new MantDTO()
                {
                    Tecnico = item.Tecnico.Valor,
                    Desc = item.Desc,
                    PrecioP = item.Costo,
                    Fecha = item.Fecha,

                };
                Salida.Add(O);
            }
            return Salida;
        }
    }

}

