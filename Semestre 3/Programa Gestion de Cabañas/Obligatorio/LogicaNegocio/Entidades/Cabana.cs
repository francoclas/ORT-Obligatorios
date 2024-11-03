using LogicaNegocio.InterfacesDom;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LogicaNegocio.Excepciones.Tipo;
using System.Text.RegularExpressions;
using LogicaNegocio.VO;

namespace LogicaNegocio.Entidades
{
    public class Cabana : IValidable, IEntity
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        public Nombre Nombre { get; set; }
        public string? Desc { get;set; }
        //public int Costo { get; set; }
        public bool HayJacuzzi { get; set; }
        public bool HayReserva { get; set; }

        public int NumHabitacion { get; set; }
        public int CantPersMax { get; set; }

        public string? Foto { get; set; }

        public int TipoCabId { get; set; }

        public TipoCab TipoCab { get; set; }
        public List<Mantenimiento> mantenimientos { get; set; } = new List<Mantenimiento>();

        public void Validar()
        {
            ValNom();
            ValDesc();
        }

        private void ValNom()
        {
            Regex regex = new Regex("^[a-zA-Z ]*$");
            if (string.IsNullOrEmpty(Nombre.Valor))
                throw new TipoNomException("El nombre no puede ser vacio.");
            if (!regex.IsMatch(Nombre.Valor))
                throw new TipoNomException("El nombre no puede contener caracteres especiales.");
        }
        private void ValDesc()
        {
            if (string.IsNullOrEmpty(Desc))
                throw new TipoDescException("La descripcion no puede ser vacia.");
            if (Desc.Length < Data.LimMinDescCab)
                throw new TipoDescException($"La descripcion no puede tener menos de {Data.LimMinDescTipo} caracteres.");
            if (Desc.Length > Data.LimMaxDescCab)
                throw new TipoDescException($"La descripcion no puede tener menos de {Data.LimMaxDescTipo} caracteres.");
        }
    }
}
