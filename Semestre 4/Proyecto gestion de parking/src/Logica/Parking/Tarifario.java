/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Excepciones.CocheraException;
import Logica.Propietario.TipoVehiculo;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author frgomez
 */
public class Tarifario {
    private List<TipoPrecio> Precios = new ArrayList<>();
    
    public void ActualizarPrecio(double PN, String Tipo) throws CocheraException{
        TipoPrecio TP = ObtenerTipo(Tipo);
        TP.ActualizarPrecio(PN);
    }
    public double DevolverPreciodeTipo (String Tipo) throws CocheraException {
        TipoPrecio TP = ObtenerTipo(Tipo);
        return TP.getPrecio();
        
    }
    
    public List<TipoPrecio> ListarPrecios(){
        return Precios;
    }
    private TipoPrecio ObtenerTipo(String Tipo) throws CocheraException {
        TipoPrecio Aux = null;
        for(TipoPrecio TS : Precios){
            if(TS.Tipo.getNombre().equalsIgnoreCase(Tipo)){
                Aux = TS;
            }
        }
        if(Aux == null){ throw new CocheraException("No se encontro tipo en tarifario.");}
        return Aux;        
    }

    //Recibe una lista de todos los tiposprecio en el sistema, y carga los que no tenga, con valor por defecto de 0.1.
    public void AgregarNuevosTipos(List<TipoPrecio> Tipos) {      
        for (TipoPrecio Tipo : Tipos) {
            if (!Precios.contains(Tipo)) {
                Precios.add(Tipo);
            }
        }
    }
    
   
}
