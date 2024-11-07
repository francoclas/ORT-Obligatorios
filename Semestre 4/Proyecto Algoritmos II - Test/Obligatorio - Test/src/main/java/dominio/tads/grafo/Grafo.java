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
        //Obtengo posiciones de ambos vertices
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
    public boolean verticeCritico(String v) {
        /*Para verificar si es critico, voy agenerar un array de vertices, marcar el solicitado como verdadero
        Luego itero mediante DFS, para ver si me quedo alguno sin marcar, si alguno queda sin revisar
        el vertice es critico.
        */

        //Obtengo el subgrafo de la region de mi vertice
        Grafo RegionVertice = dfsGeneradorSubgrafo(v);
        //Marco el solicitado como visitado
        boolean [] VerticesSubgrafo =  new boolean [RegionVertice.cantMAxVertices];
        //Busco mi vertice a verificar si es critico y lo marco como verdadero, para luego en el DFS, evitar que se revisen sus conexiones.
        VerticesSubgrafo[RegionVertice.obtenerPosVertice(v)] = true;
        //Si mi grafo solo tiene un vertice corresponde a no ser virtualmente critico.
        if (VerticesSubgrafo.length == 1){
            return false;
        }
        //Aplico DFS, creo una nueva variable para poder hacer recursiva

        int verticeAux = 0;
        if (verticeAux == RegionVertice.obtenerPosVertice(v)){
            verticeAux = 1;
        }
        RegionVertice.dfsRecursivo(verticeAux,VerticesSubgrafo);
        //Al terminar reviso si tengo todos mis vertices visitados, si todos son verdaderos no es critico, si no es el caso, el vertice es critico
        for (int i = 0; i < VerticesSubgrafo.length; i++) {
            if (!VerticesSubgrafo[i]) {
                return true;
            }
        }
        return false;
    }
    private Grafo dfsGeneradorSubgrafo(String vertice){
        //Genero array del grafo general
        boolean [] visitados = new boolean[this.cantMAxVertices];
        int pos = this.obtenerPosVertice(vertice);
        //Mando el array a dfs, para ver los nodos que estan relacionados con el vertice ingresado
        this.dfsRecursivo(pos,visitados);
        //Itero sobre los visitados, a partir de estos, obtengo la cantidad de vertices del nuevo    ubgrafo
        int cantVerticesSubGrafo = 0;
        for (int i = 0; i < visitados.length; i++) {
            if (visitados[i]) {
                cantVerticesSubGrafo++;
            }
        }
        //genero nuevo subgrafo y devuelvo
        Grafo Salida = new Grafo(cantVerticesSubGrafo,this.esDirigido);
        //Cargo los vertices que forman parte del grafo de la region solicitad, al subgrafo
        for (int i = 0; i < visitados.length; i++){
            //Verifico que no sea el valor vertice que es el primero
            if (visitados[i]) {
                Salida.agregarVertice(vertices[i]);
            }
        }
        //Vuelvo a recorrer para agregar las conexiones del subgrafo
        for (int i = 0; i< visitados.length; i++){
            if (visitados[i]) {
                //Vuelvo a iterar buscando las conexiones
                for (int j = 0; j< visitados.length; j++){
                    if (visitados[j] && this.matAdy[i][j].isExiste()){
                        //Agrego la conexion al subgrafo
                        Salida.agregarArista(vertices[i],vertices[j],matAdy[i][j].getPeso());
                    }
                }
            }
        }
        return Salida;
    }
    private void dfsRecursivo(int pos,boolean []visitados){
        //Marco la posicion recibida como verdadero
        visitados[pos] = true;
        //Itero por sus conexiones
        for (int i = 0; i < visitados.length; ++i){
            if (this.matAdy[pos][i].isExiste() && !visitados[i]) {
                this.dfsRecursivo(i,visitados);
            }
        }
    }

    @Override
    public PesoYLista BuscarConexionLimitePeso(String vInicial, int pesoLimite) {
        PesoYLista Salida = new PesoYLista();
        //Genero estructuras basicas
        int posOrigen = this.obtenerPosVertice(vInicial);
        boolean[] visitados = new boolean[this.cantActualvertices];
        int[] costos = new int[this.cantActualvertices];
        int[] anteriores = new int[this.cantActualvertices];
        //Los inicializo
        int minPos;
        for (minPos = 0; minPos < this.cantActualvertices; ++minPos) {
            visitados[minPos] = false;
            costos[minPos] = Integer.MAX_VALUE;
            anteriores[minPos] = -1;
        }
        //Marco el origen como 0, y visitado
        costos[posOrigen] = 0;
        visitados[posOrigen] = true;
        //Itero sobre los demas vertices
        for(minPos = posOrigen; minPos > -1; minPos = this.obtenerPosMenorCostoNoVisitado(costos, visitados,pesoLimite)){
            this.actualizarCostosAnteriores(minPos, costos, visitados, anteriores);
        }

        Salida.setListaSalida(GenerarStringConexiones(visitados));
        Salida.setPesoMaximo(ObtenerPesoMaximo(costos,visitados));
        return Salida;
    }

    private int ObtenerPesoMaximo(int[] costos,boolean[] visitados) {
        int Salida = Integer.MIN_VALUE;
        for (int i = 0; i < costos.length; i++) {
            if (visitados[i]) {
                if (costos[i] > Salida) {
                    Salida = costos[i];
                }
            }
        }
        return Salida;
    }

    private String[] GenerarStringConexiones(boolean[] visitados) {
        int Aux = 0;
        for (int i = 0; i < visitados.length; i++) {
            if (visitados[i]){
                Aux++;
            }
        }
        String [] Salida = new String[Aux];
        int ContadorSalida = 0;
        for (int i = 0; i < visitados.length; i++) {
            if (visitados[i]){
                Salida[ContadorSalida] = vertices[i];
                ContadorSalida++;
            }
        }

        return Salida;
    }

    private void actualizarCostosAnteriores(int posActual, int []costos,boolean [] visitados,int []anteriores){
        for (int i = 0; i < this.cantActualvertices; i++) {
            if (i != posActual) {
                int costoVerticeActual = costos[posActual] + this.matAdy[posActual][i].getPeso();
                //Tambien agrego el peso como factor, si es mayor ya no debo iterar
                if (this.matAdy[posActual][i].isExiste() && !visitados[i] && costos[i] > costoVerticeActual){
                    costos[i] = costoVerticeActual;
                    anteriores[i] = posActual;
                }
            }
        }
    }
    private int obtenerPosMenorCostoNoVisitado(int[] costos, boolean[] visitados, int pesoLimite) {
        int minCosto = Integer.MAX_VALUE;
        int posMenorCosto = -1;
        for(int i = 0; i < this.cantActualvertices; ++i) {
            if (!visitados[i] && costos[i] < minCosto && costos[i] <= pesoLimite) {

                minCosto = costos[i];
                posMenorCosto = i;
            }
        }
        if (posMenorCosto > -1){
            visitados[posMenorCosto] = true;
        }
        return posMenorCosto;
    }
    private String[] OrdenarSegunCosto(int[] anteriores){
        String [] Salida = new String[anteriores.length];
        for (int i = 0; i < Salida.length; i++) {
            if (anteriores[i] > -1){
                Salida[i] = this.vertices[anteriores[i]];
            }
        }
        return Salida;
    }
}

