using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Cliente.Models
{
    public class Cabana
    {
        public string Nombre { get; set; }
        public string? Desc { get; set; }
        public bool HayJacuzzi { get; set; }
        public bool HayReserva { get; set; }

        public int NumHabitacion { get; set; }
        public int CantPersMax { get; set; }

        public string? Foto { get; set; }
        public int id { get; set; }
        public int tipoCabId { get; set; }
        public IFormFile Img { get; set; }
        

    }
}
