/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Anomalias;

import Logica.Sistema;
import Utilidades.EnumAnomalia;
import Utilidades.Observable;
import Utilidades.Observador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franc
 */
public class ControlAnomalia extends Observable{
    private Sistema SisAux = Sistema.getInstancia();
    public List<Anomalia> Anomalias  = new ArrayList<>();
    public List<Observador> Observadores = new ArrayList<>();
    public ControlAnomalia(){
        
    }
 
    public void GenerarNuevaAnomalia(String Propietario,String Codigo,String Cochera,String NumPat){
        
        Anomalia Aux = new Anomalia(Propietario,Codigo,Cochera,NumPat);
        Anomalias.add(Aux);       
        //Luego de generada la anomalia, aviso en observable
        avisar(EnumAnomalia.Alta); 
        
        
    }
    public List<Anomalia> DevolverLista(){
        return Anomalias;
    }
    
    
}
