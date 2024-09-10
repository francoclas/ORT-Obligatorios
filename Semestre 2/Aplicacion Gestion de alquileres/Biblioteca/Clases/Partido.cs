using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Biblioteca.Enums;

namespace Biblioteca
{
   public class Partido : IValidable
    {
        private static int UltimoIdPartido { get; set; } = 1 ;
        public int IdPartido { get; set; }
        public Seleccion SeleccionLocal { get; set; }
        public Seleccion SeleccionVisitante { get; set; }
        public DateTime FechaPartido { get; set; }
        public List<Incidencia> ListaIncidencias { get; set; } = new List<Incidencia>();
        public string Resultado{ get; set; }
        public string EstadoPartido { get; set; } = "Pendiente";
        public object Res { get; set; }
        public string Ganador { get; set; }

        public Partido(){}

        public Partido(Seleccion seleccionLocal, Seleccion seleccionVisitante, DateTime fechaPartido)
        {
            IdPartido = UltimoIdPartido++;
            SeleccionLocal = seleccionLocal;
            SeleccionVisitante = seleccionVisitante;
            FechaPartido = fechaPartido;
            
        }
        //Metodo por defecto para registro de alta de incidencia, se valida en caso de que ya posea una tarjeta amarilla en el partido,
        //que sea asi se aplique una tarjeta roja.
        public virtual void RegistroIncidencia(Jugador JInc,int min, IncidenciaDesc desc) {
            if ((int)desc == 1)
                if (JugadorTieneAmarilla(JInc))
                    desc = IncidenciaDesc.roja;
            if (SeleccionLocal.Validarjugador(JInc) || SeleccionVisitante.Validarjugador(JInc))
            {
                Incidencia inc = new Incidencia(JInc, this, min, desc);
                ListaIncidencias.Add(inc);
            }
            else
            {
                throw new Exception("El jugador no existe en ninguna de las selecciones.");
            }

        }
        
        //Se recorre lista de incidencias, buscando incidencias que cuya descripcion sea una tarjeta amarilla y refiera al jugador solicitado.
        //Devuelve verdadero si encuentra una tarjeta amarilla referida al jugador J.
        public virtual bool JugadorTieneAmarilla(Jugador J) {
            foreach (var i in ListaIncidencias)
            {
                if (i.JugadorInci.IdJugador == J.IdJugador && (int)i.Desc == 1)
                    return true;
            }
            return false;
        }
        public virtual void FinalizarPartido() {
            EstadoPartido = "Finalizado";

        }
        public virtual void VerificarResultado() {          
        }
        //Se recorre lista de incidencias, buscando incidencias con descripcion Gol que refieran a la seleccion solicitada. Devuelve cantidad de goles.
        public int CalcularGolesLV(Seleccion s)
        {
            int cont = 0;
            if (EstadoPartido != "Finalizado")
            {
                throw new Exception("El partido no se encuentra finalizado.");
            }           
            foreach (var i in ListaIncidencias)
            {
                if ((int)i.Desc == 3 && SeleccionLocal.Pais.Nombre == s.Pais.Nombre)
                {
                    cont += 1;
                }

            }
            return cont;
        }
        //Devuelve cantidad de incidencias registradas en el partido sin importar su descripcion. Total de incidencias.
        public int CalcularIncidencias()
        {
            return ListaIncidencias.Count();

        }
       
  

        public override string ToString()
        {
            if (EstadoPartido == "Finalizado")
            {
                return $"Partido de {SeleccionLocal.Pais.Nombre} VS {SeleccionVisitante.Pais.Nombre}. Resultado: {Resultado} ";
            }

            return $"Partido de {SeleccionLocal.Pais.Nombre} VS {SeleccionVisitante.Pais.Nombre}. Aun no finaliza. ";
        }
        public string SalResena()
        {
            return $"{this.SeleccionLocal.Pais.Nombre} VS {this.SeleccionVisitante.Pais.Nombre}";
        }
        public override bool Equals(object obj)
        {
            return  obj is Partido part && part.IdPartido == IdPartido;
        }

        public void ValidacionDatos(List<Seleccion> List, DateTime fechaIngreso) {

            if (!List.Contains(SeleccionVisitante))
                throw new Exception("La seleccion visitante no existe en el sistema.");
            if (!List.Contains(SeleccionLocal))
                throw new Exception("La seleccion local no existe en el sistema.");
            if (!(fechaIngreso >= new DateTime(2022,11,20)) && (fechaIngreso <= new DateTime(2022,12,18)))
                throw new Exception("La fecha de ingreso del partido no corresponde al periodo del mundial.");
        } 
        public string SalidaInc()
        {
            return $"{FechaPartido} - Participaron: {SeleccionLocal} VS {SeleccionVisitante} - Cantidad de incidencias: {CalcularIncidencias()}";
        }
        //MVC

        public string ObtenerSeleccion(Jugador J)
        {
            if (SeleccionLocal.Validarjugador(J))
                return SeleccionLocal.Pais.Nombre;
            if (SeleccionVisitante.Validarjugador(J))
                return SeleccionVisitante.Pais.Nombre;

            return "";
        }
    }
}
