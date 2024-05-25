package org.example.paquete.Grafo;

import org.example.paquete.ListaEnlazada.ElementoLE;

public class Arco <T> {
    Nodo destino;
    Nodo origen;
    public Arco(Nodo destino, Nodo origen){
        this.destino = destino;
        this.origen = origen;
    }

    public Arco(ElementoLE elemento, ElementoLE elemento1) {
    }
}