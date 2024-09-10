using LogicaNegocio.InterfacesDom;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.Entidades
{
    internal class Cabaña : IValidable, IEntity
    {
        //[Key]
        public int Id { get; set; }

        public string? Nombre { get; set; }
        public bool HayJacuzzi { get; set; }
        public bool HayReserva { get; set; }

        public int NumHabitacion { get; set; }
        public int CantPersMax { get; set; }

        public string? Foto { get; set; } //falta saber como pasar ala foto


        public void Validar()
        {
            throw new NotImplementedException();
        }
    }
}
