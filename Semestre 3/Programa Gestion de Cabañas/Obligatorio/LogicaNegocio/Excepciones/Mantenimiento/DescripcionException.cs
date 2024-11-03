using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Mantenimiento
{
    public class DescripcionException : MantException
    {
        public DescripcionException() { }
        public DescripcionException(string message) : base(message) { }
    }
}
