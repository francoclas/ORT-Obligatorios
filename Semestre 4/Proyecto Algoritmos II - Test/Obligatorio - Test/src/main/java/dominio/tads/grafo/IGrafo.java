package dominio.tads.grafo;

import dominio.tads.lista.Lista;

    public interface IGrafo {

        void agregarVertice(String v);
        void agregarArista(String origen,String destino,int peso);
        void actualizarArista(String origen,String destino,int nuevoPeso);
        void borrarVertice(String v);
        void borrarArista(String origen,String destino);
        boolean esVacio();
        Lista<String> verticesAdyacentes(String v);
        boolean sonAdyacentes(String v1, String v2);
        boolean verticeCritico(String v);
    }
