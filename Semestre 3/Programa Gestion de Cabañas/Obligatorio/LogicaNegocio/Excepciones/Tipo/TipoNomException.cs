using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Tipo
{
    public class TipoNomException : TipoCabException
    {
        public TipoNomException() { }

        public TipoNomException(string message) : base(message) { }
        
    }
}
