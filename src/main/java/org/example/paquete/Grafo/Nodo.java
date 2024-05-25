package org.example.paquete.Grafo;

import org.example.paquete.ListaEnlazada.ListaEnlazada;

public class Nodo<T>{
    ///Arguments
    protected T data;
    protected ListaEnlazada listaRecep;
    protected ListaEnlazada listaSalida;

    ///Methods
    public Nodo(T dato){
        this.data = dato;
        this.listaRecep = new ListaEnlazada();
        this.listaSalida =new ListaEnlazada();
    }

    public T getDato() {
        return data;
    }

    public ListaEnlazada getListaRecep() {
        return listaRecep;
    }

    public ListaEnlazada getListaSalida() {
        return listaSalida;
    }

    public void setDato(T dato) {
        this.data = dato;
    }

    public void setListaRecep(ListaEnlazada listaRecep) {
        this.listaRecep = listaRecep;
    }

    public void setListaSalida(ListaEnlazada listaSalida) {
        this.listaSalida = listaSalida;
    }
    public void addArcoSalida(Arco arco) {
        this.listaSalida.add(arco);
    }

    public void addArcoEntrada(Arco arco) {
        this.listaRecep.add(arco);
    }
}
