package org.example.paquete.ArbolBinario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioTest {

    @Test
    void getRaiz() {
    }

    @Test
    void setRaiz() {
    }

    @Test
    void enlazarDerecha() {
    }

    @Test
    void enlazarIzquierda() {
    }

    @Test
    void imprimirArbol() {
        ArbolBinario arbol = new ArbolBinario();
        ElementoA elementoA = new ElementoA(1);
        ElementoA elementoA1 = new ElementoA(2);
        ElementoA elementoA2 = new ElementoA(3);
        elementoA1.setIzquierda(elementoA);
        arbol.setRaiz(elementoA2);
        arbol.getRaiz().setDerecha(elementoA1);
        arbol.getRaiz().setIzquierda(elementoA);
        String x = arbol.imprimirArbol();
        System.out.println(x);
        int z = (3+2)/2;
        System.out.println(z);
    }
}