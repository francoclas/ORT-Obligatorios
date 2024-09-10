/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MVC.Interfaces;


import javax.swing.table.DefaultTableModel;
/**
 *
 * @author frgomez
 */
public interface VistaTableroControl {
    public void CargarLabel(int Estadias,double Total);
    public boolean EstaEsperando();
    public void ListarParking(DefaultTableModel Parkings);
    public void ListarAnomalias(DefaultTableModel Anomalias);
    public void VerPrecio(String NomParking);
    public void VerCartelera(String NomParking);
    public void mostrarMensajeExitoso(String mensaje);
    public void mostrarMensajeError(String mensaje);
}
