/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Propietario;

import Logica.Parking.Etiqueta;
import java.util.ArrayList;
import java.util.List;
import simuladortransito.Transitable;

/**
 *
 * @author frgomez
 */
public class Vehiculo implements Transitable{
    private String NumPat;
    private Propietario Dueno;
    private TipoVehiculo Tipo;
    private List<Etiqueta> Etiquetas = new ArrayList<>();

    public Vehiculo(String NumPat, Propietario propietario, TipoVehiculo Tipo) {
        this.NumPat = NumPat;
        this.Dueno = propietario;
        this.Tipo = Tipo;
    }
    
    public Vehiculo(String NumPat){
        this.NumPat = NumPat;
    }
    
    public String DevolverPropietario(){
        return Dueno.getCI();
    }
    
    @Override
    public String getPatente() {
        return NumPat;
    }

    @Override
    public boolean esDiscapacitado() {
     return RevisarEtiqueta("Discapacitado");
    }

    @Override
    public boolean esElectrico() {
        return RevisarEtiqueta("Electrico");
    }

    @Override
    public boolean esEmpleado() {
        return RevisarEtiqueta("Empleado");
    }

    public void AgregarEtiqueta(Etiqueta E){
        if (!Etiquetas.contains(E)) {
            Etiquetas.add(E);
        }
    }
     private boolean RevisarEtiqueta (String Filtro){
        boolean Salida = false;
        for (Etiqueta Etiqueta1 : Etiquetas) {
            if(Etiqueta1.getNombre().equals(Filtro)){
                Salida = true;
            }
        }
        return Salida;
    }

    public String getTipo() {
        return Tipo.getNombre();
    }
    
    @Override
    public boolean equals(Object O){
        Vehiculo Aux = (Vehiculo) O;
        return this.NumPat.equalsIgnoreCase(Aux.NumPat);
        
    }
    
}
