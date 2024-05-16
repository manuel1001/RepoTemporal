package org.example.paquete.recursos;

public class Comida extends Recurso{
    public Comida(int vida, int posX, int posY) {
        super(vida, posX, posY);
    }
    public String toString(){
        return "{Comida= cura:" + getVida() + ", " + super.toString();
    }
}


