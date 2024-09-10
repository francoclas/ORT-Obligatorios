/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Excepciones.CocheraException;
import Logica.Propietario.TipoVehiculo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frgomez
 */
public class TipoPrecio {
    private ControlParking Parking = new ControlParking();
    TipoVehiculo Tipo;
    double Precio;

    public TipoPrecio(TipoVehiculo Tipo, double Precio) {
        this.Tipo = Tipo;
        this.Precio = Precio;
    }

    public TipoVehiculo getTipo() {
        return Tipo;
    }

    public void setTipo(TipoVehiculo Tipo) {
        this.Tipo = Tipo;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }
    public void ActualizarPrecio(double PN) throws CocheraException{
            ValidarPrecio(PN);
            setPrecio(PN);
        
    }
    private void ValidarPrecio(double PN) throws CocheraException {
        double PrecioPromedio  =  2 * Parking.CalcularPromedio(Tipo.getNombre());
        if(PN > 0){
            throw new CocheraException("Valor invalido. El precio debe ser igual o mayor a cero");
        }
        if(PN < PrecioPromedio){
            throw new CocheraException("Valor demasiado alto. El sistema no permite dispersion de precios por encima del 100%. Ingrese un valor menor a " + PrecioPromedio);
        }     
    }
    
    @Override
    public boolean equals(Object obj){
        TipoPrecio Aux = (TipoPrecio) obj;
        return this.Tipo.equals(Aux.Tipo);
    }
    
    
}
