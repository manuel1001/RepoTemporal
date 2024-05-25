package org.example.paquete.Grafo;

import org.example.paquete.ListaEnlazada.ElementoLE;
import org.example.paquete.ListaEnlazada.ListaEnlazada;
import org.example.paquete.individuos.IndivBasico;

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
        ElementoLE<String> l = new ElementoLE<String>(texto);
        cola.insert (l,0);
    }
    public void push (int id){
        ElementoLE<Integer> l = new ElementoLE<Integer>(id);
        cola.insert (l,0);
    }

    public ElementoLE pull(){
        ElementoLE el = this.cola.getElemento(this.cola.getNumeroElementos()-1);
        this.cola.del(this.cola.getNumeroElementos()-1);
        return el;
    }
}
