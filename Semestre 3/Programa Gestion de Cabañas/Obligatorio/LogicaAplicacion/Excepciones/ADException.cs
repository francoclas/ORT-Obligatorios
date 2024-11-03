using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Excepciones
{
    public class ADException : Exception
    {
        public ADException() { }

        public ADException(string message) : base(message) { }

    }
}
