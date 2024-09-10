using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    public class Pais : IValidable
    {
        private static int IDPaisGlobal = 1;
        public int IdPais { get; set; }
        public string Nombre { get; set; }
        public string CodAl { get; set; }

        public Pais () { }
        public Pais(string nombre, string codAl)
        {
            IdPais = IDPaisGlobal++;
            Nombre = nombre;
            CodAl = codAl;
        }

        public override string ToString()
        {
            return $"Pais: {Nombre}, codigo: {CodAl} .";
        }

        public override bool Equals(object obj)
        {
            return obj is Pais pais &&
                   IdPais == pais.IdPais &&
                   Nombre == pais.Nombre &&
                   CodAl == pais.CodAl;
        }

        public void ValidacionDatos()
        {
            if (string.IsNullOrEmpty(Nombre))
                throw new Exception("El nombre del pais no puede ser vacio.");        
            if (CodAl.Length != 3)
                throw new Exception("El codigo de pais solo puede ser 3 caracteres.");
        }
    }
}
