using LogicaNegocio.Excepciones.Usuario;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.VO
{
    public class User
    {
        private string _valor;
        public string Valor {
            get { return _valor; } private set { _valor = value; }

        }
        public User() { }
        public User(string valor)
        {
            if (string.IsNullOrEmpty(valor))
                throw new UsuarioException("Debe ingresar un nombre de usuario.");
        }

    }
}
