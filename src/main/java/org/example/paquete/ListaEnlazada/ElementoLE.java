package org.example.paquete.ListaEnlazada;

public class  ElementoLE <T>{
    ///Arguments
    private ElementoLE siguiente;
    private T data;
    ///Methods
    public ElementoLE(T data){
        this.data = data;
    }
    public void insertarmeEn(ElementoLE el){
        el.setSiguiente(this);
    }
    public void setData(T data) {
        this.data = data;
    }
    public Object setSiguiente(ElementoLE siguiente) {
        return this.siguiente = siguiente;
    }
    public Object getData() {
        return this.data;
    }
    protected ElementoLE getSiguiente() {
        return this.siguiente;
    }

    public String toString(){
        return String.valueOf(this.data);
    }

}