package org.example.paquete.Grafo;

import org.example.paquete.individuos.Individuo;
import org.example.paquete.recursos.Recurso;

public class Operacion {
    private String beber = "beber";
    private String comer = "comer";
    private String biblio = "biblioteca";
    private String tesoro = "tesoro";
    private String pozo = "pozo";
    private String Montania = "montaña";
    private String Clonacion = "clonación";
    private Individuo Hijo;



    //constructores

    public Operacion (Recurso recurso){

    }


    //getters y setters

    public String getClonacion() {
        return Clonacion;
    }

    public void setClonación(String clonacion) {
        Clonacion = clonacion;
    }

    public String getBeber() {
        return beber;
    }

    public void setBeber(String beber) {
        this.beber = beber;
    }

    public String getComer() {
        return comer;
    }

    public void setComer(String comer) {
        this.comer = comer;
    }

    public String getBiblio() {
        return biblio;
    }

    public void setBiblio(String biblio) {
        this.biblio = biblio;
    }

    public String getTesoro() {
        return tesoro;
    }

    public void setTesoro(String tesoro) {
        this.tesoro = tesoro;
    }

    public String getPozo() {
        return pozo;
    }

    public void setPozo(String pozo) {
        this.pozo = pozo;
    }

    public String getMontania() {
        return Montania;
    }

    public void setMontania(String montania) {
        Montania = montania;
    }
}
