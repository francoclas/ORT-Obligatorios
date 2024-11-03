using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using LogicaNegocio.Entidades;
public class JWTMan
    {
    public static string GenerarToken(Usuario usuarioDto)
    {
        var tokenHandler = new JwtSecurityTokenHandler();

        //clave secreta, generalmente se incluye en el archivo de configuración
        //Debe ser un vector de bytes 

        var clave = Encoding.ASCII.GetBytes(Data.PrivateKey);


        //Se incluye un claim para el rol

        var tokenDescriptor = new SecurityTokenDescriptor
        {
            Subject = new ClaimsIdentity(new Claim[]
            {
                    new Claim(ClaimTypes.Name, usuarioDto.Nombre),
                    new Claim(ClaimTypes.Email, usuarioDto.Correo),
            }),
            Expires = DateTime.UtcNow.AddMonths(1),

            SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(clave),
            SecurityAlgorithms.HmacSha256Signature)
        };

        var token = tokenHandler.CreateToken(tokenDescriptor);

        return tokenHandler.WriteToken(token);
        }
    }

