using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using WebAPI.DTOS;
using LogicaApp.CasoUso.UsuarioCU;

namespace WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuarioController : Controller
    {
        private Registro _registro;
        private InicioSesion _inicioSesion;
        
        public UsuarioController(Registro r,InicioSesion i)
        {
            _registro = r;
            _inicioSesion = i;

        }

        [HttpPost]
		[AllowAnonymous]
        [Route("login")]
        public ActionResult<UsuarioDTO> Login([FromBody] LoginDTO usuario)
        {
            try
            {

                var usr = _inicioSesion.IniciarSesion(usuario.Email, usuario.Password); 
                if (!usr)
                    return Unauthorized("Credenciales inválidas. Reintente");
                var Us = _inicioSesion.DevolverUsuario(usuario.Email, usuario.Password);

                //Le pedimos al manejador de tokens que nos genere un token jwt con
                //la información del usuario para usar como claims (el email y el nombre de rol)
                var token = JWTMan.GenerarToken(Us);

                return Ok(new
                {
                    Token = token

                });

            }
            catch (Exception ex)
            {
                return Unauthorized(new { Message = "Se produjo un error. Intente n" });
            }
        }
    }
}

