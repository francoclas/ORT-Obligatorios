using LogicaApp.CasoUso.TipoCabCU;
using LogicaAccesoDatos.Excepciones;
using Microsoft.AspNetCore.Mvc;
using System.ComponentModel;
using LogicaNegocio.Excepciones;
using Azure;
using Swashbuckle.Swagger.Annotations;
using LogicaNegocio.Entidades;
using WebAPI.DTOS;
using LogicaNegocio.VO;
using Microsoft.AspNetCore.Authorization;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
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

        [HttpGet("ListarTodos")]
        public IActionResult ListarTodos() {
            try
            {
                List<TipoCabAltaDTO> salida = new List<TipoCabAltaDTO>();
                foreach (var item in _busquedatipo.ListarTodos())
                {
                    TipoCabAltaDTO N = new TipoCabAltaDTO()
                    {
                        Id = item.Id,
                        Nombre = item.Nombre,
                        Desc = item.Desc.Valor,
                        CostoP = item.CostoP,
                    };
                    salida.Add(N);
                }
                return Ok(salida);
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);

            } catch (ADException A)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            } catch (DomainException A)
            {
                return BadRequest(A.Message);
            }


        }
        [HttpPost]
        public IActionResult Alta([FromBody] TipoCabAltaDTO T)
        {
            try
            {
                TipoCab Sal = new TipoCab()
                {
                    Nombre = T.Nombre,
                    Desc = new DescTipo(T.Desc),
                    CostoP = T.CostoP,
                };
                _altatipocab.Nuevo(Sal);
                return Ok();
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);
            }
            catch (ADException)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
        }

        [HttpGet("BusquedaTipo")]
        public IActionResult BusquedaTipo(string Nom) {
            try
            {
                List<TipoCabAltaDTO> salida = new List<TipoCabAltaDTO>();
                foreach (var item in _busquedatipo.BuscarNombre(Nom))
                {
                    TipoCabAltaDTO N = new TipoCabAltaDTO()
                    {
                        Id = item.Id,
                        Nombre = item.Nombre,
                        Desc = item.Desc.Valor,
                        CostoP = item.CostoP,
                    };
                    salida.Add(N);
                }
                return Ok(salida);
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);
            }
            catch (ADException)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }

        }
        [HttpPut("ModificarTipo")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult ModificarTipo([FromBody] TipoCabAltaDTO T) {
            try
            {
                TipoCab C = new TipoCab() { Id =  T.Id, Desc = new DescTipo(T.Desc), CostoP = T.CostoP };
                _editartipo.Editar(C);
                return Ok();
            }
            catch (NotFoundException A)
            {
                return NotFound(A.Message);
            }
            catch (ADException)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }

        }

        [HttpDelete("EliminarTipo")]
        public IActionResult EliminarTipo(int Id)
        {
            try
            {
                _bajaTipo.Baja(Id);
                return StatusCode(204, "Se elimino el tipo correctamente.");
            }
            catch(NotFoundException A)
            {

                return NotFound(A.Message);
            }
            catch (ADException A)
            {
                return StatusCode(500, A.Message);
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
        }

        [HttpGet("id")]
        public IActionResult BuscarTipoId(int ID)
        {
            try
            {
                var A = _busquedatipo.BuscarId(ID);
                TipoCabAltaDTO T = new TipoCabAltaDTO()
                {
                    Id = A.Id,
                    Nombre = A.Nombre,
                    Desc = A.Desc.Valor,
                    CostoP = A.CostoP,
                };
                return Ok(T);
            }
            catch (NotFoundException A)
            {

                return NotFound(A.Message);
            }
            catch (ADException)
            {
                return StatusCode(500, "Intente nuevamente en otro momento.");
            }
            catch (DomainException A)
            {
                return BadRequest(A.Message);
            }
        }
    }
}
