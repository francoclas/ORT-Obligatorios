using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    interface IValidable
    {
       public void ValidacionDatos() { }
    } 

    interface IValidarPrecio
    {
        public void CompararPrecio () { }
    }
}
