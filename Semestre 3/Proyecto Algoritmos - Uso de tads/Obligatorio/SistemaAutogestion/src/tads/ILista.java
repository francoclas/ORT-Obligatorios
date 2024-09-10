/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author frgomez
 */
public interface ILista<T extends Comparable<T>> {
      //pre: averigua si una lista es vacia o no
    //post: retorna true si la lista es vacia y false si no lo es
    public boolean esVacia();
    
    //pre: partimos de una lista de datos T (vacia o no)
    //post: retorna un entero que indica la cantidad de elem de la lista
    public int cantidadElementos();
    
    //pre: tenemos una lista de datos T
    //post: muestra los datos de la lista
    public void mostrar();
    
    //pre: tenemos una lista de datos T
    //post: obtenemos una lista vacía (inicio=null)
    public void vaciar();
    
    //pre: tenemos una lista de datos de tipo T
    //post:agrega un nodo con el dato x en el inicio 
    public void agregarInicio(T x);
    
    //pre: tenemos una lista de datos de tipo T
    //post: se agrega un elemento de tipo T al final de la lista
    public void agregarFinal(T x);
    
    //pre: tenemos una lista de datos de tipo T
    //post: se elimina el primer elemento de la lista.
    public void eliminarInicio();
    
    //pre: tenemos una lista de datos de tipo T
    //post: obtenemos la lista sin el ùltimo elemento
    public void eliminarFinal();
    
    //pre: se tiene una lista de tipo T
    //post: se obtiene la lista sin el nodo que contiene el dato x
    public void eliminarElemento(T x);
    
    //pre: se tiene una lista de tipo T
    //post: retorna un booleano con true si el dato x esta en la lista
    public boolean estaElemento(T x);
    
    //pre: se tiene una lista de tipo T ORDENADA ascendente
    //post: se agrega el dato x en el lugar correspondiente 
    //manteniendo el orden 
    public void agregarOrdenado(T x);
    
    //pre: se tiene una lista de tipo T
    //post: se retorna el nodo que contiene el dato x y null en caso de que no exista
    public Nodo obtenerElemento(T x);
}
