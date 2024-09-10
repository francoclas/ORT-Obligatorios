/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC.Interfaces;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author frgomez
 */
public interface VistaListaPrecios {
    public void ListarTipoPrecio(DefaultTableModel Tipos);
    public void ActualizarPrecio(String Tipo,double ValorNuevo);
    public void mostrarMensajeExitoso(String mensaje);
    public void mostrarMensajeError(String mensaje);
}
