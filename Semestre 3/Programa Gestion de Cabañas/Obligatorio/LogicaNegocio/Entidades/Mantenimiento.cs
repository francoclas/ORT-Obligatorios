using LogicaNegocio.InterfacesDom;
using LogicaNegocio.Excepciones.Mantenimiento;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LogicaNegocio.Excepciones.Mantenimiento;
using LogicaNegocio.Excepciones.Tipo;
using LogicaNegocio.VO;

namespace LogicaNegocio.Entidades
{
    public class Mantenimiento : IValidable, IEntity
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        //[Required]
        public DateTime Fecha { get; set; }
        public string? Desc { get; set;}
        public int Costo { get; set;}  
        public Tecnico Tecnico { get; set;}

        public int CabanaId { get; set;}

        public Cabana Cabana { get; set;}
        public void Validar()
        {
            ValFecha();
            ValDesc();
            ValNombre();
        }
        private void ValFecha() {
            if (Fecha.Date > DateTime.UtcNow.Date)
                throw new FechaException("La fecha no puede ser superior a la fecha del dia");
        }
        private void ValDesc() {
            if (string.IsNullOrEmpty(Desc))
                throw new DescripcionException("La descripcion no puede estar vacia");
            if (Desc.Length < Data.LimMinDescTipo)
                throw new TipoDescException($"La descripcion no puede tener menos de {Data.LimMinDescTipo} caracteres.");
            if (Desc.Length > Data.LimMaxDescTipo)
                throw new TipoDescException($"La descripcion no puede tener menos de {Data.LimMaxDescTipo} caracteres.");
        }
        private void ValNombre() {
            if (string.IsNullOrEmpty(Tecnico.Valor))
                throw new NombreException("El nombre del tecnico no estar vacio");  
        }

    }
}
