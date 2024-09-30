package dominio.clases;

public class Sucursal implements Comparable<Sucursal> {
    private String Codigo;
    private String Nombre;

    public Sucursal(String codigo, String nombre) {
        this.Codigo = codigo;
        this.Nombre = nombre;

    }

    @Override
    public int compareTo(Sucursal o) {
        return Codigo.compareTo(o.Codigo);
    }
}
