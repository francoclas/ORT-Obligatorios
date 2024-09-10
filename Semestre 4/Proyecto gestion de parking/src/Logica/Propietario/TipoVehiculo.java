/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Propietario;

import java.util.Objects;

/**
 *
 * @author frgomez */
public class TipoVehiculo {
    private String Nombre;

    public TipoVehiculo(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public boolean equals(Object obj) {
        TipoVehiculo Aux = (TipoVehiculo) obj;
        return Nombre.equals(Aux.getNombre());     
    }
    
    
}
