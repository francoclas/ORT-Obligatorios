/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Logica.Sistema;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author frgomez
 */
public class FactorDemanda {
    private Sistema SisAux = Sistema.getInstancia();
    
    private String Dueno;    
    private double Cantidad = 1;
    private LocalDateTime UltimaActualizacion;
    private Tendencia TendenciaEstablecida;
   
    
    public FactorDemanda(String NomParking,Tendencia T){
        Dueno = NomParking;
        TendenciaEstablecida = T;
        UltimaActualizacion = LocalDateTime.now();
    }
    

    public double getCantidad() {
        return Cantidad;
    }
    
    public void setFecha(LocalDateTime Fecha){
        UltimaActualizacion = Fecha;
    }
    public void setCantidad(double Cantidad) {
        if (Cantidad < 0.25) {
            Cantidad = 0.25;
        }else if (Cantidad > 10) {
            Cantidad = 10;
        }else{
            this.Cantidad = Cantidad;
        }
        
    }
    public String DevolverEstadoTendencia(){
        return TendenciaEstablecida.getEstado();
    }
    
    public void ActualizarTendencia(LocalDateTime NuevaActualizacion){
        //Verifico los 3 casos,
        int ingresos = SisAux.ObtenerIngresos(Dueno);
        int egresos = SisAux.ObtenerEgresos(Dueno);
        int capacidad = SisAux.ObtenerCapacidad(Dueno);
        
        int diferencia = ingresos - egresos;
        //Tendencia UltimaTendencia = TendenciaEstablecida;
        if (diferencia <= (capacidad * 0.1)) {
            TendenciaEstablecida.setTendencia(new TendenciaEstable());
        }else if(diferencia > 0 && diferencia > (capacidad * 0.1)){
            TendenciaEstablecida.setTendencia(new TendenciaPositiva());
        }else if(diferencia < 0 && diferencia < (capacidad * 0.1)){ 
            TendenciaEstablecida.setTendencia(new TendenciaNegativa());
        }
        
        //Debo resetear la ultima fecha
        setCantidad(TendenciaEstablecida.Calculartendencia(
                SisAux.ObtenerOcupacion(Dueno),
                SisAux.CalcularUT(UltimaActualizacion, NuevaActualizacion),
                Cantidad));
        setFecha(NuevaActualizacion);
        
    }
}
