/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Parking;

import Excepciones.CocheraException;
import Excepciones.PropietarioException;
import Logica.Sistema;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import Interfaces.MultaInterface;

/**
 *
 * @author frgomez
 */
public class Estadia {
     private Sistema SisAux = Sistema.getInstancia();
    private static int CodigoGEN = 0;
    private String IDEstadia;
    private LocalDateTime FechaIngreso;
    private LocalDateTime FechaSalida = null;
    private Double ValorEstadia;
    private Double TotalMultas = 0.0;
    private String NumPat;
    private String CodCochera;
    private List<MultaInterface> Multas = new ArrayList<>();

    public Estadia(String NumPat, String CodCochera) {
        this.IDEstadia = CodCochera + CodigoGEN++;
        this.NumPat = NumPat;
        this.CodCochera = CodCochera;
        this.FechaIngreso = LocalDateTime.now();
    }
    public LocalDateTime getFechaIngreso() {
        return FechaIngreso;
    }

    public LocalDateTime getFechaSalida() {
        return FechaSalida;
    }

    public String getNumPat() {
        return NumPat;
    }

    public String getCodCochera() {
        return CodCochera;
    }

    public void setFechaSalida(LocalDateTime Fecha) {
        this.FechaSalida = Fecha;
    }

    public void setValorEstadia(Double ValorEstadia) {
        this.ValorEstadia = ValorEstadia;
    }
    public double getValorEstadia(){
        return this.ValorEstadia;
    }
    public String getIDEstadia(){
        return IDEstadia;
    }

    public Double getTotalMultas() {
        return TotalMultas;
    }
    
    
    //Para casos donde no se emita precio de estadia
    public void FinalizarEstadiaAnomala(){
        //Dejo precio en 0 y fecha vacia
        setValorEstadia(0.0);
        setFechaSalida(null);
    }
    //Para estadia finalizada correctamente
    public void FinalizarEstadia() throws PropietarioException, CocheraException{
        //Obtengo el factor demanda del parking con la cochera.
        double factorDemanda = SisAux.DevolverFactorDemanda(CodCochera);
        //Obtengo precio del tipo del vehiculo.
        double precioBase = SisAux.DevolverPrecioTipo(CodCochera, SisAux.ObtenerTipoVehiculo(NumPat));
                      
        setFechaSalida(LocalDateTime.now());
        //Se actualiza el valor estandar, para despues poder agregar las multas.
        CalcularValorEstadia(precioBase,factorDemanda);
        //Calculo el valor total de las multas en caso que corresponda, y se setea.
        CalcularMultas();
        //Ahora con todas las partes seteo el valor total de la estadia.
        CalcularValorTotal();
        
        
    }
    
    //Se plantea un calculo del valor de la estadia sin las multas.
    private void CalcularValorEstadia(double PrecioBase, double FactorDemanda){
        setValorEstadia(PrecioBase * SisAux.CalcularUT(FechaIngreso, FechaSalida) * FactorDemanda);
    }
    
    //Se termina de calcular el valor total de la estadia, con el agregado de multas
    public void CalcularValorTotal(){
        setValorEstadia(this.ValorEstadia + TotalMultas);
        
    }

    //Multas
        //Cargar multa
        public void cargarMulta(List<MultaInterface> lista){
            Multas = lista;
        }
        
        //Recorre las multas, genera el total y lo asigna al TotalMu
        private void CalcularMultas() {
            double Salida = 0;
            for (MultaInterface Multa : Multas) {
                Salida += Multa.calcularValor(this);
            }
            TotalMultas =  Salida;

        }
             
    
}
