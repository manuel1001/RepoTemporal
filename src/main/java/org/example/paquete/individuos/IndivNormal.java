package org.example.paquete.individuos;

import org.example.paquete.ArbolBinario.ArbolBinario;
import org.example.paquete.ArbolBinario.ElementoA;

public class IndivNormal extends Individuo{
    public IndivNormal(int probRepro, int vida, int probClon, int id,  int posX, int posY ){
        this.setVida(vida);
        this.setProbRepro(probRepro);
        this.setProbClon(probClon);
        this.setId(id);
        this.setGeneration(1);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setTipo("Normal");
        this.setArbolGene(new ArbolBinario(new ElementoA(this.getId())));
    }
}
