package org.example.paquete.recursos;

public class Recurso {
    private int vida;
    private int posX;
    private int posY;

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    private int duracion;


    public Recurso(int vida, int posX, int posY) {
        this.vida = vida;
        this.posX = posX;
        this.posY = posY;
        this.duracion = 3;
    }

    public Recurso(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.duracion = 3;
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
    public String toString(){
        return ",turnos: " + duracion + "} ";
    }
}