/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Anomalias;

import java.time.LocalDateTime;

/**
 *
 * @author franc
 */
public class Anomalia {
    
    private LocalDateTime FechaRegistro;
    private String Propietario;
    private String Codigo;
    private String Cochera;
    private String NumPat;

    public Anomalia(String Propietario, String Codigo, String Cochera, String NumPat) {
        this.FechaRegistro = LocalDateTime.now();
        this.Propietario = Propietario;
        this.Codigo = Codigo;
        this.Cochera = Cochera;
        this.NumPat = NumPat;
    }

    public LocalDateTime getFechaRegistro() {
        return FechaRegistro;
    }



    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCochera() {
        return Cochera;
    }

    public void setCochera(String Cochera) {
        this.Cochera = Cochera;
    }

    public String getNumPat() {
        return NumPat;
    }

    public void setNumPat(String NumPat) {
        this.NumPat = NumPat;
    }
    
    
    
    
}
