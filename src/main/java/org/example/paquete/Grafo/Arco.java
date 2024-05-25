package org.example.paquete.Grafo;

public class Arco <T> {
    Nodo<T> destino;
    Nodo<T> origen;
    double coste;

    public Arco(Nodo<T> destino, Nodo<T> origen, double coste){
        this.destino = destino;
        this.origen = origen;
        this.coste = coste;
    }
    public Arco(Nodo<T> destino, Nodo<T> origen){
        this.destino = destino;
        this.origen = origen;
        this.coste = coste;
    }

}
