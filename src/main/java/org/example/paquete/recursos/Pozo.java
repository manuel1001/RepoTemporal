package org.example.paquete.recursos;

public class Pozo extends Recurso {
    public Pozo(int vida, int posX, int posY) {
        super(vida, posX, posY);
    }

    public Pozo(int posX, int posY) {
        super(posX, posY);
    }

    public String toString() {
        return "{Pozo=" + super.toString();
    }
}

