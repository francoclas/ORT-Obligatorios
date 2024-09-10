using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca.Clases
{
    /*Se utiliza unicamente para poder enviar dos models en View/Operador/VistaListaResenas y poder enviar los datos tanto del partido como de la resena segun se necesita*/
    public class ResenaPartido
    {
        public Reseña R { get; set; }
        public Partido P {get; set; }

        public ResenaPartido()
        {
        }
        public ResenaPartido(Reseña r)
        {
            R = r;
            P = r.PartidoRes;
        }
    }
}
