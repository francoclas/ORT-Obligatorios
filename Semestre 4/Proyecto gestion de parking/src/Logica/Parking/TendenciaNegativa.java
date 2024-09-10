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
public class TendenciaNegativa implements TendenciaInterface{

    @Override
    public double CalcularTendencia(double Ocupacion,int CantUT,double FactorActual) {
        if (FactorActual > 1) {
            return 1;
        }else{
            //Si el valor fuese menor a 0.25, se setea a 0,25 en el SetValor de la clase Factor Demanda.
            return FactorActual - (CantUT * 0.05);
        }
    
    }

    @Override
    public String DevolverEstado() {
        return "Negativa";
    }

  
    
}
