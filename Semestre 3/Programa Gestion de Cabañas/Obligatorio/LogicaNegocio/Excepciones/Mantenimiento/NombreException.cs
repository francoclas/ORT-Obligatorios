using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Mantenimiento
{
    public class NombreException : MantException
    {
        public NombreException() { }
        public NombreException(string message) : base(message) { }
    }
}
