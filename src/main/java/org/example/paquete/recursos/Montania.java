package org.example.paquete.recursos;

public class Montania extends Recurso{
    public Montania(int vida, int posX, int posY) {
        super(vida, posX, posY);
    }

    public String toString(){
    return "{Montaña= daño:" + getVida() + ", " + super.toString();
    }
    }
