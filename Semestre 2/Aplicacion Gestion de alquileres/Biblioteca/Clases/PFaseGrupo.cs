using System;
using System.Collections.Generic;
using System.Text;

namespace Biblioteca
{
    public class PFaseGrupo : Partido
    {
        private string NomGrupo { get; set; }
        private bool HayEmpate { get; set; }

        public PFaseGrupo()
        {
        }

        public PFaseGrupo(string nomGrupo,Seleccion SLoc, Seleccion SVis,DateTime FPart) : base(SLoc,SVis,FPart)
        {
            NomGrupo = nomGrupo;
        }

        public override void VerificarResultado()
        {

            if (EstadoPartido == "Finalizado")
            {
                int golLocal = 0, golVisitante = 0;
                foreach (var incidencia in ListaIncidencias)
                {
                    if (((int)incidencia.Desc) == 3)
                    {

                        if (SeleccionLocal.Validarjugador(incidencia.JugadorInci))
                            golLocal++;
                        else
                            golVisitante++;

                    }
                }
                Resultado = $"{golLocal}-{golVisitante}";
                if (golLocal == golVisitante)
                {
                    Ganador = "Empate";
                    HayEmpate = true;
                }          
                else
                {
                    if (golLocal > golVisitante)
                        Ganador = SeleccionLocal.Pais.Nombre;
                    else
                        Ganador = SeleccionVisitante.Pais.Nombre;
                }
                        

            }
            else
            {
                throw new Exception("El partido no se encuentra finalizado.");
            }
        }
        public override string ToString()
        {
            return $"Id del partido: {IdPartido}||{SeleccionLocal.Pais.Nombre} VS {SeleccionVisitante.Pais.Nombre} ||";
        }
    }

}
