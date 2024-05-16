package org.example.paquete.recursos;

public class Tesoro extends Recurso{
    private int aumentoTesoro;
    public Tesoro(int aumentoTesoro,int posX, int posY) {
        super(posX, posY);
        this.aumentoTesoro = aumentoTesoro;
    }
    public String toString(){
        return "{Tesoro= aumento:" + aumentoTesoro + super.toString();
    }
}
