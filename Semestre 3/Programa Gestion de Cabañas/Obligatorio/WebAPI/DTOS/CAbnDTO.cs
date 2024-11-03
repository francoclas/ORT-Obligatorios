namespace WebAPI.DTOS
{
    public class CAbnDTO
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
    }
}
