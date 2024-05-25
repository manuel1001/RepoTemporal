package org.example.paquete.individuos;

import static org.junit.jupiter.api.Assertions.*;

class IndividuoTest {

        @org.junit.jupiter.api.Test
        void getId() {
            Individuo i = new Individuo ();
            int c = i.getId();
            assertEquals(c, 0);
        }

        @org.junit.jupiter.api.Test
        void setId() {
            Individuo i = new Individuo ();
            i.setId(3);
            assertEquals(i.getId(), 3);
        }



        @org.junit.jupiter.api.Test
        void getVida() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            assertEquals(i.getVida(), 3);
        }

        @org.junit.jupiter.api.Test
        void setVida() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.setVida(5);
            assertEquals(i.getVida(), 5);
        }

        @org.junit.jupiter.api.Test
        void getProbRepro() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            assertEquals(i.getProbRepro(), 50);
        }

        @org.junit.jupiter.api.Test
        void setProbRepro() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.setProbRepro(60);
            assertEquals(i.getProbRepro(), 60);
            assertEquals(i.getProbMuerte(), 40);
        }

        @org.junit.jupiter.api.Test
        void getProbClon() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            assertEquals(i.getProbClon(), 50);
        }

        @org.junit.jupiter.api.Test
        void setProbClon() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.setProbClon(30);
            assertEquals(i.getProbClon(),30);
        }

        @org.junit.jupiter.api.Test
        void getProbMuerte() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            assertEquals(i.getProbMuerte(), 50);
        }

        @org.junit.jupiter.api.Test
        void setProbMuerte () {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.setProbMuerte(30);
            assertEquals(i.getProbMuerte(),30);
            assertEquals(i.getProbRepro(), 70);
        }


        @org.junit.jupiter.api.Test
        void restaProbRepro() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.restaProbRepro(75);
            assertEquals(i.getProbRepro(),0);
        }


        @org.junit.jupiter.api.Test
        void restaProbClon() {
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.restaProbClon(75);
            assertEquals(i.getProbClon(),0);
        }

        @org.junit.jupiter.api.Test
        void muerte(){
            Individuo i = new Individuo (1,1,3,50,50,50,1,1,"Normal");
            i.muerte();
            assertEquals(i.getVida(),0);
        }
    }