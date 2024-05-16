package org.example.paquete.recursos;

public class Agua extends Recurso {

    public Agua(int vida, int posX, int posY) {
        super(vida, posX, posY);
    }
    public String toString(){
        return "{Agua= cura:" + getVida() + ", " + super.toString();
    }
}

