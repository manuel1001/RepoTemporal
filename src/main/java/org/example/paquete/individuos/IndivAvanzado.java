package org.example.paquete.individuos;

public class IndivAvanzado extends Individuo{
    public IndivAvanzado(int probRepro, int vida, int probClon, int id, int posX, int posY ){
        this.setVida(vida);
        this.setProbRepro(probRepro);
        this.setProbClon(probClon);
        this.setId(id);
        this.setGeneration(1);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setTipo("Avanzado");
    }
}
