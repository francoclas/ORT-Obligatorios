using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Mantenimiento
{
    public class CostoException : MantException
    {
        public CostoException() { }
        public CostoException(string message) : base(message) { }
    }
}
