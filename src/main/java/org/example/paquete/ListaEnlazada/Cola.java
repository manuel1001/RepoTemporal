package org.example.paquete.ListaEnlazada;

public class Cola {
    private ListaEnlazada cola;

    public Cola (){
    }

    public ListaEnlazada getCola() {
        return cola;
    }

    public void setCola(ListaEnlazada cola) {
        this.cola = cola;
    }

    public void push (String texto){
        ElementoLE l = new ElementoLE(texto);
        cola.insert (l,0);
    }

    public void pull (){
        cola.del(0);
    }
}
