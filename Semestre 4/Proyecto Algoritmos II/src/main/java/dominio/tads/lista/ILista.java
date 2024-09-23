package dominio.tads.lista;

public interface ILista<T> {
    //realizamos un insertar al inicio
    public void insertar(T dato);

    public boolean existe(T dato);

    public T recuperar(T dato);

    public void eliminar(T dato);

    public T recuperar(int indice);

    public void eliminar(int indice);

    public int largo();

    public void mostrarIter();

    public void mostrarRec();
}
