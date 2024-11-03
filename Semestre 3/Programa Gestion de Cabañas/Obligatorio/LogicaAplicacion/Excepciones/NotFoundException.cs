using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Excepciones
{
    public class NotFoundException : ADException
    {
        public NotFoundException() { }

        public NotFoundException(string message) : base(message) { }
    }
}
