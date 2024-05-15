package org.example.paquete.ListaEnlazada;


import org.example.paquete.ListaEnlazada.ElementoRec;

public class ListaRecursos {
    ///Arguments
    private ElementoRec primero;

    ///Methods
    public ListaRecursos(ElementoRec primero) {
        this.primero = primero;
    }
    public ListaRecursos() {
        this.primero = null;
    }

    public int getNumeroElementos() {
        if (primero == null) {
            return 0;
        } else {
            int cont = 1;
            ElementoRec inicial = primero;
            while (inicial.getSiguiente() != null) {
                cont++;
                inicial = inicial.getSiguiente();
            }
            return cont;
        }

    }

    public boolean isVacia() {
        boolean vacio = primero == null;
        return vacio;
    }

    public void vaciar() {
        while (primero != null) {
            ElementoRec ele = primero.getSiguiente();
            primero = null;
            primero = ele;
        }
    }

    public void add(ElementoRec el) {
        ///Mete un elemento al final de la lista
        if (this.isVacia()) {
            primero = el;
        } else {
            this.getUltimo().setSiguiente(el);

        }
    }




    public void insert(ElementoRec el, int posicion) {
        if (posicion >= 0 && posicion <= getNumeroElementos()) {
            if (posicion == 0) {
                el.setSiguiente(primero);
                primero = el;
            } else if (posicion == getNumeroElementos()) {
                this.getUltimo().setSiguiente(el);
            } else { ///Si la posición ya está ocupada, desplaza ese elemento hacia detrás
                el.setSiguiente(getElemento(posicion));
                this.getElemento(posicion - 1).setSiguiente(el);
            }
        } else {
            System.out.println("error");
        }
    }

    public void del(int posicion) {
        if (posicion >= 0 && posicion <= getNumeroElementos() - 1) {
            if (posicion == 0) {
                primero = primero.getSiguiente();
            } else {
                this.getElemento(posicion - 1).setSiguiente(this.getElemento(posicion).getSiguiente());
            }
        }
    }

    public int getPosicion(ElementoRec el) {
        int contador = 0;
        while (this.getElemento(contador) != el && contador<this.getNumeroElementos()) {
            contador++;
        }
        if(contador == this.getNumeroElementos()){
            System.out.println("error");
            return Integer.parseInt(null);
        }
        else{
            return contador;
        }
    }
    public int getPosicion(Object el) {
        int contador = 0;
        while (this.getElemento(contador).getData() != el && contador<this.getNumeroElementos()) {
            contador++;
        }
        if(contador == this.getNumeroElementos()){
            System.out.println("error");
            return Integer.parseInt(null);
        }
        else{
            return contador;
        }

    }

    public ElementoRec getPrimero() {
        return primero;
    }

    public ElementoRec getUltimo() {
        if (isVacia()) {
            return null;
        }
        return this.getElemento(this.getNumeroElementos() - 1);
    }

    public ElementoRec getSiguiente(ElementoRec el) {
        int contador = 0;
        while (contador< this.getNumeroElementos()){
            if (this.getElemento(contador) == el){
                return el.getSiguiente();
            }
            contador++;
        }
        System.out.println("error");
        return null;
    }

    public ElementoRec getElemento(int posicion){
        if (posicion >= 0 && posicion <= getNumeroElementos()-1){
            if(posicion == 0){
                return primero;
            }
            else{
                int contador = 0;
                ElementoRec objetivo = primero;
                while (contador != posicion){
                    objetivo = objetivo.getSiguiente();
                    contador++;
                }
                return objetivo;
            }
        }
        return null;
    }

    public void imprimir() {
        int c = 0;
        while (c < this.getNumeroElementos()) {
            System.out.println(this.getElemento(c));
            c++;
        }
    }
}
