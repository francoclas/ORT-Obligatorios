package dominio.clases;

public class Sucursal implements Comparable<Sucursal> {
    private String Codigo;
    private String Nombre;
    private int numVertice;
    public Sucursal(String codigo, String nombre) {
        this.Codigo = codigo;
        this.Nombre = nombre;

    }


    @Override
    public String toString(){
        return Codigo + " - " + Nombre;
    }
    @Override
    public int compareTo(Sucursal o) {
        return this.Codigo.compareTo(o.Codigo);
    }

    public int getNumVertice(){
        return numVertice;
    }
    public void setNumConexion(int vertice){
        this.numVertice = vertice;
    }
}


