package org.example.paquete.Grafo;

import org.example.paquete.individuos.Individuo;
import org.example.paquete.recursos.Recurso;

public class Operacion {
    private Recurso r;
    private Individuo i;
    private String Tipo;
    //constructores

    public Operacion (Recurso r){
        this.r = r;
        Tipo = "recurso";
    }

    public Operacion ( Individuo i){
        this.i = i;
        Tipo = "individuo";
    }

    public Recurso getR() {
        return r;
    }
    public void setR(Recurso r) {
        this.r = r;
    }

    public Individuo getI() {
        return i;
    }

    public void setI(Individuo i) {
        this.i = i;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
