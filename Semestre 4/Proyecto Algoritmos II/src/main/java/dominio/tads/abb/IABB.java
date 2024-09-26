package dominio.tads.abb;

public interface IABB <T extends Comparable<T>> {
    public void insertar(T dato);

    public T buscarDato(T dato);

    public String listarAscendentemente();

    public String listarDescendentemente();

    public boolean existe(T dato);

}
