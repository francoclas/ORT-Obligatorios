using Biblioteca;
using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace Biblioteca.Clases
{
    public class Usuario : IValidable, IComparable, IComparer<Usuario>
    {
        public int id { get; set; }
        public string usuario { get; set; }
        public string pass { get; set; }
        public string rol { get; set; }

        public Usuario() { }
        public Usuario(int id, string usuario, string pass, string rol)
        {
            this.id = id;
            this.usuario = usuario;
            this.pass = pass;
            this.rol = rol;
        }
        public void ValidacionDatos()
        {
            if (string.IsNullOrEmpty(usuario) || string.IsNullOrEmpty(pass))
                throw new Exception("El nombre y el apellido no pueden ser vacios."); ;

            if (!string.IsNullOrEmpty(pass))
            {
                if (pass.Length < 8)

                    throw new Exception("La contraseña ingresada debe tenre mas de 8 caracteres.");
            }
            else
            {
                throw new Exception("La contraseña no puede ser vacia.");
            }
        }

        public override bool Equals(object obj)
        {
            return obj is Usuario us && id == us.id && usuario == us.usuario;
        }
        public int Compare(Usuario U, Usuario O)
        {
            return U.usuario.CompareTo(O.usuario);
        }

        public int CompareTo(object obj)
        {
            Usuario o = (Usuario)obj;
            return usuario.CompareTo(o.usuario);
        }
    }
}
