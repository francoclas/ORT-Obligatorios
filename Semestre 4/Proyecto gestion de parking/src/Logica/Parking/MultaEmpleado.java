/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Interfaces.MultaInterface;
import Logica.Sistema;

/**
 *
 * @author frgomez
 */
public class MultaEmpleado implements MultaInterface{
    private static Sistema instancia = new Sistema();
    @Override
    public double calcularValor(Estadia estadia) {
        //Se aumenta 1 por cada 10 ut
        int salida = 1;
        int CantUT = instancia.CalcularUT(estadia.getFechaIngreso(), estadia.getFechaSalida());
        //Solo si pasa la primera parte empieza a sumar
        if (CantUT >= 10) {
           salida = CantUT/10; 
        }
        return salida;
    }
    
}
