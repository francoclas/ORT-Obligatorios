using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Cabana
{
    public class CabException : DomainException
    {
        public CabException() { }

        public CabException(string message) : base(message) { }
    }
}
