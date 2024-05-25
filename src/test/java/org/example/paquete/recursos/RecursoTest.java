package org.example.paquete.recursos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursoTest {

    @Test
    public void testGetSetTurnos() {
        Recurso recurso = new Recurso(10,11);
        recurso.setDuracion(10);
        assertEquals(10, recurso.getDuracion());

        recurso.setDuracion(20);
        assertEquals(20, recurso.getDuracion());
    }

    @Test
    public void testGetSetPosX() {
        Recurso recurso = new Recurso(0,11);
        assertEquals(0, recurso.getPosX());

        recurso.setPosX(15);
        assertEquals(15, recurso.getPosX());
    }

    @Test
    public void testGetSetPosY() {
        Recurso recurso = new Recurso(0,0);
        assertEquals(0, recurso.getPosY());

        recurso.setPosY(25);
        assertEquals(25, recurso.getPosY());
    }
}