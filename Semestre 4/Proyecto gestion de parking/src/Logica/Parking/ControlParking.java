/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Excepciones.CocheraException;
import Excepciones.ParkingException;
import Logica.Sistema;
import Logica.Propietario.TipoVehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frgomez
 */
public class ControlParking {
    private Sistema SisAux = Sistema.getInstancia();
    
    public  List<Parking> Parkings = new ArrayList<>();
    //Se utiliza con el motivo de emplear el simulador
    public List<Cochera> CocherasUniversal = new ArrayList();
    public List<Etiqueta> Etiquetas = new ArrayList<>();
    public List<TipoPrecio> Precios = new ArrayList<>();
    public List<TipoVehiculo> Tipos = new ArrayList<>();
    
    //Metodos Parking
        //Carga parking
            public void AgregarParking(Parking P) {
                Parkings.add(P);
            }
    
        //Alta cochera - Se carga una nueva cochera a la lista del parking. Y al universal de cocheras para simulador.
        public void CargarCochera(String Codigo,String NombreParking){
            Parking Aux = ObtenerParking(NombreParking);
            Aux.AgregarCochera(Codigo); 
        }
         public void AgregarCocheraUniversal(Cochera Aux) {
        //Para cargar la ultima cochera generada. Se llama desde Parking.AgregarCochera();

             CocherasUniversal.add(Aux);
        }
        //Alta TipoPrecio
        public void AltaTipoPrecio(TipoPrecio P){
            if (!Precios.contains(P)) {
                Precios.add(P);
            }
        }
        //Modificar TipoPrecio
        public void ActualizarTipoPrecioParking(String NombreParking, String Tipo, double Valor) throws ParkingException {
            Parking Aux = ObtenerParking(NombreParking);
            Aux.ActualizarPrecio(Tipo, Valor);
        }
       
        //Alta Etiqueta
        public void AltaEtiqueta(Etiqueta E){
            if (!Etiquetas.contains(E)) {
                Etiquetas.add(E);
            }
        }
        public List<Cochera> ListarCocherasParking(String NombreParking){
            //Listar cocheras de parking
            Parking Aux = ObtenerParking(NombreParking);
            return Aux.DevolverCocheras();
        }
        public void CargarEtiquetaCochera(String CodCochera,Etiqueta E){
        //Cargar etiqueta a cochera

            Parking Aux = BuscarParkingPorCochera(CodCochera);
            Aux.AgregarEtiquetaCochera(CodCochera,E);
        }
        
        public void CargarTipoPrecio(TipoVehiculo Tp) {
           //Recibe un nuevo tipo de precio se guarda en la lista de tipos, lo setea por defecto en 0.1( y luego lo agrega a todos los demas parkings.
            Tipos.add(Tp);
            TipoPrecio Aux;
            if (Tp.getNombre().equals("Motocicleta")) {
                Aux = new TipoPrecio(Tp, 0.05);
            }else{
                Aux = new TipoPrecio(Tp, 0.1);
            }
            Precios.add(Aux);
            CargarPreciosParking();
            
        }

        private void CargarPreciosParking(){
        //Al recibir una nuevo tipo de precio en el control parking, se pide a todos los parkings que agreguen el nuevo tipo.
            for (Parking Aux : Parkings) {
                  Aux.AgregarTipoPrecios(Precios);   
            }        
        }
    //Metodos relacionados estadia
        public double CalcularPromedio(String Tipo) throws CocheraException {
            double Salida = 0;
            for (Parking parking : Parkings) {
                Salida += parking.DevolverPrecioTipo(Tipo);
            }
            return Salida / Parkings.size();
        }
        public void CargarEstadiaHistorico(String Cochera,Estadia Nueva){
            Parking Aux = BuscarParkingPorCochera(Cochera);
            Aux.CargaEstadia(Nueva);
        }
        public int ObtenerTotalEstadias(){
            int Salida = 0;
            for (Parking parking : Parkings) {
                Salida += parking.ObtenerCantidadEstadias();
            }
            return Salida;
        }
    //Metodos ingresos y salidas
        public void RegistrarIngreso(String NumPat,String Cochera) throws ParkingException{
            Parking Aux = BuscarParkingPorCochera(Cochera);
            Aux.RegistrarEntradaAuto(Cochera, NumPat);
        };
         public void RegistrarSalida(String Cochera, String NumPat) throws CocheraException {
             Parking Aux = BuscarParkingPorCochera(Cochera);
             Aux.RegistrarSalidaAuto(Cochera, NumPat);
         }

    
    //Metodos busquedas parking
        public Parking ObtenerParking(String Nombre){
            Parking Aux = null;
            for (Parking parking : Parkings) {
                if(parking.getNombre().equalsIgnoreCase(Nombre)){
                    Aux = parking;
                }
            }
            return Aux;
        }
        public Parking BuscarParkingPorCochera(String Cochera){
            Parking Aux = null;
            for (Parking parking : Parkings) {
                if (parking.TengoCochera(Cochera)) {
                    Aux = parking;
                }
            }
            return Aux;
        }
        //
    //Buscar informacion
        
        //Valores o informativos
            public String DevolverNombreParking(String Cochera) {
                //Devuelve el nombre del parking dueño de la cochera.
                Parking Aux = BuscarParkingPorCochera(Cochera);
                return Aux.getNombre();
            }
            public double ObtenerOcupacionParking(String NomParking) {
                //Devuelve la ocupacion del parking.
                Parking Aux = ObtenerParking(NomParking);
                return Aux.DevolverOcupacion();
            }

            public int ObtenerCapacidadParking(String NomParking) {
                //Devuelve la capacidad total del parking.
                Parking Aux = ObtenerParking(NomParking);
                return Aux.DevolverCapacidad();
            }

            public int ObtenerEgresosParking(String NomParking) {
                //Devuelve la cantidad de egresos totales del parking
                Parking Aux = ObtenerParking(NomParking);
                return Aux.ContarEgresos();
            }

            public int ObtenerIngresosParking(String NomParking) {
                //Devuelve la cantidad de ingresos totales del parking
                Parking Aux = ObtenerParking(NomParking);
                return Aux.ContarIngresos();
            }
            public double ObtenerTotalParkings(){
                //Devuelve la cantidad total recaudada por todos los parkings.
                double Salida = 0;
                for (Parking parking : Parkings) {
                    Salida += parking.ObtenerTotal();
                }
                return Salida;
            }
            public double DevolverFDParking(String Cochera){
            //Devuelve el precio del factor demanda, se hace atraves de la cochera
                Parking Aux = BuscarParkingPorCochera(Cochera);
                return Aux.DevolverFD();
            }
            public double DevolverPrecioTipo(String Tipo,String Cochera) throws CocheraException{
            //Devuelve el precio del Tipo de vehiculo, se hace atraves de la cochera para encontrar el parking y el tipo.
                Parking Aux = BuscarParkingPorCochera(Cochera);
                return Aux.DevolverPrecioTipo(Tipo);
            }
            public int DevolverCantidadLibres(String NombreParking){
                //Devuelve cantidad de cocheras libres.
                Parking Aux = ObtenerParking(NombreParking);
                return Aux.DevolverCantidadLibres();
            }
        //Devolver listas
            public List<Cochera> ObtenerCocheras() {
                return CocherasUniversal;
            }
            
            public List<TipoPrecio> ListarPreciosParking(String NombreParking) {
                Parking Aux = ObtenerParking(NombreParking);
                return Aux.ListarPrecios();
            }
            public List<Parking> DevolverParkings() {
                return Parkings;
            }
            //Metodos CocheraTipoDisponible
            public List<CocheraTipoDisponible> ListarCocherasDisponibles(String NombreParking) {
                Parking Aux = ObtenerParking(NombreParking);
                return Aux.ListarCantCocherasDisponibles();
            }

         
    
            

            

            

    


       



        }   
