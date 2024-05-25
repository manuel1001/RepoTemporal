package org.example.paquete.Grafo;

import java.util.List;

public class Camino<T> {
    List<Nodo<T>> camino;
    double coste;

    public Camino(List<Nodo<T>> camino, double coste) {
        this.camino = camino;
        this.coste = coste;
    }

    public List<Nodo<T>> getCamino() {
        return camino;
    }

    public double getCoste() {
        return coste;
    }

    @Override
    public String toString() {
        StringBuffer salida = new StringBuffer();
        salida.append("======= Volcado del camino desde [" + getCamino().getFirst().data + "] hasta [" + getCamino().getLast().data + "]: ======\n");
        salida.append("Referencias a los vértices: " + this.getCamino() + "\n");
        salida.append("Lista de datos en vértices: [");
        for (Nodo<T> vertexx : this.getCamino()) {
            salida.append(vertexx.data);
        }
        salida.append("] - Coste: " + this.getCoste() + "\n");

        return salida.toString();
    }
}
