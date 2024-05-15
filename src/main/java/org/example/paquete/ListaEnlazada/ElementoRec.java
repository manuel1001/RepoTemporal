package org.example.paquete.ListaEnlazada;


import org.example.paquete.recursos.Recurso;

public class ElementoRec {
    private ElementoRec siguiente;
    private Recurso data;
    ///Methods
    public ElementoRec(Recurso data){
        this.data = data;
    }
    public void insertarmeEn(ElementoRec el){
        el.setSiguiente(this);
    }
    public void setData(Recurso data) {
        this.data = data;
    }
    public void setSiguiente(ElementoRec siguiente) {
        this.siguiente = siguiente;
    }
    public Recurso getData() {
        return this.data;
    }
    public ElementoRec getSiguiente() {
        return this.siguiente;
    }

    public String toString(){
        return String.valueOf(this.data);
    }
}
