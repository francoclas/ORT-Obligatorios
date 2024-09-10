using System;
using System.Collections.Generic;
using System.Text;
using Biblioteca.Enums;

namespace Biblioteca
{
    public class Incidencia
    {
        public Jugador JugadorInci { get; set; }
        public Partido PartidoRefer { get; set; }
        public int Minuto { get; set; }
        public IncidenciaDesc Desc { get; set; }

        public Incidencia() { }

        public Incidencia(Jugador jugadorInci,Partido partRefe, int minuto, IncidenciaDesc desc)
        {
            JugadorInci = jugadorInci;
            PartidoRefer = partRefe;
            Minuto = minuto;
            Desc = desc;
        }

        public override string ToString()
        {
            return $"Incidencia generada en el partido {PartidoRefer.ToString()}, al jugador {JugadorInci.NomCompleto}, en el minuto {Minuto}. Descripcion de incidencia: {Desc}";
        }

        public override bool Equals(object obj)
        {
            return  obj is Incidencia inc && JugadorInci == inc.JugadorInci && PartidoRefer == inc.PartidoRefer && Minuto == inc.Minuto;
        }
    }
}
