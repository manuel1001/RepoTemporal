package org.example.paquete.Grafo;

import javafx.scene.shape.Arc;
import org.example.paquete.ListaEnlazada.Cola;
import org.example.paquete.ListaEnlazada.ElementoLE;
import org.example.paquete.ListaEnlazada.ListaEnlazada;
import org.example.paquete.individuos.Individuo;
import org.example.paquete.recursos.*;

public class Grafo {
    ///Arguments
    private ListaEnlazada listVertices;
    private ListaEnlazada listArcos;

    public Grafo() {
        this.listArcos = new ListaEnlazada();
        this.listVertices = new ListaEnlazada();
    }

    public ListaEnlazada getListVertices() {
        return listVertices;
    }

    public ListaEnlazada getListArcos() {
        return listArcos;
    }

    public void addVertice(Nodo nodo){
        this.listVertices.add(nodo);
    }
    public void delVertice(Nodo nodo){
        this.listVertices.del(listVertices.getPosicion(nodo));
        ///Eliminamos los arcos que llegaban a este vertice
        while(nodo.listaRecep.getPrimero() != null){
            ElementoLE ultimo = nodo.listaRecep.getUltimo();
            if(this.listArcos.getPosicion(ultimo) != -1){
                listArcos.del(listArcos.getPosicion(ultimo));
            }
            nodo.listaRecep.del(nodo.listaRecep.getPosicion(ultimo));
        }
        ///Eliminamos los arcos que salian de este vertice
        while(nodo.listaSalida.getPrimero() != null){
            ElementoLE ultimo = nodo.listaSalida.getUltimo();
            if(this.listArcos.getPosicion(ultimo) != -1){
                listArcos.del(listArcos.getPosicion(ultimo));
            }
            nodo.listaSalida.del(nodo.listaSalida.getPosicion(ultimo));
        }
    }
    public void addArco(Arco arco){
        if(this.listVertices.getPosicion(arco.destino) != -1 && this.listVertices.getPosicion(arco.origen) != -1){
            ///En este caso el vertice origen y destino pertenecen al grafo
            ElementoLE L =new ElementoLE<>(arco);
            arco.origen.listaSalida.add(L);
            arco.destino.listaRecep.add(L);
            listArcos.add(L);
        }
        else{
            System.out.println("error: vertices no pertenecen al grafo");
        }
    }
    public void delArco(Arco arco){
        if(this.listArcos.getPosicion(arco.destino) != -1){
            arco.origen.listaSalida.del(arco.origen.listaSalida.getPosicion(arco));
            arco.destino.listaRecep.del(arco.destino.listaRecep.getPosicion(arco));
            this.listArcos.del(this.listArcos.getPosicion(arco));
        }
        else{
            System.out.println("error: arco no pertenece a grafo");
        }
    }
    public Grafo(Individuo i){
        Cola cola = i.getCola();
        Nodo nodoPrincipal = new Nodo(i);
        for (int z = 0; z < cola.getCola().getNumeroElementos(); z++){
            Object x = cola.getCola().getElemento(z).getData();
            if (x == "bebe"){
                Agua agua = new Agua();
                Nodo nodo = new Nodo(agua);
                Arco arco = new Arco<>(nodo, nodoPrincipal);
            } else if (x == "comer") {
                Comida comida = new Comida();
                Nodo nodo = new Nodo<>(comida);
                Arco arco = new Arco<>(nodo, nodoPrincipal);
            } else if (x == "biblioteca") {
                Biblioteca biblioteca = new Biblioteca();
                Nodo nodo = new Nodo(biblioteca);
                Arco arco = new Arco(nodo, nodoPrincipal);
            } else if (x == "tesoro") {
                Tesoro tesoro = new Tesoro();
                Nodo nodo = new Nodo (tesoro);
                Arco arco = new Arco(nodo, nodoPrincipal);
            } else if (x == "pozo") {
                Pozo pozo = new Pozo();
                Nodo nodo = new Nodo(pozo);
                Arco arco = new Arco(nodo, nodoPrincipal);
            } else if (x== "montaña") {
                Montania montania = new Montania();
                Nodo nodo = new Nodo(montania);
                Arco arco = new Arco(nodo, nodoPrincipal);
            } else if (x == "clonación") {
                Arco arco = new Arco(nodoPrincipal, nodoPrincipal);
            }
        }

    }
}
