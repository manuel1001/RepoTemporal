package org.example.paquete.Grafo;

import javafx.scene.shape.Arc;
import org.example.paquete.ListaEnlazada.Cola;
import org.example.paquete.ListaEnlazada.ElementoLE;
import org.example.paquete.ListaEnlazada.ListaEnlazada;
import org.example.paquete.ListaEnlazada.ListaInd;
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
    public Grafo(ListaInd listahistorica){

        Agua agua = new Agua();
        Comida comida = new Comida();
        Biblioteca biblioteca = new Biblioteca();
        Tesoro tesoro = new Tesoro();
        Pozo pozo = new Pozo();
        Montania montania = new Montania();

        Nodo nodoAgua = new Nodo(agua);
        Nodo nodoComida = new Nodo(comida);
        Nodo nodoBiblioteca = new Nodo(biblioteca);
        Nodo nodoTesoro = new Nodo(tesoro);
        Nodo nodoPozo = new Nodo(pozo);
        Nodo nodoMontania = new Nodo(montania);

        for (int z = 0; z < listahistorica.getNumeroElementos(); z++){
            Individuo i = listahistorica.getElemento(z).getData();
            Nodo nodoIndividuo = new Nodo<>(i);

            for (int p =0 ; p < i.getCola().getCola().getNumeroElementos()){
                if (x == "bebe"){
                    Nodo nodo = new Nodo(agua);
                    Arco arco = new Arco<>(nodo, nodoPrincipal);
                } else if (x == "comer") {

                    Nodo nodo = new Nodo<>(comida);
                    Arco arco = new Arco<>(nodo, nodoPrincipal);
                } else if (x == "biblioteca") {

                    Nodo nodo = new Nodo(biblioteca);
                    Arco arco = new Arco(nodo, nodoPrincipal);
                } else if (x == "tesoro") {

                    Nodo nodo = new Nodo (tesoro);
                    Arco arco = new Arco(nodo, nodoPrincipal);
                } else if (x == "pozo") {

                    Nodo nodo = new Nodo(pozo);
                    Arco arco = new Arco(nodo, nodoPrincipal);
                } else if (x== "montaña") {

                    Nodo nodo = new Nodo(montania);
                    Arco arco = new Arco(nodo, nodoPrincipal);
                } else if (x == "clonación") {
                    Nodo nodo = new Nodo (i);
                    Arco arco = new Arco(nodo, nodoPrincipal);
                }
            }



        }

    }
}
