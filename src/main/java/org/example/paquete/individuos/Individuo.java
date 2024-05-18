package org.example.paquete.individuos;

import org.example.paquete.ListaEnlazada.ListaRecursos;
import org.example.paquete.recursos.Recurso;

import java.util.Random;

public class Individuo {
    private int id;
    private int generation;
    private int vida;
    private int probRepro;
    private int probClon;
    private int probMuerte = 100 - this.probRepro;
    private int posX;

    private int posY;

    public Recurso getRecursoObj() {
        return recursoObj;
    }

    public void setRecursoObj(Recurso recursoObj) {
        this.recursoObj = recursoObj;
    }

    public Recurso recursoObj;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    private String tipo;


    public Individuo() {
        id = 0;
        generation = 0;
        vida = 3;
        probRepro = 50;
        probClon = 50;

    }

    public Individuo(int probRepro, int vida, int probClon, int id) {
        this.vida = vida;
        this.probRepro = probRepro;
        this.probClon = probClon;
        this.id = id;
    }

    public Individuo(int id, int generation, int vida, int probRepro, int probClon, int probMuerte, int posX, int posY, String tipo) {
        this.id = id;
        this.generation = generation;
        this.vida = vida;
        this.probRepro = probRepro;
        this.probClon = probClon;
        this.probMuerte = probMuerte;
        this.posX = posX;
        this.posY = posY;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getProbRepro() {
        return probRepro;
    }

    public void setProbRepro(int probRepro) {
        this.probRepro = probRepro;
        probMuerte = 100 - this.probRepro;
    }

    public int getProbClon() {
        return probClon;
    }

    public void setProbClon(int probclon) {
        this.probClon = probclon;
    }

    public int getProbMuerte() {
        return probMuerte;
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


    public void setProbMuerte(int probMuerte) {
        this.probMuerte = probMuerte;
        probRepro = 100 - this.probMuerte;
    }

    public int sumaVida(int turnos) {
        vida = vida + turnos;
        return vida;
    }

    public int restaVida(int turnos) {
        vida = vida - turnos;
        if (vida < 0)
            vida = 0;
        return vida;
    }

    public int sumaProbRepro(int percent) {
        probRepro = probRepro + percent;
        if (probRepro > 100)
            probRepro = 100;
        return probRepro;
    }

    public int restaProbRepro(int percent) {
        probRepro = probRepro - percent;
        if (probRepro < 0)
            probRepro = 0;
        return probRepro;
    }

    public int sumaProbClon(int percent) {
        probClon = probClon + percent;
        if (probClon > 100)
            probClon = 100;
        return probClon;
    }

    public int restaProbClon(int percent) {
        probClon = probClon - percent;
        if (probClon < 0)
            probClon = 0;
        return probClon;
    }


    public void muerte() {
        vida = 0;
    }


    public void movimiento(int X, int Y) {
        this.posX = X;
        this.posY = Y;
    }

    @Override
    public String toString() {
        return "Individuo" + tipo + ":" +
                "id=" + id +
                ", generation=" + generation +
                ", vida=" + vida +
                ", probRepro=" + probRepro +
                ", probClon=" + probClon +
                ", probMuerte=" + probMuerte +
                '}';
    }

    public void movimientoBasic() {
        Random numero1 = new Random();
        Random numero2 = new Random();
        int n1 = numero1.nextInt(2);
        int n2 = numero2.nextInt(2);
        if (n1 == 1 && getPosX() < 8) {
            this.posX++;
            if (n2 == 1 && this.posY < 8) {
                this.posY++;
            } else if (n2 == 0 && this.posY > 1) {
                this.posY--;
            }
        } else if (n1 == 0 && this.posX > 1) {
            this.posX--;
            if (n2 == 1 && this.posY < 8) {
                this.posY++;
            } else if (n2 == 0 && this.posY > 1) {
                this.posY--;
            }
        }
    }
    public Boolean meClono(){
        Random rand = new Random();
        int n = rand.nextInt(101);
        return this.probClon >= n;
    }
}


