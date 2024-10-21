package dominio.tads.grafo;

public class Arista {
    private int peso;
    private boolean existe;

    public Arista() {
        this.peso = 0;
        this.existe = false;
    }

    public Arista(int peso) {
        this.peso = peso;
        this.existe = true;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
