package org.example.paquete.recursos;

public class Recurso {
    private int vida;
    private int posX;
    private int posY;


    public Recurso(int vida, int posX, int posY) {
        this.vida = vida;
        this.posX = posX;
        this.posY = posY;
    }

    public int getTurnos() {
        return vida;
    }

    public void setTurnos(int turnos) {
        this.vida = turnos;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}