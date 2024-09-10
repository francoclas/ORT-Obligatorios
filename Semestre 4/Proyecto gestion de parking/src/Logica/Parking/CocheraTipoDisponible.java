/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

/**
 *
 * @author frgomez
 */
public class CocheraTipoDisponible {
   /*Clase con la unica funcionalidad de poder contabilizar la cantidad disponible de cocheras segun su etiqueta.
     se utiliza de manera temporal, y no se guarda la informacion puesto que se utilizara con metodos para calcularla segun se necesite
    */
    
    public String NombreEtiqueta;
    public int CantidadDisponible;

    public CocheraTipoDisponible(String NombreEtiqueta) {
        this.NombreEtiqueta = NombreEtiqueta;
    }

    public String getNombreEtiqueta() {
        return NombreEtiqueta;
    }

    public int getCantidadDisponible() {
        return CantidadDisponible;
    }
    public void setCantidad(int Cant){
        CantidadDisponible = Cant;
    }
    
    @Override
    public boolean equals(Object O){
        CocheraTipoDisponible C = (CocheraTipoDisponible) O;
        return this.NombreEtiqueta.equalsIgnoreCase(C.NombreEtiqueta);
    }
           
}
