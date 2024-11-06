package dominio.tads.lista;

public class Nodo<T> {
    private T dato;

    private Nodo<T> sig;

    public Nodo(T dato) {
        this.dato = dato;
//        this.sig = null;
    }

    public Nodo(T dato, Nodo<T> sig) {
        this.dato = dato;
        this.sig = sig;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSig() {
        return sig;
    }

    public void setSig(Nodo<T> sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return dato.toString() + "";
    }
}
