using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    public class Periodista : IValidable
    {
        private static int UltimoIdPeriodista { get; set; } = 1;
        public int IdPeriodista { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public string Email { get; set; }
        public string Pass { get; set; }

        public Periodista() { }
        public Periodista(string nombre, string apellido, string email, string pass)
        {
            IdPeriodista = UltimoIdPeriodista++;
            Nombre = nombre;
            Apellido = apellido;
            Email = email;
            Pass = pass;
        }
        
        public override string ToString()
        {
            return $"Periodista {Nombre} {Apellido}.";
        }

        public override bool Equals(object obj)
        {
            return obj is Periodista perio && IdPeriodista == perio.IdPeriodista && Email == perio.Email;
        }

    
        public void ValidacionDatos()
        {
            if(string.IsNullOrEmpty(Nombre) || string.IsNullOrEmpty(Apellido))
                throw new Exception("El nombre y el apellido no pueden ser vacios."); ;
            if (!string.IsNullOrEmpty(Email))
            {
                if (Email.LastIndexOf("@") == 1 || Email.LastIndexOf("@") == Email.Length || Email.LastIndexOf("@") == -1) {
                    throw new Exception("El correo no posee el formato adecuado.");
                }
            }
            else
            {
                throw new Exception("El correo no puede ser vacio.");
            }

            if (!string.IsNullOrEmpty(Pass)) {
                if (Pass.Length < 8)
                    throw new Exception("La contraseña ingresada debe tenre mas de 8 caracteres.");
            } else
            {
                throw new Exception("La contraseña no puede ser vacia.");
            }
        }
    }
}
