/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Propietario;

import Excepciones.PropietarioException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author frgomez
 */
public class ControlPropietario {
    private List<Propietario> Propietarios = new ArrayList<>();
    private List<Vehiculo> Vehiculos = new ArrayList<>();
    //Altas
        public void AltaPropietario(String CI, String Nombre, double Saldo) {
            Propietarios.add(new Propietario(CI,Nombre,Saldo));
        }
    
    
    
    //Metodos Propietario
        public void CobrarPropietario(String CI,double Tarifa) throws PropietarioException{
            Propietario Cobrado = BuscarPropietario(CI);
            Cobrado.CobrarEstadia(Tarifa);
        } 

        private Propietario BuscarPropietario(String CI) throws PropietarioException{
            Propietario Salida = null;
            Propietario Aux = new Propietario(CI);
            for (Propietario propietario : Propietarios) {
                if(propietario.equals(Aux)){
                    Salida = propietario;
                }  
            }
            if(Salida == null){
                throw new PropietarioException("No se encuentra propietario.");
            }
            return Salida;
        } 

        public String ObtenerNombreDueno(String NumPat) {
            String Salida = null;
            for (Propietario propietario : Propietarios) {
                if (propietario.TengoVehiculo(NumPat)) {
                    Salida = propietario.getNombreCompleto();
                }
            }
          return Salida;  
        }
          public List<Propietario> ListarPropietarios() {
              return Propietarios;
          }
    //Metodos Vehiculo
        //Devuelve el tipo de vehiculo
        public String DevolverTipoVehiculo(String NumPat) throws PropietarioException{
            Vehiculo Aux = ObtenerVehiculo(NumPat);
            return Aux.getTipo();
        }
        public Vehiculo ObtenerVehiculo(String NumPat) throws PropietarioException{
            Vehiculo Salida = null;
            for (Propietario propietario : Propietarios) {
                if (propietario.TengoVehiculo(NumPat)) {
                    Salida = propietario.BuscarVehiculo(NumPat);
                }
            }
            return Salida;
        }
        //Se agrega vehiculo al propietario y al Universaldevehiculos.
        public void AgregarVehiculoPropietario(Vehiculo V, String CI) throws PropietarioException {
            Propietario Prop = BuscarPropietario(CI);
            Prop.Agregarvehiculo(V);
            Vehiculos.add(V);

    }
        
//Listados
    public List<Vehiculo> ObtenerVehiculos(){
        return Vehiculos;
    }
    //Metodo unico para precarga, no tiene sentido con el funcionamiento del programa es para facilitar una precarga aleatoria
    public Propietario ObtenerDuenoRandom() {
        Random R = new Random();
        int indice = R.nextInt(Propietarios.size());
        return Propietarios.get(indice);
    }


  

   
}
