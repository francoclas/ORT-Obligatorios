using LogicaNegocio.Excepciones.Mantenimiento;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.VO
{
    public class Tecnico
    {
        private string _valor;
        public string Valor { get { return _valor; } private set { _valor = value; } }
        public Tecnico() { }

        public Tecnico(string valor) {
            if (string.IsNullOrEmpty(valor))
                throw new NombreException("Ingrese nombre del tecnico");
            _valor = valor;
        }
    }
}
