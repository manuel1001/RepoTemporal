package org.example.paquete.ListaEnlazada;

import javafx.scene.control.Label;

public class ElementoLabel {
    ///Arguments
    private ElementoLabel siguiente;
    private Label data;
    ///Methods
    public ElementoLabel(Label data){
        this.data = data;
    }
    public void insertarmeEn(ElementoLabel el){
        el.setSiguiente(this);
    }
    public void setData(Label data) {
        this.data = data;
    }
    public void setSiguiente(ElementoLabel siguiente) {
         this.siguiente = siguiente;
    }
    public Label getData() {
        return this.data;
    }
    public ElementoLabel getSiguiente() {
        return this.siguiente;
    }

    public String toString(){
        return String.valueOf(this.data);
    }
}