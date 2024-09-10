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
public class MultaDiscapacitado implements MultaInterface{
   
    @Override
    public double calcularValor(Estadia estadia) {
        //No implementa nada en particular, es una tarifa fija.
        return 250;
    }
    
}
