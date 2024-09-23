package dominio.tads.abb;

public interface IABB {
    public void insertar(int dato);

    public void listarAscendentemente();

    public void listarDescendentemente();

    public boolean existe(int dato);

    public int borrarMinimo();

}
