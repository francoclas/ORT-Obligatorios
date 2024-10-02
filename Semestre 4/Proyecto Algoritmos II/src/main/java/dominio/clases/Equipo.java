package dominio.clases;

import dominio.tads.abb.ABB;

public class Equipo implements Comparable<Equipo>{
    private String Nombre;
    private String Manager;
    private ABB<Jugador> Plantel;
    private int cantJugadoresActual;
    //Metodos baiscos
    public Equipo(String nombre, String manager) {
        Nombre = nombre;
        Manager = manager;
        Plantel = new ABB<Jugador>();
    }
    //Se utiliza para comparar o en casos de existencia
    public Equipo(String nombreEquipo) {
        Nombre = nombreEquipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }
    public int getCantJugadores(){
        return cantJugadoresActual;
    }
    @Override
    public String toString(){
        return this.Nombre + ";" + this.Manager + ";" + cantJugadoresActual;
    }
    //Funciones obligatorio
        public void AgregarJugador(Jugador Nuevo) throws Exception {
            //Verifico que el equipo no este completo
            if (cantJugadoresActual == 5) {
                throw new Exception("El equipo esta completo");
            }
            //Agrego el jugador al plantel
            Plantel.insertar(Nuevo);
        }
        public String ListarJugadores(){
            return Plantel.listarAscendentemente();
        }

        //Nombre es identificador unico
        @Override
        public int compareTo(Equipo o) {
            return this.Nombre.compareTo(o.getNombre());
        }
}
