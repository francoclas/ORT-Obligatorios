using LogicaNegocio.InterfacesDom;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Entidades
{
    public class Mantenimiento : IValidable, IEntity
    {
        //[Key]
        public int Id { get; set; }
        //[Required]
        public DateTime Fecha { get; set; }
        public string? Desc { get; set;}
        public int Costo { get; set;}  
        public string? Tecnico { get; set;}

        public void Validar()
        {
            throw new NotImplementedException();
        }
    }
}
