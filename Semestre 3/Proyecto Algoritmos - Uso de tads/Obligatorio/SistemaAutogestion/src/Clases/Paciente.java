/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author frgomez
 */
public class Paciente implements Comparable{
    int CI;
    String Nombre;
    int Direccion;
    
    public boolean TieneConsultas(){
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Paciente P = (Paciente) o;
        if(CI == P.CI){
            return 0;
        }else{
            if(CI > P.CI){
                return 1;
            }else{
                return -1;
            }
        }
    }
   }
