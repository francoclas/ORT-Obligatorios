using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Tipo
{
    public class TipoCabException : DomainException

    {
        public TipoCabException() { }

        public TipoCabException(string message) : base(message) { }

    }
}
