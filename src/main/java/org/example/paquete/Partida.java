package org.example.paquete;

import org.example.paquete.ListaEnlazada.ListaInd;

import org.example.paquete.ListaEnlazada.*;

public class Partida {
    //////Argumentos

    ///Nombre del Json
    private String archivoNombre;

    ///Atributos del entorno
    private int aparicionAgua;
    private int turnosAgua;
    private int aparicionComida;
    private int turnosComida;
    private int aparicionMontania;
    private int turnosMontania;
    private int aparicionBiblio;
    private int aumentoBiblio;
    private int aparicionTesoro;
    private int aumentoTesoro;
    private int aparicionPozo;
    private int aparicionRecurso;

    ///Datos del mapa
    private int numeroColumnas;
    private int numeroFilas;
    private ListaRecursos todosRecuros;


    ///Individuos
    private ListaInd listaInicialIndividuos;

    public void setAparicionRecurso(int aparicionRecurso) {
        this.aparicionRecurso = aparicionRecurso;
    }

    ///Methods
    public Partida() {

    }

    public Partida(String archivoNombre, int aparicionAgua, int turnosAgua, int aparicionComida, int turnosComida,
                   int aparicionMontania, int turnosMontania, int aparicionBiblio, int aumentoBiblio, int aparicionTesoro, int aumentoTesoro, int aparicionPozo, ListaInd listaInicialIndividuos, int numeroColumnas, int numeroFilas, int aparicionRecurso) {
        this.archivoNombre = archivoNombre;
        this.aparicionAgua = aparicionAgua;
        this.turnosAgua = turnosAgua;
        this.aparicionComida = aparicionComida;
        this.turnosComida = turnosComida;
        this.aparicionMontania = aparicionMontania;
        this.turnosMontania = turnosMontania;
        this.aparicionBiblio = aparicionBiblio;
        this.aumentoBiblio = aumentoBiblio;
        this.aparicionTesoro = aparicionTesoro;
        this.aumentoTesoro = aumentoTesoro;
        this.aparicionPozo = aparicionPozo;
        this.listaInicialIndividuos = listaInicialIndividuos;
        this.numeroColumnas = numeroColumnas;
        this.numeroFilas = numeroFilas;
        this.aparicionRecurso = aparicionRecurso;
    }

    ///Constructor temporal mientras se hace la clase Individuo
    public Partida(String archivoNombre, int aparicionAgua, int turnosAgua,
                   int aparicionComida, int turnosComida, int aparicionMontania,
                   int turnosMontania, int aparicionBiblio, int aumentoBiblio,
                   int aparicionTesoro, int aumentoTesoro, int aparicionPozo,
                   int numeroColumnas, int numeroFilas) {
        this.archivoNombre = archivoNombre;
        this.aparicionAgua = aparicionAgua;
        this.turnosAgua = turnosAgua;
        this.aparicionComida = aparicionComida;
        this.turnosComida = turnosComida;
        this.aparicionMontania = aparicionMontania;
        this.turnosMontania = turnosMontania;
        this.aparicionBiblio = aparicionBiblio;
        this.aumentoBiblio = aumentoBiblio;
        this.aparicionTesoro = aparicionTesoro;
        this.aumentoTesoro = aumentoTesoro;
        this.aparicionPozo = aparicionPozo;
        this.numeroColumnas = numeroColumnas;
        this.numeroFilas = numeroFilas;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public int getAparicionAgua() {
        return aparicionAgua;
    }

    public int getTurnosAgua() {
        return turnosAgua;
    }

    public int getAparicionComida() {
        return aparicionComida;
    }

    public int getTurnosComida() {
        return turnosComida;
    }

    public int getAparicionMontania() {
        return aparicionMontania;
    }

    public int getTurnosMontania() {
        return turnosMontania;
    }

    public int getAparicionBiblio() {
        return aparicionBiblio;
    }

    public int getAumentoBiblio() {
        return aumentoBiblio;
    }

    public int getAparicionTesoro() {
        return aparicionTesoro;
    }

    public int getAumentoTesoro() {
        return aumentoTesoro;
    }

    public int getAparicionPozo() {
        return aparicionPozo;
    }

//    public ListaEnlazada getListaInicialIndividuos() {
//        return listaInicialIndividuos;
//    }

    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public void setAparicionAgua(int aparicionAgua) {
        this.aparicionAgua = aparicionAgua;
    }

    public void setTurnosAgua(int turnosAgua) {
        this.turnosAgua = turnosAgua;
    }

    public void setAparicionComida(int aparicionComida) {
        this.aparicionComida = aparicionComida;
    }

    public void setTurnosComida(int turnosComida) {
        this.turnosComida = turnosComida;
    }

    public void setAparicionMontania(int aparicionMontania) {
        this.aparicionMontania = aparicionMontania;
    }

    public void setTurnosMontania(int turnosMontania) {
        this.turnosMontania = turnosMontania;
    }

    public void setAparicionBiblio(int aparicionBiblio) {
        this.aparicionBiblio = aparicionBiblio;
    }

    public void setAumentoBiblio(int aumentoBiblio) {
        this.aumentoBiblio = aumentoBiblio;
    }

    public void setAparicionTesoro(int aparicionTesoro) {
        this.aparicionTesoro = aparicionTesoro;
    }

    public void setAumentoTesoro(int aumentoTesoro) {
        this.aumentoTesoro = aumentoTesoro;
    }

    public void setAparicionPozo(int aparicionPozo) {
        this.aparicionPozo = aparicionPozo;
    }

//    public void setListaInicialIndividuos(ListaEnlazada listaInicialIndividuos) {
//        this.listaInicialIndividuos = listaInicialIndividuos;
//    }

    public void setNumeroColumnas(int numeroColumnas) {
        this.numeroColumnas = numeroColumnas;
    }

    public void setNumeroFilas(int numeroFilas) {
        this.numeroFilas = numeroFilas;
    }
    public int getAparicionRecurso() {
        return aparicionRecurso;
    }
    public ListaInd getListaInicialIndividuos() {
        return listaInicialIndividuos;
    }
    public ListaRecursos getTodosRecuros() {
        return todosRecuros;
    }

    public void setTodosRecuros(ListaRecursos todosRecuros) {
        this.todosRecuros = todosRecuros;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "archivoNombre='" + archivoNombre + '\'' +
                ", aparicionAgua=" + aparicionAgua +
                ", turnosAgua=" + turnosAgua +
                ", aparicionComida=" + aparicionComida +
                ", turnosComida=" + turnosComida +
                ", aparicionMontania=" + aparicionMontania +
                ", turnosMontania=" + turnosMontania +
                ", aparicionBiblio=" + aparicionBiblio +
                ", aumentoBiblio=" + aumentoBiblio +
                ", aparicionTesoro=" + aparicionTesoro +
                ", aumentoTesoro=" + aumentoTesoro +
                ", aparicionPozo=" + aparicionPozo +
                ", numeroColumnas=" + numeroColumnas +
                ", numeroFilas=" + numeroFilas;

    }
}

