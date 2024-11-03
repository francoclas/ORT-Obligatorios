namespace WebAPI.DTOS
{
    /// <summary>
    /// DTO para los mantenimientos..
    /// </summary>
    public class MantDTO
    {

        public string Tecnico { get; set; }
        public string Desc { get; set; }
        public int PrecioP { get; set; }
        public DateTime Fecha { get; set; }

    }
}
