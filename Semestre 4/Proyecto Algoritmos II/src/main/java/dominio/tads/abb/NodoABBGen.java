package dominio.tads.abb;

public class NodoABBGen<T>{

    private T dato;

    private NodoABBGen<T> izq;
    private NodoABBGen<T> der;

    public NodoABBGen(T dato, NodoABBGen izq, NodoABBGen der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public NodoABBGen(T dato) {
        this.dato = dato;
        this.izq=null;
        this.der=null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoABBGen<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoABBGen<T> izq) {
        this.izq = izq;
    }

    public NodoABBGen<T> getDer() {
        return der;
    }

    public void setDer(NodoABBGen<T> der) {
        this.der = der;
    }
}
