package dominio.tads.abb;
import  dominio.tads.lista.Lista;


public class ABB<T extends Comparable<T>>{

    private NodoABBGen<T> raiz;

    public ABB() {
    }

    public ABB(T dato) {
        this.raiz = new NodoABBGen<>(dato);
    }

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

    public void listarAscendentemente() {
        if (this.raiz != null) {
            listarAscendentemente(this.raiz);
        } else {
            System.out.println("ERROR: El ABB está vacío");
        }
    }

    private void listarAscendentemente(NodoABBGen<T> nodo) {
        if (nodo != null) {
            listarAscendentemente(nodo.getIzq());
            System.out.print(nodo.getDato() + " - ");
            listarAscendentemente(nodo.getDer());
        }
    }

    public void listarDescendentemente() throws Exception {
        throw new Exception("IMPLEMENTAR");
    }

    public boolean existe(T dato) {
        return existe(this.raiz, dato);
    }


    private boolean existe(NodoABBGen<T> nodo, T dato) {
        if (nodo != null) {
            if (nodo.getDato() == dato) {
                return true;
            } else if (dato.compareTo(nodo.getDato())>0) {
                return existe(nodo.getDer(), dato);
            } else {
                return existe(nodo.getIzq(), dato);
            }
        }
        return false;
    }

    public T borrarMinimo() {
        if (this.raiz == null) {
            return null;
        } else if (this.raiz.getIzq() == null) {
            T min_value = this.raiz.getDato();
            this.raiz = this.raiz.getDer();
            return min_value;
        }
        return borrarMinimo(this.raiz);
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

    public int cantMayoresA(T k) throws Exception {
        if (this.raiz != null) {
            return cantMayoresARec(this.raiz, k);
        } else {
            throw new Exception("Error el ABB esta vacío");
        }
    }

    private int cantMayoresARec(NodoABBGen<T> nodo, T k) {
        if (nodo != null) {
            if (nodo.getDato().compareTo(k)<=0) {
                return cantMayoresARec(nodo.getDer(), k);
            } else {
                return 1 + cantMayoresARec(nodo.getDer(), k) + cantMayoresARec(nodo.getIzq(), k);
            }
        }
        return 0;
    }

    public Lista<T> listaMayoresA(T k) throws Exception {
        Lista<T> mayores = new Lista<>();
        if (this.raiz != null) {
            listaMayoresARec(this.raiz, k, mayores);
        } else {
            throw new Exception("Error el ABB esta vacío");
        }
        return mayores;
    }

    private void listaMayoresARec(NodoABBGen<T> nodo, T k, Lista<T> mayores) {
        if (nodo != null) {
            if (nodo.getDato().compareTo(k) <= 0) {
                listaMayoresARec(nodo.getDer(), k, mayores);
            } else {
                mayores.insertar(nodo.getDato());
                listaMayoresARec(nodo.getDer(), k, mayores);
                listaMayoresARec(nodo.getIzq(), k, mayores);
            }
        }
    }


}
