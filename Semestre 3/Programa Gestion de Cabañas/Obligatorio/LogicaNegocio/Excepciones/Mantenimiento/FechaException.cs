using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Mantenimiento
{
    public class FechaException : MantException
    {
        public FechaException() { }
        public FechaException(string message) : base(message) { }
    }
}
