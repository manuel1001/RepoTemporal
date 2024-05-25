package org.example.paquete.individuos;

import org.example.paquete.ArbolBinario.ArbolBinario;
import org.example.paquete.ArbolBinario.ElementoA;
import org.example.paquete.Grafo.Cola;

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
        this.setArbolGene(new ArbolBinario(new ElementoA(this.getId())));
        this.setColaAccion(new Cola());
    }
}
