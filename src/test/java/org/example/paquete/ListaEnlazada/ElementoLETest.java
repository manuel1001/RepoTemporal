package org.example.paquete.ListaEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLETest {

    @Test
    void insertarmeEn() {
        ElementoLE el = new ElementoLE(12);
        ElementoLE el2 = new ElementoLE(13);
        el2.insertarmeEn(el);
        assertEquals(el2, el.getSiguiente());
        ElementoLE el3 = new ElementoLE(14);
        el3.insertarmeEn(el2);
        el2.insertarmeEn(el);
        assertEquals(el3, el.getSiguiente().getSiguiente());
    }

    @Test
    void setData() {
        ElementoLE el = new ElementoLE(12);
        el.setData(13);
        assertEquals(el.getData(), 13);
    }

    @Test
    void setSiguiente() {
        ElementoLE el = new ElementoLE(12);
        ElementoLE el2 = new ElementoLE(13);
        el.setSiguiente(el2);
        assertEquals(el.getSiguiente(), el2);
    }

    @Test
    void getData() {
        ElementoLE el = new ElementoLE(12);
        assertEquals(el.getData(), 12);
    }

    @Test
    void getSiguiente() {
        ElementoLE el = new ElementoLE(12);
        ElementoLE el2 = new ElementoLE(13);
        el.setSiguiente(el2);
        assertEquals(el.getSiguiente(), el2);
    }
}