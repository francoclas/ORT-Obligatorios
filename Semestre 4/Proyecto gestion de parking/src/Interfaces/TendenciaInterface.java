/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author frgomez
 */
public interface TendenciaInterface {
    
    public double CalcularTendencia(double Ocupacion, int CantUT, double FactorActual);
    public String DevolverEstado();
}
