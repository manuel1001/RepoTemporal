package org.example.paquete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {

    @Test
    void getTurnosComida() {
        Partida partida = new Partida();
        partida.setTurnosComida(2);
        assertEquals(2, partida.getTurnosComida());
    }
}