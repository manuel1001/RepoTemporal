package org.example.paquete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {

    @Test
    void getArchivoNombre() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        assertEquals("miArchivo", partida.getArchivoNombre());
    }

    @Test
    void getAparicionAgua() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(1, partida.getAparicionAgua());
    }

    @Test
    void getTurnosAgua() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(2, partida.getTurnosAgua());
    }

    @Test
    void getAparicionComida() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(3, partida.getAparicionComida());
    }

    @Test
    void getTurnosComida() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(4, partida.getTurnosComida());
    }

    @Test
    void getAparicionMontania() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(5, partida.getAparicionMontania());
    }

    @Test
    void getTurnosMontania() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(6, partida.getTurnosMontania());
    }

    @Test
    void getAparicionBiblio() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(7, partida.getAparicionBiblio());
    }

    @Test
    void getAumentoBiblio() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(8, partida.getAumentoBiblio());
    }

    @Test
    void getAparicionTesoro() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(9, partida.getAparicionTesoro());
    }

    @Test
    void getAumentoTesoro() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(10, partida.getAumentoTesoro());
    }

    @Test
    void getAparicionPozo() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(11, partida.getAparicionPozo());
    }

    @Test
    void getNumeroColumnas() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(12, partida.getNumeroColumnas());
    }

    @Test
    void getNumeroFilas() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertEquals(13, partida.getNumeroFilas());
    }

    @Test
    void setArchivoNombre() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setArchivoNombre("archivo");
        assertEquals("archivo", partida.getArchivoNombre());
    }

    @Test
    void setAparicionAgua() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAparicionAgua(0);
        assertEquals(0, partida.getAparicionAgua());
    }

    @Test
    void setTurnosAgua() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setTurnosAgua(1);
        assertEquals(1, partida.getTurnosAgua());
    }

    @Test
    void setAparicionComida() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAparicionComida(2);
        assertEquals(2, partida.getAparicionComida());
    }

    @Test
    void setTurnosComida() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setTurnosComida(3);
        assertEquals(3, partida.getTurnosComida());
    }

    @Test
    void setAparicionMontania() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAparicionMontania(4);
        assertEquals(4, partida.getAparicionMontania());
    }

    @Test
    void setTurnosMontania() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setTurnosMontania(5);
        assertEquals(5, partida.getTurnosMontania());
    }

    @Test
    void setAparicionBiblio() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAparicionBiblio(6);
        assertEquals(6, partida.getAparicionBiblio());
    }

    @Test
    void setAumentoBiblio() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAumentoBiblio(7);
        assertEquals(7, partida.getAumentoBiblio());
    }

    @Test
    void setAparicionTesoro() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAparicionTesoro(8);
        assertEquals(8, partida.getAparicionTesoro());
    }

    @Test
    void setAumentoTesoro() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAumentoTesoro(9);
        assertEquals(9, partida.getAumentoTesoro());
    }

    @Test
    void setAparicionPozo() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setAparicionPozo(10);
        assertEquals(10, partida.getAparicionPozo());
    }

    @Test
    void setNumeroColumnas() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setNumeroColumnas(11);
        assertEquals(11, partida.getNumeroColumnas());
    }

    @Test
    void setNumeroFilas() {
        Partida partida = new Partida("miArchivo", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        partida.setNumeroFilas(12);
        assertEquals(12, partida.getNumeroFilas());
    }
}