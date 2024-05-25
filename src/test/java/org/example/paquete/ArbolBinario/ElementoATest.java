package org.example.paquete.ArbolBinario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoATest {
    ElementoA nodo = new ElementoA(12);
    ElementoA nodoDer = new ElementoA(13);
    ElementoA nodoIzq = new ElementoA(14);
    @Test
    void getData() {
        assertEquals(nodo.getData(), 12);
    }

    @Test
    void setData() {
        nodo.setData(9);
        assertEquals(nodo.getData(), 9);
    }

    @Test
    void setDerecha() {
        nodo.setDerecha(nodoDer);
        assertEquals(nodo.getDerecha(), nodoDer);
    }

    @Test
    void setIzquierda() {
        nodo.setIzquierda(nodoIzq);
        assertEquals(nodo.getIzquierda(), nodoIzq);
    }

    @Test
    void getDerecha() {
        nodo.setDerecha(nodoDer);
        assertEquals(nodo.getDerecha(), nodoDer);
    }

    @Test
    void getIzquierda() {
        nodo.setIzquierda(nodoIzq);
        assertEquals(nodo.getIzquierda(), nodoIzq);
    }
}