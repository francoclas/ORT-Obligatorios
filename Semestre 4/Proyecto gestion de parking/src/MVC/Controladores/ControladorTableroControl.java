/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Controladores;

import Logica.Anomalias.Anomalia;
import Logica.Parking.Parking;
import Logica.Sistema;
import MVC.DTO.parkingDTO;
import MVC.Interfaces.VistaTableroControl;
import Utilidades.EnumAnomalia;
import Utilidades.Observable;
import Utilidades.Observador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author frgomez
 */
public class ControladorTableroControl implements Observador{
    private String[] ColumnasParking = {"Parking","# Ocupadas","# Libres","Estado","Factor","Estadias","SubMultas","Total"};
    private String[] ColumnasAnomalia={"Fecha","Propietario","Codigo","Cochera"};
    DefaultTableModel ModeloParking = new DefaultTableModel(ColumnasParking, 0);
    DefaultTableModel ModeloAnomalia = new DefaultTableModel(ColumnasAnomalia,0);
    private String NombreParking;
    public VistaTableroControl Vista;
    
    
    private Sistema SisAux = Sistema.getInstancia();
    
    
    public ControladorTableroControl(VistaTableroControl nuevaVista){
        Vista = nuevaVista;
        SisAux.AgregarObservador(this);
    }
    public void CargaValores(){
        CargarAnomalias();
        CargarParkings();
        
        
    }
    
    //Listados
    public void ObtenerDatos(){
        //Obtengo valores
        int estadias = SisAux.ObtenerTotalEstadias();
        double total = SisAux.ObtenerTotalParkings();
        //Llamo a la vista con la info
        Vista.CargarLabel(estadias, total);
    }
    public void CargarAnomalias(){
        //Reseteo modelo
        ModeloAnomalia.setRowCount(0);
        //Genero modelo con anomalias nuevas
        List<Anomalia> Anoms = SisAux.ListarAnomalias();
        for (Anomalia Anom : Anoms) {
            ModeloAnomalia.addRow( new Object []{
                Anom.getFechaRegistro().toString(),
                Anom.getPropietario(),
                Anom.getCodigo(),
                Anom.getCochera()
            });
        }
        Vista.ListarAnomalias(ModeloAnomalia);
    }
    public void CargarParkings(){
        //Reseteo modelo
        ModeloParking.setRowCount(0);
        //Genero modelo con los parkings
        List<Parking> Parkings = SisAux.ListarParkings();
        for (Parking parking : Parkings) {
            ModeloParking.addRow(new Object []{
                parking.getNombre(),
                parking.DevolverCantidadOcupadas(),
                parking.DevolverCantidadLibres(),
                parking.ObtenerTendencia(),
                parking.DevolverFD(),
                parking.ObtenerCantidadEstadias(),
                parking.ObtenerTotalMultas(),
                parking.ObtenerTotal()
            });
            
        }
        Vista.ListarParking(ModeloParking);
    }
    
   //Se espera que al llegar notificacion del observable, si la vista esta con el Check activado, se actualice.
    @Override
    public void actualizar(Observable origen, Object evento) {
        if (Vista.EstaEsperando()) {
               if (evento.equals(EnumAnomalia.Alta)) {
                CargarAnomalias();     
            } 
        }
             
    }
    
  
    
    
}
