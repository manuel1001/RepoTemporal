package org.example.paquete.ListaEnlazada;

import org.example.paquete.individuos.Individuo;

import org.example.paquete.individuos.*;

public class Casilla {

    private ListaInd listaIndividuos;
    private ListaEnlazada listaRecursos;
    private int posX;
    private int posY;

    public Casilla(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.listaIndividuos = new ListaInd();
        this.listaRecursos = new ListaEnlazada();
    }

    public ListaInd getListaIndividuos() {
        return listaIndividuos;
    }

    public void setListaIndividuos(ListaInd listaIndividuos) {
        this.listaIndividuos = listaIndividuos;
    }

    public ListaEnlazada getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(ListaEnlazada listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void addIndividuo(Individuo ind){
        this.listaIndividuos.add(new ElementoInd(ind));
    }
    public void delIndividuo(Individuo ind){
        this.listaIndividuos.del(listaIndividuos.getPosicion(ind));
    }
}




