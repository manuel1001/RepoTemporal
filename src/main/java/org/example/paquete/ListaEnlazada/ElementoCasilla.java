package org.example.paquete.ListaEnlazada;

public class ElementoCasilla {
    ///Arguments
    private ElementoCasilla siguiente;
    private Casilla data;
    ///Methods
    public ElementoCasilla(Casilla data){
        this.data = data;
    }
    public void insertarmeEn(ElementoCasilla el){
        el.setSiguiente(this);
    }
    public void setData(Casilla data) {
        this.data = data;
    }
    public void setSiguiente(ElementoCasilla siguiente) {
        this.siguiente = siguiente;
    }
    public Casilla getData() {
        return this.data;
    }
    protected ElementoCasilla getSiguiente() {
        return this.siguiente;
    }

    public String toString(){
        return String.valueOf(this.data);
    }
}