namespace MVCR.Models
{
    public class ModelCabana
    {
        public int Id { get; set; }

        public string Nombre { get; set; }
        public string Desc { get; set; }
        public bool HayJacuzzi { get; set; }
        public bool HayReserva { get; set; }

        public int NumHabitacion { get; set; }
        public int CantPersMax { get; set; }
        public string Foto { get; set; }
        public IFormFile Img { get; set; }
    }
}
