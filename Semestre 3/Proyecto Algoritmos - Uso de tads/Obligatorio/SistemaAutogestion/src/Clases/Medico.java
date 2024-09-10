/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author frgomez
 */
public class Medico implements Comparable{
   int CodMed;
   String Nom;
   int Tel;
   int Especialidad;
    
   public boolean TieneConsulta(){
       return false;
   }

    @Override
    public int compareTo(Object o) {
        Medico M = (Medico) o;
        if(CodMed == M.CodMed){
            return 0;
        }else{
            if(CodMed > M.CodMed){
                return 1;
            }else{
                return -1;
            }
        }
        
    }
}
