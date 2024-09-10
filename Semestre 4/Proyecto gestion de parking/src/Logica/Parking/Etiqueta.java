/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

/**
 *
 * @author frgomez
 */
public class Etiqueta {
    private String Nombre;

    public Etiqueta(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getNombre(){
        return Nombre;
    }
    @Override
    public boolean equals (Object obj){
        Etiqueta Aux = (Etiqueta) obj;
        return Nombre.equals(Aux.getNombre());
    }
            
}
