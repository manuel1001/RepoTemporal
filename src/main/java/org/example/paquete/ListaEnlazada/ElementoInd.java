package org.example.paquete.ListaEnlazada;

import org.example.paquete.individuos.Individuo;

import org.example.paquete.individuos.*;

public class ElementoInd {
    ///Arguments
    private ElementoInd siguiente;
    private Individuo data;
    ///Methods
    public ElementoInd(Individuo data){
        this.data = data;
    }
    public void insertarmeEn(ElementoInd el){
        el.setSiguiente(this);
    }
    public void setData(Individuo data) {
        this.data = data;
    }
    public void setSiguiente(ElementoInd siguiente) {
        this.siguiente = siguiente;
    }
    public Individuo getData() {
        return this.data;
    }
    protected ElementoInd getSiguiente() {
        return this.siguiente;
    }

    public String toString(){
        return String.valueOf(this.data);
    }
}