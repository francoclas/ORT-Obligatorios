/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Excepciones.CocheraException;
import Excepciones.ParkingException;
import Excepciones.PropietarioException;
import Logica.Anomalias.Anomalia;
import Logica.Anomalias.ControlAnomalia;
import Logica.Parking.Cochera;
import Logica.Parking.CocheraTipoDisponible;
import Logica.Propietario.ControlPropietario;
import Logica.Parking.ControlParking;
import Logica.Parking.Estadia;
import Logica.Parking.Etiqueta;
import Logica.Parking.Parking;
import Logica.Parking.TipoPrecio;
import Logica.Propietario.Propietario;
import Logica.Propietario.TipoVehiculo;
import Logica.Propietario.Vehiculo;
import Utilidades.Observador;
import Utilidades.Precarga;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author frgomez
 */
public class Sistema {
    private static Sistema instancia = new Sistema();
    private ControladorUT CUT = new ControladorUT();
    private ControlParking CParking = new ControlParking();
    private ControlPropietario CProp = new ControlPropietario();
    private ControlAnomalia CAnomalia = new ControlAnomalia();
    
    public Sistema(){
    }
    
    public static Sistema getInstancia(){
        return instancia;
    }
    
    //Metodos UT
    public int CalcularUT(LocalDateTime FechaIni, LocalDateTime FechaDes){
       return CUT.CaclularUT(FechaIni,FechaDes);
       
    }
    //Metodos InicioSesion Registro
        
    
    //Metodos Parking
            public void AltaCochera(String Codigo, String Parking){
                
                CParking.CargarCochera(Codigo, Parking);
            }
    
            public void RegistrarEtiqueta(Etiqueta Etiqueta) {
                CParking.AltaEtiqueta(Etiqueta);
            }
            public void CargarEstadia(String Cochera,Estadia nueva){
                CParking.CargarEstadiaHistorico(Cochera, nueva);
            }
            public void CargarTipoPrecioParking(TipoVehiculo Tp){
                CParking.CargarTipoPrecio(Tp);
            }
            public void ActualizarTipoPrecioParking(String NombreParking, String Tipo, double Valor) throws ParkingException {
                CParking.ActualizarTipoPrecioParking(NombreParking,Tipo,Valor);
            }
        //Metodos Registrar Estacionado
            public void IngresoVehiculo(String CodCochera,String NumPat) throws ParkingException{
                CParking.RegistrarIngreso(NumPat, CodCochera);
            }


            public void SalidaVehiculo(String CodCochera,String NumPat) throws CocheraException{
                CParking.RegistrarSalida(CodCochera,NumPat);
            }
        //Buscar - Devolver datos
            public double DevolverFactorDemanda(String CodCohera){
                return CParking.DevolverFDParking(CodCohera);
            }
            
            public double DevolverPrecioTipo(String CodCochera,String Tipo) throws CocheraException{
                return CParking.DevolverPrecioTipo(Tipo,CodCochera);
            }
            
            public int ObtenerIngresos(String NomParking) {
                return CParking.ObtenerIngresosParking(NomParking);
            }

            public int ObtenerEgresos(String NomParking) {
                return CParking.ObtenerEgresosParking(NomParking);
            }

            public int ObtenerCapacidad(String NomParking) {
                return CParking.ObtenerCapacidadParking(NomParking);
            }
            public int ObtenerCocherasLibres(String NomParking){
                return CParking.DevolverCantidadLibres(NomParking);
            }
            public double ObtenerOcupacion(String NomParking) {
                return CParking.ObtenerOcupacionParking(NomParking);
            }
            public double ObtenerTotalParkings(){
                return CParking.ObtenerTotalParkings();
            }
            public int ObtenerTotalEstadias(){
                return CParking.ObtenerTotalEstadias();
            }
            
        //Busqueda Listados
                public List<CocheraTipoDisponible> ObtenerListaCocherasDisponibles(String NombreParking) {
                    return CParking.ListarCocherasDisponibles(NombreParking);
                }
                public List<TipoPrecio> ObtenerListaPreciosParking(String NombreParking) {
                    return CParking.ListarPreciosParking(NombreParking);
                }
                 public List<Parking> ListarParkings(){
                    return CParking.DevolverParkings();
                }
                public List<Cochera> ListarCocherasDeParking(String Parking){
                    return CParking.ListarCocherasParking(Parking);
                }
    //Metodo Anomalias
        
        public void GenerarAnomalia(String Propietario,String Codigo,String Cochera,String NumPat){
            CAnomalia.GenerarNuevaAnomalia(Propietario, Codigo, Cochera, NumPat);
        }
        
        public List<Anomalia> ListarAnomalias(){
            return CAnomalia.DevolverLista();
        }
    //Metodos Propietario
        public String ObtenerDueno(String NumPat) {
            return CProp.ObtenerNombreDueno(NumPat);
        }
        public void AltaPropietario(String CI,String Nombre,double Saldo){
            CProp.AltaPropietario(CI,Nombre,Saldo);
        }
        public List<Propietario> DevolverPropietarios(){
            return CProp.ListarPropietarios();
        }
        public void AsignarVehiculoPropietario(Vehiculo V, String CI) throws PropietarioException{
            CProp.AgregarVehiculoPropietario(V,CI);
        }
        //Metodos vehiculo
            
            public Vehiculo ObtenerVehiculo(String NumPat) throws PropietarioException{
                return CProp.ObtenerVehiculo(NumPat);
            }

            public String ObtenerTipoVehiculo(String NumPat) throws PropietarioException{
                return CProp.DevolverTipoVehiculo(NumPat);
            }

    //Metodos altas precarga
            public void AltaParking(Parking P){
                CParking.AgregarParking(P);
            }

            public Parking ObtenerParking(String Parking) {
                return CParking.ObtenerParking(Parking);
            }


                    //Se utiliza unicamente para precarga, o simulador          
            public Propietario ObtenerDuenoRandom() {
                return CProp.ObtenerDuenoRandom();
            }
            public List<Vehiculo> ObtenerTodoslosVehiculos(){
                return CProp.ObtenerVehiculos();
            }
            public List<Cochera> ObtenerTodaslasCocheras(){
                return CParking.ObtenerCocheras();
            }
            
            public void CargarCocheraUniversal(Cochera Aux) {
                CParking.AgregarCocheraUniversal(Aux);
            }
            //Devuelve el nombre del parking padre
            public String ObtenerNombreParking(String Cochera) {
                return CParking.DevolverNombreParking(Cochera);
            }   

            public void AgregarObservador(Observador Ob){
                CAnomalia.agregar(Ob);
            }

    

  

   

            

    
}
