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
public class TendenciaPositiva implements TendenciaInterface{

    @Override
    public double CalcularTendencia(double Ocupacion, int CantUT,double FactorActual) {
        double Aux;
        if (Ocupacion > 66) {
            Aux = CantUT * 0.15;
        }else if (Ocupacion < 66 && Ocupacion > 33){
            Aux = CantUT * 0.1;
        }else{
            Aux = CantUT * 0.05;
        }
        
        return FactorActual + Aux;
    }

    @Override
    public String DevolverEstado() {
        return "Positiva";
               
    }

    
   
}
