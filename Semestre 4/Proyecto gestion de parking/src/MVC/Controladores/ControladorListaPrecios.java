/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Controladores;

import Excepciones.ParkingException;
import Logica.Parking.TipoPrecio;
import Logica.Sistema;
import MVC.Interfaces.VistaListaPrecios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author franc
 */
public class ControladorListaPrecios {
    private String NombreParking;
    private VistaListaPrecios Vista;
    private Sistema SisAux;
    //Tablas
    private String[] ColumnaPrecios = {"Tipo de vehiculo", "Precio X UT"};
    private DefaultTableModel ModeloPrecios = new DefaultTableModel(ColumnaPrecios,0);
    
    public ControladorListaPrecios(VistaListaPrecios vista,String Nombre){
        SisAux = Sistema.getInstancia();
        NombreParking = Nombre;
        Vista = vista;
    }
    
    
    public void CargarPrecios(){
        //Obtengo lista a cargar
        List<TipoPrecio> Precios = SisAux.ObtenerListaPreciosParking(NombreParking);
        //Reseteo modelo y cargo valores
        ModeloPrecios.setRowCount(0);
        for (TipoPrecio Precio : Precios) {
            ModeloPrecios.addRow(new Object[]{
                Precio.getTipo().getNombre(),
                Precio.getPrecio()
            });
        }
        //Paso el modelo cargado a la vista.
        Vista.ListarTipoPrecio(ModeloPrecios);
    }
    public void ActualizarPrecio(String Tipo,double Valor){
        try {
            SisAux.ActualizarTipoPrecioParking(NombreParking,Tipo,Valor);
        } catch (ParkingException ex) {
            Vista.mostrarMensajeError(ex.getMessage());
        }
        CargarPrecios();
    }
}
