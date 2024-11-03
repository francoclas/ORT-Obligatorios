using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Mantenimiento
{
    public class MantException :DomainException
    {
        public MantException() { }

        public MantException(string message) : base(message) { }
    }
}
