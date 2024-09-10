/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC.Interfaces;

import Logica.Parking.TipoPrecio;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author frgomez
 */
public interface VistaCartelera {
    public void CargarNombreParking(String NombreParking,String Disponibilidad);
    public void ListarCocheras(DefaultTableModel Cocheras);
    public void ListarTipoPrecios(DefaultTableModel Tipos);
    public void Cerrar();
    public void mostrarMensajeExitoso(String mensaje);
    public void mostrarMensajeError(String mensaje);
}
