package org.example.paquete.Grafo;

import org.example.paquete.ListaEnlazada.ElementoLE;
import org.example.paquete.ListaEnlazada.ListaEnlazada;
import org.example.paquete.recursos.Recurso;

public class Nodo{
    ///Arguments
    private int id;
    protected String data;
    protected ListaEnlazada listaRecep;
    protected ListaEnlazada listaSalida;

    ///Methods
    public Nodo(String dato){
        this.data = dato;
        this.listaRecep = new ListaEnlazada();
        this.listaSalida =new ListaEnlazada();
    }
    public Nodo(int id){
        this.id = id;
        this.listaRecep = new ListaEnlazada();
        this.listaSalida =new ListaEnlazada();
    }

    public String getDato() {
        return data;
    }

    public ListaEnlazada getListaRecep() {
        return listaRecep;
    }

    public ListaEnlazada getListaSalida() {
        return listaSalida;
    }

    public void setDato(String dato) {
        this.data = dato;
    }

    public void setListaRecep(ListaEnlazada listaRecep) {
        this.listaRecep = listaRecep;
    }

    public void setListaSalida(ListaEnlazada listaSalida) {
        this.listaSalida = listaSalida;
    }
    public void addArcoSalida(Arco arco) {
        this.listaRecep.add(new ElementoLE<>(arco));
    }

    public void addArcoEntrada(Arco arco) {
        this.listaRecep.add(new ElementoLE<>(arco));
    }
}