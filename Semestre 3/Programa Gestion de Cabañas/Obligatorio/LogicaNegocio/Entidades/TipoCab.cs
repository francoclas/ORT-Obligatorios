using LogicaNegocio.InterfacesDom;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Entidades
{
    internal class TipoCab : IValidable
    {
        //[Key]
        public string Nombre { get; set; }
        public string? Desc { get; set;}
        public int CostoP { get; set;}

        public void Validar()
        {
            throw new NotImplementedException();
        }
    }
}
