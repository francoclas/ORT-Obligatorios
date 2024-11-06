package dominio;

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
        public void AgregarJugador(Jugador Nuevo){
            //Agrego el jugador al plantel
            Plantel.insertar(Nuevo);
            //Sumo cantidad de jugadores
            cantJugadoresActual++;
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
