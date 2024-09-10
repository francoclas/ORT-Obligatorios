/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Interfaces.MultaInterface;

/**
 *
 * @author franc
 */
public class MultaElectrico implements MultaInterface {

    @Override
    public double calcularValor(Estadia estadia) {
        //Se debe devolver el 50% de monto de la estadia
        return estadia.getValorEstadia() / 2;
    }
   
    
}
