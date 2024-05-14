package org.example.paquete;

import org.example.paquete.ListaEnlazada.*;
import org.example.paquete.individuos.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import org.example.paquete.ListaEnlazada.*;
import org.example.paquete.individuos.Individuo;

import java.io.FileNotFoundException;


public class JuegoController implements GsonUtilEjemplo {
    ///Arguments
    @FXML
    private GridPane tablero;
    private String nombreJuego;
    private Partida modelo;
    private PartidaProperties propiedadesModelo;
    private ListaLabel listaLabels;

    ///Resto de elemenot FXML que iran conectados con el modelo
    @FXML
    private Label labelProbAgua;
    @FXML
    private Slider sliderProbAgua;
    @FXML
    private Label labelTurnAgua;
    @FXML
    private Slider sliderTurnAgua;
    @FXML
    private Label labelProbMont;
    @FXML
    private Slider sliderProbMont;
    @FXML
    private Label labelTurnMon;
    @FXML
    private Slider sliderTurnMont;
    @FXML
    private Label labelProbCom;
    @FXML
    private Slider sliderProbCom;
    @FXML
    private Label labelTurnCom;
    @FXML
    private Slider sliderTurnCom;
    @FXML
    private Label labelProbTesoro;
    @FXML
    private Slider sliderProbTesoro;
    @FXML
    private Label labelAumentoTesoro;
    @FXML
    private Slider sliderAumentoTesoro;
    @FXML
    private Label labelProbBiblio;
    @FXML
    private Slider sliderProbBiblio;
    @FXML
    private Label labelAumentoBiblio;
    @FXML
    private Slider sliderAumentoBiblio;
    @FXML
    private Label labelProbPozo;
    @FXML
    private Slider sliderProbPozo;
    ///Boolean para la pausa
    private Boolean pausado = true;
    @FXML
    private Button PausarButton;

    ///Interactuar con las posiciones
    @FXML
    private ChoiceBox<String> choiceAccion;
    private String[] opcionesAccion = {"Añadir", "Borrar"};
    @FXML
    private ChoiceBox<String> choiceClase;
    private String[] opcionesClase = {"Agua", "Comida", "Montaña", "Tesoro", "Biblioteca", "Pozo", "IndivBásico", "IndivNormal", "IndivAvanzado"};
    //Estas que vayan en el comenzarbutton
    @FXML
    private ChoiceBox<Integer> choicePosX;
    @FXML
    private ChoiceBox<Integer> choicePosY;
    ///Casillas
    private ListaCasilla listaCasillas = new ListaCasilla();
    private int contadorId;

    ///Methods
    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    @FXML
    public void onComenzarClick() {
        try {
            if (this.nombreJuego == null) {
                this.nombreJuego = "miLucha.json";
            }
            ///Iniciamos el modelo y sus properties desde le Json
            this.modelo = GsonUtilEjemplo.cargarObjetoDesdeArchivo(nombreJuego, Partida.class);
            propiedadesModelo = new PartidaProperties(modelo);
            sliderAumentoBiblio.valueProperty().bindBidirectional(propiedadesModelo.aumentoBiblioProperty());
            sliderAumentoTesoro.valueProperty().bindBidirectional(propiedadesModelo.aumentoTesoroProperty());
            sliderProbAgua.valueProperty().bindBidirectional(propiedadesModelo.aparicionAguaProperty());
            sliderProbBiblio.valueProperty().bindBidirectional(propiedadesModelo.aparicionBiblioProperty());
            sliderProbPozo.valueProperty().bindBidirectional(propiedadesModelo.aparicionPozoProperty());
            sliderProbCom.valueProperty().bindBidirectional(propiedadesModelo.aparicionComidaProperty());
            sliderProbMont.valueProperty().bindBidirectional(propiedadesModelo.aparicionMontaniaProperty());
            sliderProbTesoro.valueProperty().bindBidirectional(propiedadesModelo.aparicionTesoroProperty());
            sliderTurnAgua.valueProperty().bindBidirectional(propiedadesModelo.turnosAguaProperty());
            sliderTurnCom.valueProperty().bindBidirectional(propiedadesModelo.turnosComidaProperty());
            sliderTurnMont.valueProperty().bindBidirectional(propiedadesModelo.turnosMontaniaProperty());
            this.contadorId = modelo.getListaInicialIndividuos().getNumeroElementos() + 1;
            System.out.println("Recibido:" + modelo.getArchivoNombre());
            ///Los choices que dependen de modelo
            int contador = 1;
            while (contador <= modelo.getNumeroFilas()) {
                choicePosX.getItems().add(contador);
                contador++;
            }
            int contador2 = 1;
            while (contador2 <= modelo.getNumeroColumnas()) {
                choicePosY.getItems().add(contador2);
                contador2++;
            }

            ///Creamos el gridPane
            int x = modelo.getNumeroFilas();
            int y = modelo.getNumeroColumnas();
            System.out.println(x + "," + y);
            listaLabels = new ListaLabel();
            for (int i = 1; i < x + 1; i++) {
                for (int j = 1; j < y + 1; j++) {
                    Label labelcasilla = new Label("");
                    labelcasilla.setMinSize(15, 15);
                    labelcasilla.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                    tablero.add(labelcasilla, j, i);
                    listaLabels.add(new ElementoLabel(labelcasilla));
                    listaCasillas.add(new ElementoCasilla(new Casilla(j, i)));
                }
            }
            int x2 = modelo.getListaInicialIndividuos().getNumeroElementos();
            for (int i = 0; i < x2; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                int posx = ind.getPosX();
                int posy = ind.getPosY();
                listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().addIndividuo(ind);
                listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText("I");
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    ///Metodo para buscar conseguir las labels de la lista en funcion de la posición
    public int conversorPosicion(int x, int y) {
        return x - 1 + ((y - 1) * modelo.getNumeroFilas());
    }

    public void initialize() {
        labelProbMont.textProperty().bind(sliderProbMont.valueProperty().asString());
        labelTurnMon.textProperty().bind(sliderTurnMont.valueProperty().asString());
        labelProbCom.textProperty().bind(sliderProbCom.valueProperty().asString());
        labelTurnCom.textProperty().bind(sliderTurnCom.valueProperty().asString());
        labelProbTesoro.textProperty().bind(sliderProbTesoro.valueProperty().asString());
        labelAumentoTesoro.textProperty().bind(sliderAumentoTesoro.valueProperty().asString());
        labelProbBiblio.textProperty().bind(sliderProbBiblio.valueProperty().asString());
        labelAumentoBiblio.textProperty().bind(sliderAumentoBiblio.valueProperty().asString());
        labelProbPozo.textProperty().bind(sliderProbPozo.valueProperty().asString());
        labelProbAgua.textProperty().bind(sliderProbAgua.valueProperty().asString());
        labelTurnAgua.textProperty().bind(sliderTurnAgua.valueProperty().asString());
        PausarButton.setText("Reanudar");
        ///Choicebox linkeadas
        choiceAccion.getItems().addAll(opcionesAccion);
        choiceClase.getItems().addAll(opcionesClase);
    }

    @FXML
    public void onActualizarClick() throws NullPointerException {
        try {
            if (pausado) {
                propiedadesModelo.commit();
                System.out.println(modelo);
            } else {
                System.out.println("Pausa primero");
            }
        } catch (NullPointerException ex) {
            System.out.println("Comienza primero");
        }
    }

    @FXML
    public void onPausarContinuarClick() throws InterruptedException {
        ///NullPointer
//        if (pausado){
//            pausado = false;
//            PausarButton.setText("Pausar");
//            int x3 = modelo.getListaInicialIndividuos().getNumeroElementos();
//            while(!pausado){
//            if (x3 >= 1) {
//                for (int i = 0; i < x3; i++) {
//                    Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
//                    if (ind.getVida() == 0) {
//                        modelo.getListaInicialIndividuos().del(i);
//                        int posx = ind.getPosX();
//                        int posy = ind.getPosY();
//                        listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().delIndividuo(ind);
//                        listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().replaceFirst("I", ""));
//                        i--;
//                        x3--;
//                    } else {
//                        int posx = ind.getPosX();
//                        int posy = ind.getPosY();
//                        listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().delIndividuo(ind);
//                        listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().replaceFirst("I", ""));
//                        if(ind.getTipo().equals("Básico")) {
//                            ind.movimientoBasic();
//                            int posx2 = ind.getPosX();
//                            int posy2 = ind.getPosY();
//                            listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
//                            listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
//                            System.out.println("Iterando");
//                            ind.setVida(ind.getVida() - 1);
//                            if (ind.meClono()) {
//                                System.out.println("Clonando");
//                                Individuo ind2 = ind;
//                                ind2.setGeneration(ind.getGeneration() + 1);
//                                ind2.setId(contadorId);
//                                contadorId++;
//                                listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind2);
//                                listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
//                            }
//                        }
//                    }
//                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                System.out.println("Fin del juego");
//                pausado=true;
//            }}
//
//        }
//        else if (!pausado){
//            pausado = true;
//            PausarButton.setText("Reanudar");
//        }
    }

    public void onAvanzarButton() {
        ///NullPointer
        int x3 = modelo.getListaInicialIndividuos().getNumeroElementos();
        if (x3 >= 1) {
            for (int i = 0; i < x3; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                int posx = ind.getPosX();
                int posy = ind.getPosY();
                if (ind.getVida() == 0) {
                    modelo.getListaInicialIndividuos().del(i);
                    listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().delIndividuo(ind);
                    listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().replaceFirst("I", ""));
                    i--;
                    x3--;
                } else {
                    listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().delIndividuo(ind);
                    listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().replaceFirst("I", ""));
                    if (ind.getTipo().equals("Básico")) {
                        ind.movimientoBasic();
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                        System.out.println("Iterando");
                        ind.setVida(ind.getVida() - 1);
                        if (ind.meClono()) {
                            System.out.println("Clonando");
                            Individuo ind2 = new Individuo(contadorId, ind.getGeneration() + 1, ind.getVida(), ind.getProbRepro(), ind.getProbClon(), ind.getProbMuerte(), posx2, posy2, ind.getTipo());
                            contadorId++;
                            modelo.getListaInicialIndividuos().add(new ElementoInd(ind2));
                            listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind2);
                            listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                        }
                    }
                }
            }
        } else {
            System.out.println("Fin del juego");
        }

    }

    public void onBucleOrden() {
        int x3 = modelo.getListaInicialIndividuos().getNumeroElementos();
        ///Comprobamos si ha terminado la partida
        if (x3 >= 1) {
            ///1.Actualizamos el tiempo de vida de cada individuo
            for (int i = 0; i < x3; i++) {
                Individuo ind1 = modelo.getListaInicialIndividuos().getElemento(i).getData();
                if (ind1.getVida() == 0) {
                    int posx1 = ind1.getPosX();
                    int posy1 = ind1.getPosY();
                    modelo.getListaInicialIndividuos().del(i);
                    listaCasillas.getElemento(conversorPosicion(posx1, posy1)).getData().delIndividuo(ind1);
                    listaLabels.getElemento(conversorPosicion(posx1, posy1)).getData().setText(listaLabels.getElemento(conversorPosicion(posx1, posy1)).getData().getText().replaceFirst("I", ""));
                    i--;
                    x3--;
                } else {
                    ind1.setVida(ind1.getVida() - 1);
                }
            }
            ///2. Para cada recurso se evalua si sigue activo o debe eliminarse
            ///
            ///3. Movimiento
            int x4 = modelo.getListaInicialIndividuos().getNumeroElementos();
            for (int i = 0; i < x4; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                int posx = ind.getPosX();
                int posy = ind.getPosY();
                listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().delIndividuo(ind);
                listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().replaceFirst("I", ""));
                if (ind.getTipo().equals("Básico")) {
                    ind.movimientoBasic();
                    int posx2 = ind.getPosX();
                    int posy2 = ind.getPosY();
                    listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                    listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                    System.out.println("Movimiento");
                }
            }
            ///4. Mejoras obtenidas por los recursos de la posicion nueva
            //
            ///5. ¿Reproducciones?
            //
            ///6. ¿Clonaciones?
            for (int i = 0; i < x4; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                if (ind.meClono()) {
                    int posx2 = ind.getPosX();
                    int posy2 = ind.getPosY();
                    System.out.println("Clonando");
                    Individuo ind2 = new Individuo(contadorId, ind.getGeneration() + 1, ind.getVida(), ind.getProbRepro(), ind.getProbClon(), ind.getProbMuerte(), posx2, posy2, ind.getTipo());
                    contadorId++;
                    modelo.getListaInicialIndividuos().add(new ElementoInd(ind2));
                    listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind2);
                    listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                }
            }
            ///7. Máximo tres en cada casilla
            int numCasillas = listaCasillas.getNumeroElementos();
            System.out.println(numCasillas);
            for (int l = 0; l < numCasillas; l++) {
                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos() > 3) {
                    System.out.println("más de 3 aquí");
                    while (listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos() > 3) {
                        int posEliminado = 0;
                        int vidaMinima = 10000;
                        for (int m = 0; m < listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos(); m++) {
                            if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getVida() < vidaMinima) {
                                posEliminado = m;
                                vidaMinima = listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getVida();
                            }
                        }
                        ///Borramos de la lista de individuos, de la casilla, y por ultimo del label
                        modelo.getListaInicialIndividuos().del(modelo.getListaInicialIndividuos().getPosicion(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(posEliminado).getData()));
                        listaCasillas.getElemento(l).getData().getListaIndividuos().del(posEliminado);
                        listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("I", ""));
                    }
                }
            }
            ///8. ¿Nuevos recursos?
        }

        else{
            System.out.println("fin partida");
        }
    }
}
