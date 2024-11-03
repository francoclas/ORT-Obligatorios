namespace MVCR.Models
{
    public class ModelMantCab
    {
        //Informacion cabana
        public int IdCabana { get; set; }
        public string? NombreCabana { get; set; }
        public string? DescripcionCabana { get; set; }
        public bool HayJacuzzi { get; set; }
        public bool HayReserva { get; set; }

        public int NumHabitacion { get; set; }
        public int CantPersMax { get; set; }

        public string? Foto { get; set; }

        //Informacion Mant

        public DateTime Fecha { get; set; }
        public string? DescMantenimiento { get; set; }
        public int Costo { get; set; }
        public string? Tecnico { get; set; }
    }
}
