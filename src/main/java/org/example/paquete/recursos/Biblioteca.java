package org.example.paquete.recursos;

public class Biblioteca extends Recurso{
    private int aumentoBiblio;
    public Biblioteca(int aumentoBiblio, int posX, int posY) {
        super(posX, posY);
        this.aumentoBiblio = aumentoBiblio;
        this.setId("B");
    }
    public String toString(){
        return "{Biblio= aumento:" + aumentoBiblio + super.toString();
    }
}
