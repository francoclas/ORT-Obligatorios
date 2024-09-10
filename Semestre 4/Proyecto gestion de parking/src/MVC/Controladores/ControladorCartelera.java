/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.Controladores;

import Logica.Parking.CocheraTipoDisponible;
import Logica.Parking.TipoPrecio;
import Logica.Sistema;
import MVC.Interfaces.VistaCartelera;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author frgomez
 */
public class ControladorCartelera {
    private String NombreParking;
    private VistaCartelera Vista;
    private Sistema SisAux;
    
    //Tablas
        private String[] ColumnaCocheras = {"Cocheras","Disponibilidad"};
        private String[] ColumnaPrecios = {"Tipo de vehiculo", "Precio X UT"};
        private DefaultTableModel ModeloPrecios = new DefaultTableModel(ColumnaPrecios,0);
        private DefaultTableModel ModeloCocheras = new DefaultTableModel(ColumnaCocheras,0);
        //Modelos
    public ControladorCartelera(VistaCartelera vista,String Nombre){
        Vista = vista;
        NombreParking = Nombre;
        SisAux = Sistema.getInstancia();
    }
    
    
    //Carga lista de cocheras, la mapea
    public void CargarCocheras(){
        //Reseteo modelo
        ModeloCocheras.setRowCount(0);
        List<CocheraTipoDisponible> Cocheras = SisAux.ObtenerListaCocherasDisponibles(NombreParking);
        for (CocheraTipoDisponible Cochera : Cocheras) {
            ModeloCocheras.addRow(new Object[]{
                Cochera.getNombreEtiqueta(),
                Cochera.getCantidadDisponible()
            });
        }
        Vista.ListarCocheras(ModeloCocheras);
    }
    
    public void CargarTipoPrecio(){
        //Reseteo modelo
        ModeloPrecios.setRowCount(0);
        //Obtengo precios
        List<TipoPrecio> Precios = SisAux.ObtenerListaPreciosParking(NombreParking);
        //Mapeo al modelo
        for (TipoPrecio Precio : Precios) {
            ModeloPrecios.addRow(new Object[] {
                Precio.getTipo().getNombre(),
                Precio.getPrecio()
            });
        }
        //Llamo a la vista y le paso la nueva tabla
        Vista.ListarTipoPrecios(ModeloPrecios);
    }

    public void CargarValores() {
        Vista.CargarNombreParking(NombreParking, String.valueOf(SisAux.ObtenerCocherasLibres(NombreParking)));
    }
            
}
