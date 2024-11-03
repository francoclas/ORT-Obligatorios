using LogicaNegocio.Excepciones.Tipo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.VO
{
    public class Nombre
    {
        private string _valor;
        public string Valor { get { return _valor; } private set { _valor = value; } }
        public Nombre() { }
        public Nombre(string valor)
        {
            if (string.IsNullOrEmpty(valor))
                throw new TipoNomException("Debe ingresar un nombre");

            _valor = valor;
        }
    }
}
