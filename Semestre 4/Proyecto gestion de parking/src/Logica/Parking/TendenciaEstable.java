/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Interfaces.TendenciaInterface;

/**
 *
 * @author frgomez
 */
public class TendenciaEstable implements TendenciaInterface {

    public TendenciaEstable(){
        
    }
    @Override
    
    public double CalcularTendencia(double Ocupacion,int CantUT,double FactorActual) {
        double resta = 0.01 * CantUT;
        return FactorActual - resta;
    }
    
    @Override
    public String DevolverEstado(){
        return "Estable";
    }
}
