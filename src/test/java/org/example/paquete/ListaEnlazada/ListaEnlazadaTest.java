package org.example.paquete.ListaEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazadaTest {

    ElementoLE el = new ElementoLE(12);
    ElementoLE el2 = new ElementoLE(13);
    ElementoLE el3 = new ElementoLE(14);

    @Test
    void getNumeroElementos() {
        ListaEnlazada lista = new ListaEnlazada(el);
        assertEquals(lista.getNumeroElementos(), 1);
        lista.add(el2);
        assertEquals(lista.getNumeroElementos(), 2);
        lista.add(el3);
        assertEquals(lista.getNumeroElementos(), 3);
        ElementoLE el4 = new ElementoLE(1);
        ElementoLE el5 = new ElementoLE(2);
        ElementoLE el6 = new ElementoLE(3);
        el6.insertarmeEn(el5);
        el5.insertarmeEn(el4);
        ListaEnlazada lista2 = new ListaEnlazada(el4);
        assertEquals(lista2.getNumeroElementos(), 3);

    }

    @Test
    void isVacia() {
        ListaEnlazada lista = new ListaEnlazada(el);
        ListaEnlazada listaVacia = new ListaEnlazada();
        assertFalse(lista.isVacia());
        assertTrue(listaVacia.isVacia());
    }

    @Test
    void vaciar() {
        el.setSiguiente(el2);
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.vaciar();
        assertTrue(lista.isVacia());
    }

    @Test
    void add() {
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.add(el2);
        assertEquals(lista.getUltimo(),el2);
        lista.add("Hola");
        assertEquals(lista.getUltimo().getData(), "Hola");
    }

    @Test
    void insert() {
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.insert(el2, 0);
        assertEquals(lista.getElemento(0), el2);
        assertEquals(lista.getElemento(1), el);
        lista.insert(el3,1);
        assertEquals(lista.getElemento(1), el3);
        assertEquals(lista.getElemento(2), el);
        ElementoLE el4 = new ElementoLE(18);
        lista.insert(el4,3);
        assertEquals(lista.getElemento(3),el4);
        ElementoLE el5 = new ElementoLE(19);
        lista.insert(el4,19);
        ///Esto retorna null porque hay que seguir el orden de los elementos
        assertEquals(lista.getElemento(19), null);
    }

    @Test
    void del() {
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.add(el2);
        lista.add(el3);
        lista.del(1);
        assertEquals(lista.getElemento(1), el3);
        assertEquals(lista.getElemento(0), el);
    }

    @Test
    void getPosicion() {
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.add(el2);
        lista.add(el3);
        assertEquals(lista.getPosicion(el), 0);
        assertEquals(lista.getPosicion(el2), 1);
        assertEquals(lista.getPosicion(el3), 2);
    }

    @Test
    void getPrimero() {
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.add(el2);
        lista.add(el3);
        assertEquals(lista.getPrimero(), el);
        lista.del(0);
        assertEquals(lista.getPrimero(), el2);
    }

    @Test
    void getUltimo() {
        ListaEnlazada lista = new ListaEnlazada(el);
        assertEquals(lista.getUltimo(), el);
        lista.add(el2);
        assertEquals(lista.getUltimo(), el2);
    }

    @Test
    void getSiguiente() {
        el.setSiguiente(el2);
        ListaEnlazada lista = new ListaEnlazada(el);
        assertEquals(lista.getSiguiente(el), el2);
        lista.add(el3);
        assertEquals(lista.getSiguiente(el2), el3);
        assertEquals(lista.getSiguiente(el3), null);
    }

    @Test
    void getElemento() {
        el.setSiguiente(el2);
        ListaEnlazada lista = new ListaEnlazada(el);
        assertEquals(lista.getElemento(1), el2);
        assertEquals(lista.getElemento(0), el);
        ElementoLE el4 = new ElementoLE(16);
        el3.setSiguiente(el4);
        lista.add(el3);
        assertEquals(lista.getElemento(2), el3);
        assertEquals(lista.getElemento(3), el4);
    }

    @Test
    void imprimir(){
        el.setSiguiente(el2);
        ListaEnlazada lista = new ListaEnlazada(el);
        lista.imprimir();
    }
}