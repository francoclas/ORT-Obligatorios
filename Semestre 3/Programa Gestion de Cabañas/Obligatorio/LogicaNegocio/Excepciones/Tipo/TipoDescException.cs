using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Excepciones.Tipo
{
    public class TipoDescException : TipoCabException
    {
        public TipoDescException() { }
        public TipoDescException(string message) : base(message) { }
    }
    }
