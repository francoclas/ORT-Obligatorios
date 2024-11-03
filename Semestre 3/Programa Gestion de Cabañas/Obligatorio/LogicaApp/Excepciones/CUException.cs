using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.Excepciones
{
    public class CUException : Exception
    {
        public CUException() { }
        public CUException(string message) : base(message) { }
    }
}
