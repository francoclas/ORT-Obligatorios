using Biblioteca;
using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    public class Jugador : IValidable, IValidarPrecio, IComparable, IComparer<Jugador>
    {
        private static int ValorMedida { get; set; } = 0;
        public int IdJugador { get; set; }
        public string NomCompleto { get; set; }
        public string NumCamiseta { get; set; }
        public DateTime FechaNac { get; set; }
        public double Altura { get; set; }
        public string PieHabil { get; set;}
        public int ValMercado { get; set; }
        public Moneda TipoMoneda { get; set; }
        public Pais Pais { get; set; }
        public bool esVip { get; set; } = false;
        public string Posicion { get; set; }
     
        public Jugador () { }

        public Jugador(int idJugador, string numCamiseta, string nomCompleto, DateTime fechaNac, double altura, string pieHabil, int valMercado, Moneda tipoMoneda,Pais pais , string posicion)
        {
            IdJugador = idJugador;
            NomCompleto = nomCompleto;
            NumCamiseta = numCamiseta;
            FechaNac = fechaNac;
            Altura = altura;
            PieHabil = pieHabil;
            ValMercado = valMercado;
            TipoMoneda = tipoMoneda;
            Pais = pais;
            Posicion = posicion;
        }
        public void CambiarValorReferencial(int valor) {
            if (valor < 0)
                throw new Exception("El nuevo valor de mercado no puede ser menor a 0.");
            else
                ValorMedida = valor;
        }
        public void VerificarCategoria() {
            if (ValMercado > ValorMedida)
                esVip = true;
            else
                esVip = false;
        }
        public override string ToString()
        {
            //return $"Jugador: {NomCompleto}, juega de {Posicion} con el numero {NumCamiseta}, mide {Altura}, es {PieHabil}, es de  {Pais.Nombre}. Valor en mercado {ValMercado}{TipoMoneda} ";
            return $"{NomCompleto} - ";
        }

        public override bool Equals(object obj)
        {
            return obj is Jugador jugador && IdJugador == jugador.IdJugador;
        }

        //Interfaces
        public void ValidacionDatos()
        {
            if (ValMercado < 0)
                throw new Exception("El valor del jugador no puede ser menor a 0.");
            if (Altura < 0)
                throw new Exception("El valor de la altura no puede ser menor a 0.");
            if (string.IsNullOrEmpty(NomCompleto))
                throw new Exception("El nombre no puede ser vacio");
            if (string.IsNullOrEmpty(Posicion))
                throw new Exception("La posicion no puede ser vacia");
            if (string.IsNullOrEmpty(NumCamiseta))
                throw new Exception("El numero de camiseta no puede ser vacio.");
            if (!Enum.IsDefined(typeof(Moneda),this.TipoMoneda))
                throw new Exception("Tipo de moneda no soportada, o no existe.");
            if (string.IsNullOrEmpty(PieHabil))
                throw new Exception("Tipo de pie habil no soportado o no existe.");
            if (FechaNac == new DateTime(1, 1, 1))
                throw new Exception("La fecha de nacimiento no puede ser valor null.");
        }

        //Se modificaron interfaces de comparacion para comparar por ValMercado.
        public int CompareTo(object K)
        {
            Jugador L = (Jugador)K;
            if (ValMercado.CompareTo(L.ValMercado) > 0)
                return-1;
            else if (ValMercado.CompareTo(L.ValMercado) < 0)
                return 1;
            else
                return Compare(this, L);

        }
        public int Compare(Jugador J, Jugador K) {

            if (J.ValMercado.CompareTo(K.ValMercado) > 0)

                return -1;
            else if (J.ValMercado.CompareTo(K.ValMercado) < 0)
                return 1;
            else
                return 0;
        }
    }
}
