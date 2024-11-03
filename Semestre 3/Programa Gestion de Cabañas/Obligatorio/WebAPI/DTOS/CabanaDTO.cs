using LogicaNegocio.VO;

namespace WebAPI.DTOS
{
    /// <summary>
    /// DTO que representa una cabaña. Cuando se implemente no sera necesario rellenar por completo el objeto.
    /// </summary>
    public class CabanaDTO
    {
        public string Nombre { get; set; }
        public string? Desc { get; set; }
        public bool HayJacuzzi { get; set; }
        public bool HayReserva { get; set; }

        public int NumHabitacion { get; set; }
        public int CantPersMax { get; set; }

        public string? Foto { get; set; }
        public int Id { get; internal set; }
        public int TipoCabId { get; set; }
        public IFormFile Img { get; set; }
        
    }
}
