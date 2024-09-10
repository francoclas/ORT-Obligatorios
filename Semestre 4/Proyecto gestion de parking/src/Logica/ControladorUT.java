/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author frgomez
 */
public class ControladorUT {
 
    //Devuelve cantidad de Uts entre dos fechas
    public int CaclularUT(LocalDateTime FechaIni, LocalDateTime FechaFin){
        return (int) ChronoUnit.SECONDS.between(FechaIni, FechaFin);
    }
}
