package org.example.paquete.ArbolBinario;
import org.example.paquete.individuos.Individuo;

public class ArbolBinario {
    ///Arguments
    protected ElementoA raiz;
    ///Methods

    public ArbolBinario() {
        this.raiz = null;
    }

    public ArbolBinario(ElementoA nodo) {
        this.raiz = nodo;
    }

    public ElementoA getRaiz(){
        return this.raiz;
    }
    public void setRaiz(ElementoA raiz){
        this.raiz =raiz;
    }
    public void enlazarDerecha(Individuo ind){
        this.raiz.setDerecha(ind.getArbolGene().getRaiz());
    }
    public void enlazarIzquierda(Individuo ind){
        this.raiz.setIzquierda(ind.getArbolGene().getRaiz());
    }

    public String imprimirArbol() {
        return imprimirArbol(this.raiz, 0);
    }

    // Método auxiliar recursivo para imprimir el árbol y devolverlo como String
    private String imprimirArbol(ElementoA nodo, int nivel) {
        StringBuilder sb = new StringBuilder();
        if (nodo != null) {
            sb.append(imprimirArbol(nodo.getDerecha(), nivel + 1));
            sb.append(" ".repeat(nivel * 4)).append(nodo.getData()).append("\n");
            sb.append(imprimirArbol(nodo.getIzquierda(), nivel + 1));
        }
        return sb.toString();
    }
}



