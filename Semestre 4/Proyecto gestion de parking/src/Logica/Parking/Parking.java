/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Excepciones.CocheraException;
import Excepciones.ParkingException;
import Logica.Propietario.TipoVehiculo;
import Logica.Sistema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frgomez
 */
public class Parking {
    private Sistema SisAux = Sistema.getInstancia();
    private String Nombre;
    private String Direccion;
    private List<Cochera> Cocheras;
    private List<Estadia> HistoricoEstadia;
    private FactorDemanda FD;
    private Tarifario Tarifas;

    public Parking(String Nombre, String Direccion) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        Cocheras = new ArrayList<>();
        HistoricoEstadia = new ArrayList<>();
        FD = new FactorDemanda(Nombre,new Tendencia(new TendenciaEstable()));
        Tarifas = new Tarifario();
    }
    
    public void AgregarCochera(String C){
        Cochera Aux = new Cochera(C);
        Cocheras.add(Aux);
        SisAux.CargarCocheraUniversal(Aux);
    }
    
    public void RegistrarEntradaAuto(String CodCochera,String NumPat) throws ParkingException{
        //Cargo cochera
        Cochera Aux = ObtenerCochera(CodCochera);
        try {
            Aux.RegistrarEstadia(NumPat);
        } catch (CocheraException ex) {
            throw new ParkingException(ex.getMessage());
        }
        //Actualizo FactorDemanda
        FD.ActualizarTendencia(LocalDateTime.now());
        //
    
    }
    
    public String getNombre(){
        return Nombre;
            
        }

    //Getter
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    //Estadias
    public void CargaEstadia(Estadia estadia){
        HistoricoEstadia.add(estadia);
    }
    public void RegistrarSalidaAuto(String CodCochera, String NumPat) throws CocheraException {
        Cochera CAux = ObtenerCochera(CodCochera);
        CAux.FinalizarEstadia(NumPat);
    }
        
        
    //Contador
    public int ContarIngresos(){
        int Salida = 0 ;
        LocalDateTime Aux = LocalDateTime.now();       
        Aux = Aux.minusSeconds(10);
        for (Estadia estadia : HistoricoEstadia) {
            
            
            if (estadia.getFechaIngreso().isBefore(Aux)) {
                Salida++;
            }
        }
        return Salida;
    }
    public int ContarEgresos(){
        int Salida = 0 ;
        LocalDateTime Aux = LocalDateTime.now();       
        Aux = Aux.minusSeconds(10);
        for (Estadia estadia : HistoricoEstadia) {
            if (estadia.getFechaSalida() != null) {
                if (estadia.getFechaIngreso().isBefore(Aux)) {
                    Salida++;
                }
            }
            
        }
        return Salida;
    }
    
    //Metodos cochera
        public Cochera ObtenerCochera(String CodCochera){
            Cochera Salida = null;
            for (Cochera cochera : Cocheras) {
                if(cochera.getCodigo().equalsIgnoreCase(CodCochera)){
                    Salida = cochera;
                }
            }
            return Salida;      
        }
        //Agregar etiqueta
        public void AgregarEtiquetaCochera(String CodCochera, Etiqueta E) {
            Cochera Aux = ObtenerCochera(CodCochera);
            Aux.AgregarEtiqueta(E);
            
        }
    //Metodos Tarifa
        public double DevolverPrecioTipo(String Tipo) throws CocheraException{
            return Tarifas.DevolverPreciodeTipo(Tipo);
        }
        //Recibe una lista (normalmente del controlador de parking, para generar los tipos de vehiculos que falten, si ya existe  no se agrega.
        public void AgregarTipoPrecios(List<TipoPrecio> Tipos){
            Tarifas.AgregarNuevosTipos(Tipos);
        }
   
        public boolean TengoCochera(String CodCochera){
            boolean Salida = false;
            for (Cochera C : Cocheras) {
                if (C.getCodigo().equalsIgnoreCase(CodCochera)) {
                        Salida = true;
                }
            }
            return Salida;
        }
        
        public void ActualizarPrecio(String Tipo, double NuevoPrecio) throws ParkingException{
        try {
            Tarifas.ActualizarPrecio(NuevoPrecio, Tipo);
        } catch (CocheraException ex) {
            throw new ParkingException(ex.getMessage());
        }
        }
        
        public List<TipoPrecio> ListarPrecios(){
            return Tarifas.ListarPrecios();    
        }
        
        @Override
        public boolean equals(Object O){
            Parking Aux = (Parking) O;
            return this.Nombre.equals(Aux.Nombre);
        }

    //Metodos DTOS/Obtener datos
        public double DevolverFD() {
            return FD.getCantidad();
        }
        //Devuelve la cantidad de cocheras ocupadas.
        public int DevolverCantidadOcupadas(){
            int Salida = 0;
            for (Cochera C : Cocheras) {
                if (C.isEnUso()) {
                    Salida++;
                }
            }
            return Salida;
        }
        
        //Devuelve la cantidad de cocheras libres.
        public int DevolverCantidadLibres(){
            int Salida = 0;
            for (Cochera C : Cocheras) {
                if (!C.isEnUso()) {
                    Salida++;
                }
            }
            return Salida;
        }
        
        //Devuelve la tendencia actual del factor demanda.
        public String ObtenerTendencia(){
            return FD.DevolverEstadoTendencia();
        }
        
        //Devuelve el total de las estadias registradas en el parking
        public int ObtenerCantidadEstadias(){
            return HistoricoEstadia.size();
        }

        //Recorre todas las estadias, devolviendo unicamente el total de las multas acumulado.
        public double ObtenerTotalMultas(){
            double Salida = 0;
            for (Estadia E : HistoricoEstadia) {
                Salida += E.getTotalMultas();
            }
            return Salida;
        }
        //Recorre todas las estadias, devolviendo unicamente el total de las estadias.
        public double ObtenerTotal(){
            double Salida = 0;
            for (Estadia E : HistoricoEstadia) {
                Salida += E.getValorEstadia();
            }
            return Salida;
        }
        //Devuelve la capacidad total del parking
        public int DevolverCapacidad(){
        return Cocheras.size();
        }
        //Devuelve el porcentaje de ocupacion del parking.
        public double DevolverOcupacion(){
            double Salida = 0;

            for (Cochera cochera : Cocheras) {
                if (cochera.isEnUso()) {
                    Salida++;
                }
            }
            return (Salida / DevolverCapacidad()) * 100;
        }
        
        
        //Se utiliza en precarga para poder cargar las etiquetas.
        public List<Cochera> DevolverCocheras(){
            return Cocheras;
        }

        //Metodos ListaEspacioCochera
            /*Se genera una lista con las etiquetas que tiene el parking, sin setear el valor. Para poder calcular el valor a futuro
            */
            public List<CocheraTipoDisponible> ListarCantCocherasDisponibles(){
                List<CocheraTipoDisponible> Salida = GenerarListaEspacios();
                Salida = CalcularPreciosEtiquetas(Salida);
                return Salida;
            }
        
            private List<CocheraTipoDisponible> GenerarListaEspacios(){
                        List<CocheraTipoDisponible> Salida = new ArrayList<>();
                        for (Cochera cochera : Cocheras) {
                            for (Etiqueta E : cochera.DevolverEtiquetas()) {
                                CocheraTipoDisponible C = new CocheraTipoDisponible(E.getNombre());
                                if (!Salida.contains(C)) {
                                    Salida.add(C);
                                }
                            }
                            
                        }
                        return Salida;
                    }
            /*
                Se vuelve a iterar sobre la lista de etiquetas y se suma segun cada tipo registrado en las cocheras en GenerarListaEspacios()
            */
            private List<CocheraTipoDisponible> CalcularPreciosEtiquetas(List<CocheraTipoDisponible> Lista){
                for (CocheraTipoDisponible cocheraTipoDisponible : Lista) {
                    int Capacidad = 0;
                    for (Cochera cochera : Cocheras){
                        if (!cochera.isEnUso() && cochera.tengoEtiqueta(cocheraTipoDisponible.getNombreEtiqueta())) {
                            Capacidad ++;
                        }
                    }
                    cocheraTipoDisponible.setCantidad(Capacidad);
                    
                }
                return Lista;
            }

}
