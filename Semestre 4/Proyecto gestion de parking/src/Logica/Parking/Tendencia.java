/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Interfaces.TendenciaInterface;

/**
 *
 * @author franc
 */
public class Tendencia {
    private TendenciaInterface tendencia;
    private String Estado;
    
    
    public String getEstado() {
        return Estado;
    }
    
    
    public Tendencia(TendenciaInterface Tendencia){
        tendencia = Tendencia;
        Estado = tendencia.DevolverEstado();
    }
    
    public void setTendencia(TendenciaInterface Tendencia){
        tendencia = Tendencia;
        Estado = tendencia.DevolverEstado();
    }
            
    public double Calculartendencia(double Ocupacion,int cantUT, double Factor){
       return tendencia.CalcularTendencia(Ocupacion,cantUT,Factor);
    }
}
