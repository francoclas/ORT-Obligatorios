/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC.DTO;

/**
 *
 * @author franc
 */

//Se utiliza para mostrar informacion adicional en la ventana TableroControl
public class parkingDTO {
    
    public String NombreParking;
    public int CantidadOcupadas;
    public int CantidadLIbres;
    public String Estado;
    public double FactorDemanda;
    public int Estadias;
    public double TotalMulta;
    public double TotalParking;

    public parkingDTO(String NombreParking, int CantidadOcupadas, int CantidadLIbres, String Estado, double FactorDemanda, int Estadias, double TotalMulta, double TotalParking) {
        this.NombreParking = NombreParking;
        this.CantidadOcupadas = CantidadOcupadas;
        this.CantidadLIbres = CantidadLIbres;
        this.Estado = Estado;
        this.FactorDemanda = FactorDemanda;
        this.Estadias = Estadias;
        this.TotalMulta = TotalMulta;
        this.TotalParking = TotalParking;
    }

    public String getNombreParking() {
        return NombreParking;
    }

    public void setNombreParking(String NombreParking) {
        this.NombreParking = NombreParking;
    }

    public int getCantidadOcupadas() {
        return CantidadOcupadas;
    }

    public void setCantidadOcupadas(int CantidadOcupadas) {
        this.CantidadOcupadas = CantidadOcupadas;
    }

    public int getCantidadLIbres() {
        return CantidadLIbres;
    }

    public void setCantidadLIbres(int CantidadLIbres) {
        this.CantidadLIbres = CantidadLIbres;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public double getFactorDemanda() {
        return FactorDemanda;
    }

    public void setFactorDemanda(double FactorDemanda) {
        this.FactorDemanda = FactorDemanda;
    }

    public int getEstadias() {
        return Estadias;
    }

    public void setEstadias(int Estadias) {
        this.Estadias = Estadias;
    }

    public double getTotalMulta() {
        return TotalMulta;
    }

    public void setTotalMulta(double TotalMulta) {
        this.TotalMulta = TotalMulta;
    }

    public double getTotalParking() {
        return TotalParking;
    }

    public void setTotalParking(double TotalParking) {
        this.TotalParking = TotalParking;
    }
    
    
    
}
