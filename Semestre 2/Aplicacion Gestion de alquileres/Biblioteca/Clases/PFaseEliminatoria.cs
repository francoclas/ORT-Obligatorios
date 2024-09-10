using System;
using System.Collections.Generic;
using System.Text;
using Biblioteca.Enums;

namespace Biblioteca
{
    public class PFaseEliminatoria : Partido
    {
        public string Etapa { get; set; }
        public bool HayPenales { get; set; }

       public PFaseEliminatoria() { }
        public PFaseEliminatoria(string Etap, Seleccion SLoc, Seleccion SVis, DateTime FPart) : base(SLoc, SVis, FPart)
        {
            Etapa = Etap;
        }

        public override void RegistroIncidencia(Jugador JInc, int min, IncidenciaDesc desc) {
            if (min <= -1)
                throw new Exception("No se puede ingresar como valor de tiempo un valor menor o igual a -1");
            if (HayPenales == true)
                min = -1;
            if (SeleccionLocal.Validarjugador(JInc) || SeleccionVisitante.Validarjugador(JInc))
            {
                Incidencia inc = new Incidencia(JInc, this, min, desc);
                ListaIncidencias.Add(inc);
            }
            else
            {
                throw new Exception("EL jugador no existe en ninguna de las selecciones.");
            }
        }

        public override void VerificarResultado()
        {

            if (EstadoPartido == "Finalizado")
            {
                int golLocal = 0, golVisitante = 0,penalesLoc = 0, penalesVis = 0;
                foreach (var incidencia in ListaIncidencias)
                {
                    if (((int)incidencia.Desc) == 3 && incidencia.Minuto != -1)
                    {

                        if (SeleccionLocal.Validarjugador(incidencia.JugadorInci))
                            golLocal++;
                        else
                            golVisitante++;

                    }
                }
                if (golLocal == golVisitante)
                {
                    HayPenales = true;
                    foreach (var incidencia in ListaIncidencias)
                    {
                        if (((int)incidencia.Desc) == 3 && incidencia.Minuto == -1)
                        {
                            if (SeleccionLocal.Validarjugador(incidencia.JugadorInci))
                                penalesLoc++;
                            else
                                penalesVis++;
                        }
                    }
                   
                    if (penalesLoc > penalesVis)
                        Ganador = SeleccionLocal.Pais.Nombre;
                    else
                        Ganador = SeleccionVisitante.Pais.Nombre;
                    Resultado = $"Empate en tiempo de juego. Ganador: {Ganador} en tanda de penales.";
                }
                else
                {
                    if (golLocal > golVisitante)
                        Ganador = SeleccionLocal.Pais.Nombre;
                    else
                        Ganador = SeleccionVisitante.Pais.Nombre;
                    Resultado = $"Ganador:{Ganador} Resultado: {golLocal}-{golVisitante}";
                }


            }
            else
            {
                throw new Exception("El partido no se encuentra finalizado.");
            }
        }

        public void PartidoConPenales ()
        {
            HayPenales = true;
        }
    }
}
