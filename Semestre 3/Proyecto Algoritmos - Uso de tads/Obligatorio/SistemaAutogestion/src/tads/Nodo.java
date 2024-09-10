/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author frgomez
 */
public class Nodo<T extends Comparable<T>> {
    private T Objeto;
    private Nodo<T> siguiente;

    public Nodo(T dato) {
        this.Objeto = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return Objeto;
    }

    public void setDato(T dato) {
        this.Objeto = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
