using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    public class Seleccion :IValidable
    {
        public Pais Pais { get; set; }
        public List<Jugador> ListaJugadores { get; set; } = new List<Jugador>();

        public string Jugadoressalida { get; set; } = string.Empty;
        public Seleccion(Pais pais)
        {
            Pais = pais;
        }
        public bool Validarjugador(Jugador J){
            return ListaJugadores.Contains(J);
        }
        public void AgregarJugador(Jugador j)
        {
            if (!this.ListaJugadores.Contains(j)) {
                ListaJugadores.Add(j);
                this.Jugadoressalida += $"{j.NomCompleto} - ";
            }
            else
                throw new Exception("El jugador ya existe en la seleccion.");
        }

        public override string ToString()
        {
           
            return $"{Pais.Nombre}";
        }

        public string DevJug()
        {
            string jugadores = "";
            foreach (var item in this.ListaJugadores)
            {
                jugadores += item.NomCompleto;
            }

            return jugadores;
        }
        public override bool Equals(object obj)
        {
            return obj is Seleccion seleccion &&
                   EqualityComparer<Pais>.Default.Equals(Pais, seleccion.Pais) &&
                   EqualityComparer<List<Jugador>>.Default.Equals(ListaJugadores, seleccion.ListaJugadores);
        }

        public void ValidacionDatos()
        {
            if (Pais == null)
            {
                throw new Exception("El pais ingresado no puede ser vacio o null.");
            }
            if (ListaJugadores.Count < 11)
            {
                throw new Exception("La cantidad de jugadores no puede ser menor a 11.");
            }
        }
    }
}
