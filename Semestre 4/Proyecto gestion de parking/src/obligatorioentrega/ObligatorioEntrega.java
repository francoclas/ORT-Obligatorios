/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorioentrega;

import Excepciones.CocheraException;
import Excepciones.ParkingException;
import Excepciones.PropietarioException;
import Logica.Parking.Cochera;
import Logica.Propietario.Vehiculo;
import Logica.Sistema;
import MVC.UI.CarteleraPreciosCochera;
import MVC.UI.TablerodeControl;
import Utilidades.Precarga;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuladortransito.ConfiguracionException;
import simuladortransito.Estacionable;
import simuladortransito.FlujoEgreso;
import simuladortransito.FlujoIngreso;
import simuladortransito.Periodo;
import simuladortransito.Sensor;
import simuladortransito.SimuladorTransito;
import simuladortransito.Transitable;

/**
 *
 * @author frgomez
 */
public class ObligatorioEntrega {
    private static SimuladorTransito simulador;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropietarioException, ConfiguracionException {
        Sistema Sis = Sistema.getInstancia();
        Precarga P = new Precarga();
        P.GenerarPrecarga();

        simulador = SimuladorTransito.getInstancia();
        //Simulador
            
            List<Transitable> Vehiculos = MapearTransitable(Sis.ObtenerTodoslosVehiculos());
            List<Estacionable> Cocheras = MapearEstacionable(Sis.ObtenerTodaslasCocheras());
            simulador.addEstacionables(Cocheras);
            simulador.addTransitables(Vehiculos);
            simulador.programar(new FlujoIngreso("Ingreso prueba",new Periodo(0,6),5));
            simulador.programar(new FlujoEgreso("Salida prueba",new Periodo(7,10),3));
            simulador.iniciar(new Sensor() {
            @Override
            public void ingreso(Transitable transitable, Estacionable estacionable) {
                Vehiculo V = (Vehiculo) transitable;
                Cochera C = (Cochera) estacionable;
                try {
                    
                    Sis.IngresoVehiculo(C.getCodigo(), V.getPatente());
                } catch (ParkingException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            @Override
            public void egreso(Transitable transitable, Estacionable estacionable) {
                Vehiculo V = (Vehiculo) transitable;
                Cochera C = (Cochera) estacionable;
                try {
                    Sis.SalidaVehiculo(C.getCodigo(), V.getPatente());
                } catch (CocheraException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        //Interfaz grafica    
            TablerodeControl Tablero = new TablerodeControl();
            Tablero.setVisible(true);
            CarteleraPreciosCochera C = new CarteleraPreciosCochera("Altus");
            C.setVisible(true);
    }

        //Mapeo de cocheras y vehiculos a interface de simulador.
        private static List<Transitable> MapearTransitable(List<Vehiculo> Lista) {
            List<Transitable> Salida = new ArrayList<>();
            for(Vehiculo V : Lista ){
                Salida.add(V);
            }
            return Salida;
        }
        private static List<Estacionable> MapearEstacionable(List<Cochera> Lista) {
            List<Estacionable> Salida = new ArrayList<>();
            for(Cochera C : Lista ){
                Salida.add(C);
            }
            return Salida;
        }
    
}
