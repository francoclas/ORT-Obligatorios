using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LogicaNegocio.Excepciones;
using LogicaNegocio.Excepciones.Tipo;

namespace LogicaNegocio.VO
{
    public class DescTipo
    {
        private string _valor;
        public string Valor { get { return _valor; } private set { _valor = value; } }
        public DescTipo() { }
        public DescTipo(string valor)
        {
            if (string.IsNullOrEmpty(valor))
                throw new TipoDescException("Debe ingresar una descripcion");
            _valor = valor;
        }
    }
}
