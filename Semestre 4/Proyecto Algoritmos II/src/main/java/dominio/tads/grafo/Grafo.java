package dominio.tads.grafo;

import dominio.tads.lista.Lista;

public class Grafo implements IGrafo{


    private int cantMAxVertices;
    private int cantActualvertices;

    private boolean esDirigido;

    private String[] vertices;
    private Arista[][] matAdy;

    public Grafo(int cantMAxVertices) {
        this.cantMAxVertices = cantMAxVertices;
        this.esDirigido = true;
        this.cantActualvertices = 0;

        this.vertices = new String[this.cantMAxVertices];
        this.matAdy = new Arista[this.cantMAxVertices][this.cantMAxVertices];

        for (int i = 0; i < this.cantMAxVertices; i++) {
            for (int j = 0; j < this.cantMAxVertices; j++) {
                this.matAdy[i][j] = new Arista();
            }
        }
    }


    public Grafo(int cantMAxVertices, boolean esDirigido) {
        this.cantMAxVertices = cantMAxVertices;
        this.esDirigido = esDirigido;
        this.cantActualvertices = 0;
        this.vertices = new String[this.cantMAxVertices];
        this.matAdy = new Arista[this.cantMAxVertices][this.cantMAxVertices];

        if (this.esDirigido) {
            for (int i = 0; i < this.cantMAxVertices; i++) {
                for (int j = 0; j < this.cantMAxVertices; j++) {
                    this.matAdy[i][j] = new Arista();
                }
            }
        } else {
            for (int i = 0; i < this.cantMAxVertices; i++) {
                for (int j = i; j < this.cantMAxVertices; j++) {
                    this.matAdy[i][j] = new Arista();
                    this.matAdy[j][i] = this.matAdy[i][j];

                }
            }
        }
    }

    private int obtenerPosVacia() {
        for (int i = 0; i < this.cantMAxVertices; i++) {
            if (this.vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void agregarVertice(String v) {
        if (this.cantActualvertices < this.cantMAxVertices) {
            int poscicionvacia = this.obtenerPosVacia();
            this.vertices[poscicionvacia] = v;
            this.cantActualvertices++;
        }
    }

    private int obtenerPosVertice(String v) {
        for (int i = 0; i < this.cantMAxVertices; i++) {
            if (this.vertices[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void borrarVertice(String v) {
        int posVertice = this.obtenerPosVertice(v);
        this.vertices[posVertice] = null;
        this.cantActualvertices--;
        for (int i = 0; i < this.cantMAxVertices; i++) {
            this.matAdy[posVertice][i] = new Arista();
            this.matAdy[i][posVertice] = new Arista();
        }

    }

    @Override
    public void agregarArista(String origen, String destino, int peso) {
        int posOrigen = this.obtenerPosVertice(origen);
        int posDestino = this.obtenerPosVertice(destino);

        if (posOrigen >= 0 && posDestino >= 0) {
            this.matAdy[posOrigen][posDestino].setExiste(true);
            this.matAdy[posOrigen][posDestino].setPeso(peso);
            if (!this.esDirigido) {
                this.matAdy[posDestino][posOrigen].setExiste(true);
                this.matAdy[posDestino][posOrigen].setPeso(peso);
            }
        }
    }
    @Override
    public void actualizarArista(String origen, String destino, int nuevoPeso) {
        int posOrigen = this.obtenerPosVertice(origen);
        int posDestino = this.obtenerPosVertice(destino);

        //Verifico que exista conexion
        if (posOrigen >= 0 && posDestino >= 0 && this.matAdy[posOrigen][posDestino].isExiste()){
            this.matAdy[posOrigen][posDestino].setPeso(nuevoPeso);
            if (!this.esDirigido) {
                this.matAdy[posDestino][posOrigen].setPeso(nuevoPeso);
            }
        }
    }
    @Override
    public void borrarArista(String origen, String destino) {
        int posOrigen = this.obtenerPosVertice(origen);
        int posDestino = this.obtenerPosVertice(destino);
        this.matAdy[posOrigen][posDestino] = new Arista();
        if (!this.esDirigido) {
            this.matAdy[posDestino][posOrigen] = new Arista();
        }
    }


    @Override
    public boolean esVacio() {
        return this.cantActualvertices == 0;
    }

    @Override
    public Lista<String> verticesAdyacentes(String v) {
        Lista<String> adyacentes = new Lista<>();
        int posVert = this.obtenerPosVertice(v);
        for (int i = 0; i < this.cantMAxVertices; i++) {
            if (this.matAdy[posVert][i].isExiste()) {
                adyacentes.insertar(this.vertices[i]);
            }
        }
        return adyacentes;
    }

    @Override
    public boolean sonAdyacentes(String v1, String v2) {
        int posOrigen = this.obtenerPosVertice(v1);
        int posDestino = this.obtenerPosVertice(v2);

        return this.matAdy[posOrigen][posDestino].isExiste();
    }

    @Override
    public boolean existeVertice(String v) {
        return this.obtenerPosVertice(v) >= 0;
    }



    @Override
    public Grafo crearGrafoVacio(int cantMaxDeVertices) {
        return null;
    }



    @Override
    public boolean verticeCritico(String v) {
        /*Para verificar si es critico, voy agenerar un array de vertices, marcar el solicitado como verdadero
        Luego itero mediante DFS, para ver si me quedo alguno sin marcar, si alguno queda sin revisar
        el vertice es critico.
        */
        boolean [] Vertices = new boolean[cantActualvertices];
        //Marco el solicitado como visitado
        Vertices[this.obtenerPosVertice(v)] = true;
        //Comienzo el DFS
        //Para empezar busco


        //Al terminar reviso si tengo todos mis vertices visitados, si no es el caso, el vertice es critico
        for (int i = 0; i < cantActualvertices; i++) {
            if (!Vertices[i]) {
                return true;
            }
        }
        return false;
    }
    private void dfsCritico(int pos,boolean []visitados){
        //Seteo el vertice recibido como visitado
        visitados[pos] = true;
        //Reviso las conexion de mi vertice, si existe, y no esta visitado. Lo visito con DFS
        for(int i = 0; i < this.cantMaxVertices; ++i) {
            if (this.matAdy[pos][i].isExiste() && !visitados[i]) {
                this.dfsRec(i, visitados);
            }
        }

    }
}
