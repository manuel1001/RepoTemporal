package org.example.paquete.individuos;

import org.example.paquete.ArbolBinario.ArbolBinario;
import org.example.paquete.ArbolBinario.ElementoA;
import org.example.paquete.Grafo.Cola;
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
    private Cola colaAccion;
    private ArbolBinario arbolGene;

    private int posY;
    ///El argumento arbolGenealógico surge como necesidad

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
        this.colaAccion = new Cola();
    }

    public Individuo(int probRepro, int vida, int probClon, int id) {
        this.vida = vida;
        this.probRepro = probRepro;
        this.probClon = probClon;
        this.id = id;
        this.colaAccion = new Cola();
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
        this.setArbolGene(new ArbolBinario(new ElementoA(this.getId())));
        this.colaAccion = new Cola();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Cola getColaAccion() {
        return colaAccion;
    }

    public void setColaAccion(Cola colaAccion) {
        this.colaAccion = colaAccion;
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
    public int restaProbRepro(int percent) {
        probRepro = probRepro - percent;
        if (probRepro < 0)
            probRepro = 0;
        return probRepro;
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


    public void movimiento(int x, int y) {
        this.posX = this.posX +x;
        this.posY = this.posY +y;
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

    public void movimientoBasic(int columnas, int filas) {
        Random numero1 = new Random();
        Random numero2 = new Random();
        boolean movido = false;
        while (!movido) {
            int n1 = numero1.nextInt(2);
            int n2 = numero2.nextInt(2);
            if (n1 == 1 && getPosX() < columnas) {
                this.posX++;
                if (n2 == 1 && this.posY < filas) {
                    this.posY++;
                } else if (n2 == 0 && this.posY > 1) {
                    this.posY--;
                }
                movido = true;
            } else if (n1 == 0 && this.posX > 1) {
                this.posX--;
                if (n2 == 1 && this.posY < filas) {
                    this.posY++;
                } else if (n2 == 0 && this.posY > 1) {
                    this.posY--;
                }
                movido = true;
            }
        }
    }
    public Boolean meClono(){
        Random rand = new Random();
        int n = rand.nextInt(101);
        return this.probClon >= n;
    }
    public ArbolBinario getArbolGene(){
        return this.arbolGene;
    }
    public void setArbolGene(ArbolBinario arbolGene){
        this.arbolGene = arbolGene;
    }
}


