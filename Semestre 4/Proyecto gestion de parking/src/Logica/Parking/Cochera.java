/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Excepciones.CocheraException;
import Excepciones.PropietarioException;
import Interfaces.MultaInterface;
import Logica.Propietario.Vehiculo;
import Logica.Sistema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuladortransito.Estacionable;

/**
 *
 * @author frgomez
 */
public class Cochera implements Estacionable{
    private Sistema SisAux = Sistema.getInstancia();
    private String Codigo;
    private boolean EnUso = false;
    private Estadia EstadiaActual;
    private List<Etiqueta> Etiquetas = new ArrayList<>();

    public Cochera(String Codigo,String Parking) {
        this.Codigo = Codigo;
    }
    public Cochera(String codigo){
        this.Codigo = codigo;
    }
    //Metodos
    public boolean AgregarEtiqueta (Etiqueta E){
        if (!Etiquetas.contains(E)){
            Etiquetas.add(E);
            return true;
        }else{
            return false;
        }
            
            
    }
    //Basicos
        public String getCodigo() {
            return Codigo;
        }

        public void setCodigo(String Codigo) {
            this.Codigo = Codigo;
        }


        public boolean isEnUso() {
            return EnUso;
        }

        public void setEnUso(boolean EnUso) {
            this.EnUso = EnUso;
        }
        public void setEstadiaActual(Estadia E){
            this.EstadiaActual = E;
        }
        public Estadia getEstadiaActual(){
            return EstadiaActual;
        }
        public List<Etiqueta> DevolverEtiquetas(){
            return Etiquetas;
        }
    //Metodos Estadia egreso y salida, incluye anomalias
        public void RegistrarEstadia(String NumPat) throws CocheraException{
           //Reviso si esta ocupada
            if (EnUso) {
                //Emito anomalia houdini
                SisAux.GenerarAnomalia(SisAux.ObtenerDueno(EstadiaActual.getNumPat()),"HOUDINI",Codigo,EstadiaActual.getNumPat());
                EstadiaActual.FinalizarEstadiaAnomala();          
            }
            //Genero nueva estadia, la seteo como actual y la cargo al historico del parking.
            Estadia Nueva = new Estadia(NumPat,Codigo);
            EnUso = true;
            try {
                //Verifico si tiene alguna multa y la cargo a la estadia
                Nueva.cargarMulta(GenerarMultas(Codigo,NumPat));
            } catch (PropietarioException ex) {
                throw new CocheraException(ex.getMessage());
            }
            EstadiaActual = Nueva;
            SisAux.CargarEstadia(Codigo, Nueva);

        }

        public void FinalizarEstadia(String NumPat) throws CocheraException{
            //Si no esta en uso, o no hay estadia, emito MISTERY.
            if (EnUso == false || EstadiaActual == null) {
                SisAux.GenerarAnomalia(SisAux.ObtenerDueno(NumPat), "MISTERY", Codigo, NumPat);
            }//Reviso si tiene o no la misma NumPat.
            else if(!EstadiaActual.getNumPat().equals(NumPat)){
                //Reviso la patente, si es distinta de la ingresada por firma, emito dos anomalias, transportador 1 y 2
                //Transportador 1
                SisAux.GenerarAnomalia(SisAux.ObtenerDueno(EstadiaActual.getNumPat()),"TRANSPORTADOR1",Codigo,EstadiaActual.getNumPat());
                //Transportador 2 
                SisAux.GenerarAnomalia(SisAux.ObtenerDueno(NumPat),"TRANSPORTADOR2",Codigo,NumPat);   
            }//Si esta todo bien, finalizo la estadia y emito pago.
            else{
                try {
                    //Emito Pago
                    EstadiaActual.FinalizarEstadia();
                } catch (PropietarioException ex) {
                    throw new CocheraException(ex.getMessage());
                }
                //Libero Cochera y limpio Estadia Actual
                setEnUso(false);
                EstadiaActual = null;
            }

        }
    
            
        public void LiberarCochera() throws CocheraException{
            if(!EnUso || EstadiaActual != null){
                throw new CocheraException("");
            }else{
                //Calculo el precio de la estadia y emito el pago al propietario
                //Libero la cochera
                setEnUso(false);
                setEstadiaActual(null);
            }
        }

    //Metodo multa
    
    /*Verifica si cumple condicion para asignar multa, genera una lista con las multas para cargar en la estadia
    y al momento de finalizar si es necesario calcular el total de multas. 
    Recibe Lista de etiquetas del vehiculo, para poder comparar. */
    
        private List<MultaInterface> GenerarMultas(String Cochera,String NumPat) throws PropietarioException{
            List<MultaInterface> Salida = new ArrayList<>();
            //Se hace para los casos particulares planteados por la letra, se podria plantear para mas escalabilidad que cada etiqueta pueda emitir una multa de su tipo
            //Obtengo vehiculo
            Vehiculo Aux = SisAux.ObtenerVehiculo(NumPat);
            for (Etiqueta E : Etiquetas) {
               String Tipo = E.getNombre();
               switch (Tipo) {
                 case "Discapacitado":
                     if (!Aux.esDiscapacitado()) {
                         Salida.add(new MultaDiscapacitado());
                     }
                 break;
                 case "Empleado":
                     if (!Aux.esEmpleado()) {
                         Salida.add(new MultaEmpleado());
                     }
                 break;
                 case "Electrico":
                     if (!Aux.esElectrico()) {
                         Salida.add(new MultaElectrico());
                     }
                 break;

                 }
            }
                return Salida;
            }
    //Etiquetas
        //Recibe una etiqueta y devuelve si la posee o no, recorre la lista de etiquetas y responde.
        public boolean tengoEtiqueta(String nombreEtiqueta) {
            return Etiquetas.contains(new Etiqueta(nombreEtiqueta));
        }
    //METODOS SIMULADOR
    @Override
    public boolean equals(Object obj) {
        Cochera Aux = (Cochera) obj;
        return Codigo.equals(Aux.getCodigo());

    }

    @Override
    public boolean esDiscapacitado() {
     return RevisarEtiqueta("Discapacitado");
    }

    @Override
    public boolean esElectrico() {
        return RevisarEtiqueta("Electrico");
    }

    @Override
    public boolean esEmpleado() {
        return RevisarEtiqueta("Empleado");
    }
    
    private boolean RevisarEtiqueta (String Filtro){
        boolean Salida = false;
        for (Etiqueta Etiqueta1 : Etiquetas) {
            if(Etiqueta1.getNombre().equals(Filtro)){
                Salida = true;
            }
        }
        return Salida;
    }

    
    
}
