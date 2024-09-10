/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Excepciones.PropietarioException;
import Logica.Parking.Cochera;
import Logica.Parking.Etiqueta;
import Logica.Parking.Parking;
import Logica.Propietario.Propietario;
import Logica.Propietario.TipoVehiculo;
import Logica.Propietario.Vehiculo;
import Logica.Sistema;
import java.util.List;
import java.util.Random;

/**
 *
 * @author franc
 */
public class Precarga {
    private Sistema sis = Sistema.getInstancia();
    
    //Seteo las etiquetas aca, para poder cargarlas en el sistema, las cocheras y los vehiculos
    Etiqueta Discapacitado = new Etiqueta("Discapacitado");
    Etiqueta Electrico = new Etiqueta("Electrico");
    Etiqueta Empleado = new Etiqueta("Empleado");
    
    //Se setea igual que etiquetas, para facilitar las altas de vehiculos, se carga en el control parking tambien
    TipoVehiculo Motocicleta = new TipoVehiculo("Motocicleta");
    TipoVehiculo Standard = new TipoVehiculo("Standard");
    TipoVehiculo Carga = new TipoVehiculo("Carga");
    TipoVehiculo Pasajeros = new TipoVehiculo("Pasajeros");
    
    public void GenerarPrecarga() throws PropietarioException{
        PrecargaParkings();
        PrecargaCocheras();
        PrecargaPropietarios();
        PrecargaEtiquetas();
        PrecargaTipos();
        PrecargaVehiculos();
    }
    //Precarga Parking
     
    private void PrecargaParkings(){
       sis.AltaParking(new Parking("Altus","Paysandu 795"));
       sis.AltaParking(new Parking("Liurnia","Soriano Esq 2392"));
       sis.AltaParking(new Parking("Caelid","Canelones 902"));
    }
    //Precarga Cochera
    private void PrecargaCocheras(){
        for (int i = 0; i < 4; i++) {
            sis.AltaCochera(GeneradorCodigos(4),"Altus");
            sis.AltaCochera(GeneradorCodigos(4),"Liurnia");
            sis.AltaCochera(GeneradorCodigos(4),"Caelid");
                    
        }

        
    }
    //Precarga Propietarios
    private void PrecargaPropietarios(){
       sis.AltaPropietario("12345678", "Juan Perez", 50.0);
        sis.AltaPropietario("23456789", "Maria Gomez", 75.5);
        sis.AltaPropietario("34567890", "Carlos Sanchez", -5.0);
        sis.AltaPropietario("45678901", "Ana Lopez", 23.3);
        sis.AltaPropietario("56789012", "Luis Martinez", 100.0);
        sis.AltaPropietario("67890123", "Elena Fernandez", 0.0);
        sis.AltaPropietario("78901234", "Pedro Gonzalez", 10.5);
        sis.AltaPropietario("89012345", "Sofia Ramirez", 80.0);
        sis.AltaPropietario("90123456", "Miguel Torres", 60.8);
        sis.AltaPropietario("01234567", "Laura Diaz", -10.0);
        sis.AltaPropietario("12349876", "Jorge Alvarez", 35.2);
        sis.AltaPropietario("23450987", "Andrea Ruiz", 45.7);
        sis.AltaPropietario("34560198", "Sergio Castro", 25.1);
        sis.AltaPropietario("45671209", "Natalia Rojas", 12.3);
        sis.AltaPropietario("56782310", "Martin Jimenez", 95.6);
        sis.AltaPropietario("67893421", "Valeria Herrera", -3.3);
        sis.AltaPropietario("78904532", "Gustavo Vargas", 55.0);
        sis.AltaPropietario("89015643", "Claudia Ortiz", 70.0);
        sis.AltaPropietario("90126754", "Alberto Mendoza", -8.2);
        sis.AltaPropietario("01237865", "Patricia Morales", 85.9);
        sis.AltaPropietario("12348976", "Diego Romero", 5.5);
        sis.AltaPropietario("23459087", "Lorena Medina", 40.7);
        sis.AltaPropietario("34560198", "Ignacio Vega", 30.2);
        sis.AltaPropietario("45671209", "Paula Herrera", 62.0);
        sis.AltaPropietario("56782310", "Fernando Campos", -7.5);
        sis.AltaPropietario("67893421", "Rosa Paredes", 47.8);
        sis.AltaPropietario("78904532", "Emilio Guerrero", 20.0);
        sis.AltaPropietario("89015643", "Gabriela Castro", -2.1);
        sis.AltaPropietario("90126754", "Daniel Vargas", 99.9);
        sis.AltaPropietario("01237865", "Isabel Luna", 53.3);
        sis.AltaPropietario("12348976", "Oscar Flores", -9.0);
        sis.AltaPropietario("23459087", "Julieta Marquez", 18.4);
        sis.AltaPropietario("34560198", "Roberto Soto", 3.7);
        sis.AltaPropietario("45671209", "Silvia Gutierrez", 22.5);
        sis.AltaPropietario("56782310", "Francisco Mendez", 65.4);
        sis.AltaPropietario("67893421", "Marta Escobar", 88.1);
        sis.AltaPropietario("78904532", "Raul Suarez", -1.8);
        sis.AltaPropietario("89015643", "Teresa Pacheco", 42.6);
        sis.AltaPropietario("90126754", "Alejandro Ruiz", 28.0);
        sis.AltaPropietario("01237865", "Gloria Avila", -6.5);
        sis.AltaPropietario("12348976", "Cesar Peña", 50.2);
        sis.AltaPropietario("23459087", "Angela Ortiz", 76.8);
        sis.AltaPropietario("34560198", "Adrian Campos", 15.7);
        sis.AltaPropietario("45671209", "Fernanda Cabrera", 33.9);
        sis.AltaPropietario("56782310", "Ezequiel Navarro", 91.0);
        sis.AltaPropietario("67893421", "Victoria Molina", -4.2);
        sis.AltaPropietario("78904532", "Bruno Rivera", 68.5);
        sis.AltaPropietario("89015643", "Miriam Soto", 21.0);
        sis.AltaPropietario("90126754", "Horacio Nunez", 8.9);
        sis.AltaPropietario("01237865", "Sara Delgado", 37.5);
        sis.AltaPropietario("12348976", "Hugo Roldan", 57.1);
    }
    //Precarga Etiquetas
    private void PrecargaEtiquetas(){
        
        //Se precargan las etiquetas al sistema
       sis.RegistrarEtiqueta(Empleado);
       sis.RegistrarEtiqueta(Discapacitado);
       sis.RegistrarEtiqueta(Electrico);
       //Se precargan las etiquetas a las cocheras, de manera aleatoria. Se hace mas de una vez para que tengan mas de una etiqueta
       //si, se agrega una repetida, no se agregar a la cochera porque lo verifica, revisar Cochera AgregarEtiqueta (Etiqueta E)
       CargarEtiquetas(sis.ListarCocherasDeParking("Altus"));
       CargarEtiquetas(sis.ListarCocherasDeParking("Liurnia"));
       CargarEtiquetas(sis.ListarCocherasDeParking("Caelid"));
       CargarEtiquetas(sis.ListarCocherasDeParking("Altus"));
       CargarEtiquetas(sis.ListarCocherasDeParking("Liurnia"));
       CargarEtiquetas(sis.ListarCocherasDeParking("Caelid")); 
    }
    
    private void CargarEtiquetas(List<Cochera> L){
        
        for (Cochera C : L) {
            C.AgregarEtiqueta(EtiquetaRandom());
            
        }
    }
    //Precarga Tipos, se cargan antes de los parkings
    private void PrecargaTipos(){
        //Cargar tipos a sistema
        sis.CargarTipoPrecioParking(Motocicleta);
        sis.CargarTipoPrecioParking(Standard);
        sis.CargarTipoPrecioParking(Carga);
        sis.CargarTipoPrecioParking(Pasajeros);

                
    }
    //Precarga vehiculos
    /*Constructor vehiculo
        public Vehiculo(String NumPat, Propietario propietario, TipoVehiculo Tipo);
    */
    private void PrecargaVehiculos() throws PropietarioException{
        
        //Se precarga almenos 1 vehiculo para cada propietario
            for (Propietario P : sis.DevolverPropietarios()) {
                Vehiculo Aux = new Vehiculo(GeneradorCodigos(8),P,TipoAleatorio());
                Aux.AgregarEtiqueta(EtiquetaRandom());
                sis.AsignarVehiculoPropietario(Aux, P.getCI());
        }
        //Se carga el resto para cumplir con la letra, al azar se asignara el vehiculo a un propietario, no se controla la cantidad de vehiculosq ue puede tener un propietario
        for (int i = 0; i < 250; i++) {
            Propietario P = sis.ObtenerDuenoRandom();
            Vehiculo Aux = new Vehiculo(GeneradorCodigos(8),P,TipoAleatorio());
            Aux.AgregarEtiqueta(EtiquetaRandom());
            sis.AsignarVehiculoPropietario(Aux, P.getCI());
        }
        
        
    }
    
    /* Se utiliza para poder generar vehiculos o cocheras y facilitar el proceso, en cada caso de ejecucion, 
    el programa tendra vehiculos y cocheras con distintos valores porque es aleatorio
    
    
    */
    private String GeneradorCodigos(int Cant){
        String Cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random randomizador = new Random();
        StringBuilder Salida = new StringBuilder(Cant);
        for (int i = 0; i < Cant; i++) {
            int index = randomizador.nextInt(Cadena.length());
            Salida.append(Cadena.charAt(index));
        }
        return Salida.toString();
    }
    private TipoVehiculo TipoAleatorio(){
        int Aux = (int) Math.floor(Math.random() * 4) + 1;
        switch (Aux){
                case 1:
                    return Motocicleta;
                case 2:
                    return Standard;
                case 3:
                    return Carga;
                case 4:
                    return Pasajeros;
            }
        return null;
    }

    private Etiqueta EtiquetaRandom() {
       int Aux = (int) Math.floor(Math.random() * 3) + 1;
            switch (Aux){
                case 1:
                    return Electrico;

                case 2:
                    return Discapacitado;

                case 3:
                    return Empleado;

            }
            return null;
    }
 
   
}
