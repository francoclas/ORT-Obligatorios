package dominio.tads.abb;
import  dominio.tads.lista.Lista;
import dominio.tads.lista.Nodo;


public class ABB<T extends Comparable<T>> implements IABB<T>{

    private NodoABBGen<T> raiz;

    public ABB() {
    }

    public ABB(T dato) {
        this.raiz = new NodoABBGen<>(dato);
    }


    @Override
    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new NodoABBGen<>(dato);
        } else {
            insertarRec(this.raiz, dato);
        }
    }

    private void insertarRec(NodoABBGen<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato())>0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABBGen<>(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABBGen<>(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        }
    }

    @Override
    public T buscarDato(T dato) {
        if (this.raiz !=null){
            return buscardato(this.raiz,dato);
        }else{
            return null;
        }
    }


    private T buscardato(NodoABBGen<T> nodo, T dato){
        if (nodo != null) {
            if (dato.compareTo(nodo.getDato())==0) {
                return nodo.getDato();
            } else if(dato.compareTo(nodo.getDato()) < 0) {
                //Si es menor voy por el lado izq y sigobuscando
                return buscardato(nodo.getIzq(),dato);
            }else{
                return buscardato(nodo.getDer(),dato);
            }
        }
        //Caso base
        return null;
    }
    public String listarAscendentemente() {
        if (this.raiz != null) {
            return listarAscendentemente(this.raiz);
        } else {
            return "No se encontraron datos para mostrar";
        }
    }

    private String listarAscendentemente(NodoABBGen<T> nodo) {
        if (nodo != null) {
            return listarAscendentemente(nodo.getIzq()) +  nodo.getDato() +"|"+ listarAscendentemente(nodo.getDer());
        }
        return "";
    }

    public String listarDescendentemente(){
        if (this.raiz != null) {
           return listarDesc(this.raiz);
        } else {
            return "No se encontraron datos para mostrar";

        }
    }
    private String listarDesc(NodoABBGen<T> nodo){
        if (nodo != null) {
            return listarDesc(nodo.getDer()) + nodo.getDato() + "|" + listarDesc(nodo.getIzq());
        }
        return "";
    }

    @Override
    public boolean existe(T dato) {
        return existe(this.raiz, dato);
    }

    @Override
    public NodoABBGen<T> obtenerRaiz() {
        if (this.raiz != null) {
            return raiz;
        } else {
            return null;
        }
    }

    //La idea de la funcion es iterar encontrando las casualidades de los valores recibidos. Se utiliza para resolver el ejercicio 14
    @Override
    public String listarAscendenteporLista(String[] Lista) {
        if (this.raiz != null) {
            return listarAscListaRec(Lista,raiz);
        } else {
            return null;
        }
    }
    private String listarAscListaRec(String[] Lista, NodoABBGen<T>nodo){
        if (nodo!= null){
            //Caso base
            String Aux = "";
            Aux += listarAscListaRec(Lista,nodo.getIzq()) + "|";
            for (int i = 0; i <Lista.length ; i++) {
                if (nodo.getDato().equals(Lista[i])){
                    Aux += nodo.getDato().toString();
                }
                return listarAscListaRec(Lista, nodo.getIzq());
            }
            Aux +=listarAscListaRec(Lista,nodo.getDer());
            return Aux;
        }
        return "";
    }

    private boolean existe(NodoABBGen<T> nodo, T dato) {
        if (nodo != null) {
            if (nodo.getDato().compareTo(dato) == 0) {
                return true;
            } else if (dato.compareTo(nodo.getDato())>0) {
                return existe(nodo.getDer(), dato);
            } else {
                return existe(nodo.getIzq(), dato);
            }
        }
        return false;
    }

    private T borrarMinimo(NodoABBGen<T> nodo) {
        if (nodo.getIzq().getIzq() == null) {
            T min_value = nodo.getIzq().getDato();
            nodo.setIzq(nodo.getIzq().getDer());
            return min_value;
        }
        return borrarMinimo(nodo.getIzq());
    }

    public void listarAscendenttePorNivel(int nivel) throws Exception {
        if (this.raiz != null) {
            listarAscendenttePorNivelRec(this.raiz, nivel, 0);
        } else {
            throw new Exception("Error el ABB esta vacío");
        }
    }

    private void listarAscendenttePorNivelRec(NodoABBGen<T> nodo, int nivel, int nivelActual) {
        if (nodo != null) {
            if (nivel == nivelActual) {
                System.out.println(nodo.getDato() + " ");
            } else {
                listarAscendenttePorNivelRec(nodo.getIzq(), nivel, nivelActual + 1);
                listarAscendenttePorNivelRec(nodo.getDer(), nivel, nivelActual + 1);
            }
        }
    }

}
