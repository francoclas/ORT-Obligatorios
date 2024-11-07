package dominio;

import java.util.Objects;
public class Sucursal implements Comparable<Sucursal> {
    private String Codigo;
    private String Nombre;
    public Sucursal(String codigo, String nombre) {
        this.Codigo = codigo;
        this.Nombre = nombre;

    }
    //Solo para equals
    public Sucursal(String codigo) {
        this.Codigo = codigo;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return this.Codigo.equals(o);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(Codigo);
    }

    @Override
    public String toString(){
        return Codigo + ";" + Nombre;
    }
    @Override
    public int compareTo(Sucursal o) {
        return this.Codigo.compareTo(o.Codigo);
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}


