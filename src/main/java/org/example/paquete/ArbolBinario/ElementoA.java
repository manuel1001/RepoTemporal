package org.example.paquete.ArbolBinario;
public class ElementoA{
    ///Arguments
    private int data;
    private ElementoA derecha;
    private ElementoA izquierda;

    ///Methods

    public ElementoA(int data, ElementoA derecha, ElementoA izquierda) {
        this.data = data;
        this.derecha = derecha;
        this.izquierda = izquierda;
    }

    public void setDerecha(ElementoA derecha) {
        this.derecha = derecha;
    }

    public void setIzquierda(ElementoA izquierda) {
        this.izquierda = izquierda;
    }

    public ElementoA(int data) {
        this.data = data;
        this.derecha = null;
        this.izquierda = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ElementoA getDerecha() {
        return derecha;
    }

    public ElementoA getIzquierda() {
        return izquierda;
    }

}