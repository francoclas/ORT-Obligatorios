package dominio.clases;
import interfaz.Categoria;

public class Jugador implements Comparable<Jugador> {
    private String Alias;
    private String Nombre;
    private String Apellido;
    private Categoria categoria;
    private Equipo equipo;

    //Metodos constructores y basicos
        public Jugador(String alias, String nombre, String apellido, Categoria categoria) {
            Alias = alias;
            Nombre = nombre;
            Apellido = apellido;
            this.categoria = categoria;
            this.equipo = null;
        }

    public Jugador(String aliasJugador) {
            Alias = aliasJugador;
    }

    //Getters y setters
        public String getAlias() {
            return Alias;
        }

        public void setAlias(String alias) {
            Alias = alias;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String nombre) {
            Nombre = nombre;
        }

        public String getApellido() {
            return Apellido;
        }

        public void setApellido(String apellido) {
            Apellido = apellido;
        }

        public Categoria getCategoria() {
            return categoria;
        }

        public void setCategoria(Categoria categoria) {
            this.categoria = categoria;
        }
        public void setEquipo(Equipo nequipo){
            this.equipo = nequipo;
        }
        //Comparables y equals
        @Override
        public int compareTo(Jugador o) {
            //Comparo por alias, como identificador unico
            return this.Alias.compareTo(o.getAlias());
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Jugador JAux = (Jugador) o;
            return this.Alias.equals(JAux.getAlias());
        }
        //string
        @Override
        public String toString(){
            //Esta formateado para la salida de los listados en puntos
            return this.Alias + ";" + this.Nombre + ";" + this.Apellido + ";" + categoria.getTexto() + "|";
        }


    //Funciones aux
    public boolean esPro() {
        if (categoria.getTexto().equals("Profesional")){
            return true;
        }
        return false;
    }

    public boolean tieneEquipo() {
            if (this.equipo == null){
                return false;
            }
            return true;
    }

    public boolean perteneceCat(Categoria cat){
            return this.categoria.getTexto().equals(cat.getTexto());
    }
}
