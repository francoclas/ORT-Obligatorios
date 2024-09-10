/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Propietario;

import Excepciones.PropietarioException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frgomez
 */
public class Propietario {
    private String CI;
    private String NombreCompleto;
    private Double Saldo;
    private List<Vehiculo> Vehiculos = new ArrayList<>();
    public Propietario(String CI, String NombreCompleto) {
        this.CI = CI;
        this.NombreCompleto = NombreCompleto;
    }

    public Propietario(String CI,String NomPropietario,double Saldo) {
        this.CI = CI;
        this.NombreCompleto = NomPropietario;
        this.Saldo = Saldo;
        
    }
    public Propietario(String Ci){
        this.CI = Ci;
    }
    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }
    
    
    //Metodos
    
    public void CobrarEstadia(double Precio){
       
    }
    
    public Vehiculo BuscarVehiculo(String NumPat) throws PropietarioException{
        Vehiculo Salida = null;
        Vehiculo Aux = new Vehiculo(NumPat);
        
        for (Vehiculo vehiculo : Vehiculos) {
            if (vehiculo.equals(Aux)) {
                Salida = vehiculo;
            }
        }
        if (Salida == null) {
            throw new PropietarioException("No tiene vehiculo con ese patron");
        }
        return Salida;
    }
    
    @Override
    public boolean equals(Object Prop){
        Propietario Aux = (Propietario) Prop;
        return this.CI.equals(Aux.CI);
        
    }

    public boolean TengoVehiculo(String NumPat) {
        Vehiculo Aux = new Vehiculo(NumPat) ;
        return Vehiculos.contains(Aux);
    }

    public void Agregarvehiculo(Vehiculo V) {
        if (!Vehiculos.contains(V)) {
            Vehiculos.add(V);
        }
    }
}
