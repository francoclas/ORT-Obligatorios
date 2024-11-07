package dominio.tads.grafo;


//Se utiliza con el objetivo de facilitar el traslado de datos para la solucion de pasar valor entero y valor string en ejercicio 14.
public class PesoYLista {
    int PesoMaximo;
    String [] ListaSalida;
    public PesoYLista(){}

    public int getPesoMaximo() {
        return PesoMaximo;
    }

    public void setPesoMaximo(int pesoMaximo) {
        PesoMaximo = pesoMaximo;
    }

    public String[] getListaSalida() {
        return ListaSalida;
    }

    public void setListaSalida(String[] listaSalida) {
        ListaSalida = listaSalida;
    }
}
