package dominio.tads.lista;

import dominio.tads.lista.Nodo;

public class Lista<T> implements ILista<T> {
    private Nodo<T> inicio;
    private int cantidad;

    public Lista(Nodo<T> inicio) {
        this.inicio = inicio;
        this.cantidad++;
    }

    public Lista() {
        inicio=null;
        cantidad=0;
    }

    @Override
    public void insertar(T dato) {
        this.inicio=new Nodo<T>(dato,this.inicio);
        this.cantidad++;
    }

    @Override
    public boolean existe(T dato) {
        return false;
    }

    @Override
    public T recuperar(T dato) {
        return null;
    }

    @Override
    public void eliminar(T dato) {

    }

    @Override
    public T recuperar(int indice) {
        return null;
    }

    @Override
    public void eliminar(int indice) {

    }

    @Override
    public int largo() {
        return 0;
    }

    @Override
    public void mostrarIter() {

    }

    @Override
    public void mostrarRec() {

    }
}
