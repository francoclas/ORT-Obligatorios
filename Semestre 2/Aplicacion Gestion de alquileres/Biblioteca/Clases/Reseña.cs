using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    public  class Reseña : IValidable
    {
        public int IdReseña { get; set; }
        private static int UltimoIdReseña { get; set; } = 1;
        public Periodista PeriodistaOg { get; set; }
        public Partido PartidoRes { get; set; }
        public DateTime FechaRes { get; set; }
        public string TituloRes { get; set; }
        public string DescRes { get; set; }
        
        public Reseña() {
            IdReseña = UltimoIdReseña++;
            FechaRes = DateTime.Now;

        }

        public Reseña(Periodista periodistaOg, Partido partidoRes, DateTime fechaRes, string tituloRes, string descRes)
        {
            IdReseña = UltimoIdReseña++;
            PeriodistaOg = periodistaOg;
            PartidoRes = partidoRes;
            FechaRes = fechaRes;
            TituloRes = tituloRes;
            DescRes = descRes;
        }

        public override string ToString()
        {
            return $"Reseña titulada: {TituloRes}, contenido: {DescRes}. Redactada por {PeriodistaOg.Nombre} el {FechaRes}. Para el partido {PartidoRes}";
        }

        public override bool Equals(object obj)
        {
            return obj is Reseña objRes && IdReseña == objRes.IdReseña;
        }
        public void ValidacionDatos() { 
            if(PartidoRes == null) 
                throw new Exception("No se ingreso partido, no puede ser valor null.");
            if (PeriodistaOg == null)
                throw new Exception("No se ingreso Periodista, no puede ser valor null.");
            if (string.IsNullOrEmpty(TituloRes) || string.IsNullOrEmpty(DescRes))
                throw new Exception("Verificar que se haya ingresado titulo y descripcion de la reseña.");
            if( FechaRes == new DateTime(1, 1, 1))
                throw new Exception("La hora no puede ser null value.");
        }
    }
}
