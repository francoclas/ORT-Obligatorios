namespace WebAPI.DTOS
{
    /// <summary>
    /// DTO que filtra la informacion de las cabañas, se utiliza para un caso unico.
    /// </summary>
    public class CabanaFiltradaDTO
    {
        public string Nombre { get; set; }
        public int CantPersonas { get; set; }
    }
}
