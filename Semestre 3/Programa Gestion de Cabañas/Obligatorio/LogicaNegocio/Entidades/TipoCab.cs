using LogicaNegocio.InterfacesDom;
using LogicaNegocio.Excepciones;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LogicaNegocio.Excepciones.Tipo;
using System.Text.RegularExpressions;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using LogicaNegocio.VO;

namespace LogicaNegocio.Entidades
{
    public class TipoCab : IValidable
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Nombre { get; set; }
        public DescTipo Desc { get; set;}
        public int CostoP { get; set;}


        public List<Cabana> cabanas { get; set;} = new List<Cabana>();
        public void Validar()
        {
            ValNom();
            ValDesc();
            ValCosto();
        }
        private void ValNom()
        {
            Regex regex = new Regex("^[a-zA-Z ]");
            if (string.IsNullOrEmpty(Nombre))
                throw new TipoNomException("El nombre no puede ser vacio.");
            if (!(this.Nombre == Nombre.Trim()))
                this.Nombre = Nombre.Trim();
        }
        private void ValDesc()
        {
            if (string.IsNullOrEmpty(Desc.Valor))
                throw new TipoDescException("La descripcion no puede ser vacia.");
            if (Desc.Valor.Length < Data.LimMinDescTipo)
                throw new TipoDescException($"La descripcion no puede tener menos de {Data.LimMinDescTipo} caracteres.");
            if (Desc.Valor.Length > Data.LimMaxDescTipo)
                throw new TipoDescException($"La descripcion no puede tener menos de {Data.LimMaxDescTipo} caracteres.");
        }
        private void ValCosto()
        {
            if (this.CostoP <= 0)
                throw new Exception("El costo por persona no puede ser menor a 0");
        }
    }
}
