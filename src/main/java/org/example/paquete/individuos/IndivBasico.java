package org.example.paquete.individuos;
import java.util.Random;
public class IndivBasico extends Individuo{
    public IndivBasico(int probRepro, int vida, int probClon, int id,   int posX, int posY) {
        this.setVida(vida);
        this.setProbRepro(probRepro);
        this.setProbClon(probClon);
        this.setId(id);
        this.setGeneration(1);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setTipo("Básico");
    }
        public void movimientoBasic(){
            Random numero1 = new Random();
            Random numero2 = new Random();
            int n1 = numero1.nextInt(3);
            int n2 = numero2.nextInt(3);
            if (n1 == 1){
                setPosX(getPosX() + 1);
            } else if (n1 == 2) {
                setPosX(getPosX() - 1);
            } else if (n1==0) {
                setPosX(getPosX() + 0);
            }
            if (n2 == 1){
                setPosY(getPosY() + 1);
            } else if (n2 == 2) {
                setPosY(getPosY() - 1);
            } else if (n2==0) {
                setPosY(getPosY() + 0);
            }
        }
    }

