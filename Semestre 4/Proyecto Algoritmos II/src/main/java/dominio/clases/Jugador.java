package dominio.clases;
import interfaz.Categoria;
import java.util.Objects;

public class Jugador implements Comparable<Jugador> {
    private String Alias;
    private String Nombre;
    private String Apellido;
    private Categoria categoria;

    //Metodos constructores y basicos
        public Jugador(String alias, String nombre, String apellido, Categoria categoria) {
            Alias = alias;
            Nombre = nombre;
            Apellido = apellido;
            this.categoria = categoria;
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
}
