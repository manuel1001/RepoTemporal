package org.example.paquete;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
import org.example.paquete.recursos.Agua;
import org.example.paquete.recursos.*;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;


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
    @FXML
    private ChoiceBox<Integer> choiceId;
    @FXML
    private Label labelInfoInterac;
    ///Casillas
    private ListaCasilla listaCasillas = new ListaCasilla();
    private int contadorId;
    @FXML
    private Label labelIndLayout;
    @FXML
    private Label labelRecLayout;
    private int contadorTurno = 0;

    ///Methods
    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    @FXML
    public void onComenzarClick() {
        try {
            if (this.nombreJuego == null) {
                this.nombreJuego = "normalitos.json";
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
                    labelcasilla.setMinSize(23, 15);
                    labelcasilla.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                    labelcasilla.setOnMouseClicked(this::handleClick);
                    tablero.add(labelcasilla, j, i);
                    listaLabels.add(new ElementoLabel(labelcasilla));
                    listaCasillas.add(new ElementoCasilla(new Casilla(j, i)));
                }
            }
            ///Añadimos los individuos a cada casilla, y lo representamos en las labels
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

    @FXML
    private void handleClick(MouseEvent event) {
        Label etiqueda = (Label) event.getSource();
        boolean exit = false;
        int pos = 0;
        while (!exit) {
            if (listaLabels.getElemento(pos).getData() == etiqueda) {
                exit = true;
            }
            pos++;
        }
        pos = pos - 1;
        System.out.println("Posicion:" + pos);
        String indLayout = "";
        if (listaCasillas.getElemento(pos).getData().getListaIndividuos().getNumeroElementos() > 0) {
            for (int i = 0; i < listaCasillas.getElemento(pos).getData().getListaIndividuos().getNumeroElementos(); i++) {
                indLayout = indLayout + listaCasillas.getElemento(pos).getData().getListaIndividuos().getElemento(i).getData().toString();
            }
        }
        labelIndLayout.setText(indLayout);
        String recLayout = "";
        if (listaCasillas.getElemento(pos).getData().getListaRecursos().getNumeroElementos() > 0) {
            for (int i = 0; i < listaCasillas.getElemento(pos).getData().getListaRecursos().getNumeroElementos(); i++) {
                recLayout = recLayout + listaCasillas.getElemento(pos).getData().getListaRecursos().getElemento(i).getData().toString();
            }
        }
        labelRecLayout.setText(recLayout);
        System.out.println("Casilla clicada=" + listaCasillas.getElemento(pos).getData().getPosY() + "," + listaCasillas.getElemento(pos).getData().getPosX());
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


    public void onBucleOrden() {
        int x3 = modelo.getListaInicialIndividuos().getNumeroElementos();
        ///Comprobamos si ha terminado la partida
        if (x3 > 1) {
            ///1.Actualizamos el tiempo de vida de cada individuo
            for (int i = 0; i < x3; i++) {
                Individuo ind1 = modelo.getListaInicialIndividuos().getElemento(i).getData();
                if (ind1.getVida() <= 0) {
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
//            /2. Para cada recurso se evalua si sigue activo o debe eliminarse
            int numCasillas = listaCasillas.getNumeroElementos();
            for (int l = 0; l < numCasillas; l++) {
                int cuenta = listaCasillas.getElemento(l).getData().getListaRecursos().getNumeroElementos();
                if (cuenta != 0) {
                    for (int h = 0; h < cuenta; h++) {
                        if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData().getDuracion() <= 0) {
                            if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Agua) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("A", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Tesoro) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("T", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Biblioteca) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("B", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Comida) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("C", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Montania) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("M", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Pozo) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("P", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                            }
                        } else {
                            listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData().setDuracion(listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData().getDuracion() - 1);
                        }
                    }
                }
            }
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
                }
                if (ind.getTipo().equals("Normal")) {
//                    /Comprobamos que el objetivo recurso se valido
                    if (contadorTurno != 0) {
                        if (ind.getRecursoObj() == null || ind.getRecursoObj().getDuracion() == 0 || (ind.getRecursoObj().getPosY() == ind.getPosY() && ind.getRecursoObj().getPosX() == ind.getPosX())) {
                            boolean salir = false;
                            Random random = new Random();
                            while (!salir) {
                                int selec = random.nextInt(listaCasillas.getNumeroElementos());
                                if (listaCasillas.getElemento(selec).getData().getListaRecursos().getNumeroElementos() != 0 && (ind.getPosX() != listaCasillas.getElemento(selec).getData().getPosX() || ind.getPosY() != listaCasillas.getElemento(selec).getData().getPosY())) {
                                    int posRec = random.nextInt(listaCasillas.getElemento(selec).getData().getListaRecursos().getNumeroElementos());
                                    ind.setRecursoObj(listaCasillas.getElemento(selec).getData().getListaRecursos().getElemento(posRec).getData());
                                    salir = true;
                                }
                                System.out.println("Buclenormal");
                            }
                        }
                        ///Ahora nos movemos hacia él
                        if (ind.getRecursoObj().getPosX() < ind.getPosX()) {
                            ind.setPosX(ind.getPosX() - 1);
                        }
                        if (ind.getRecursoObj().getPosX() > ind.getPosX()) {
                            ind.setPosX(ind.getPosX() + 1);
                        }
                        if (ind.getRecursoObj().getPosY() > ind.getPosY()) {
                            ind.setPosX(ind.getPosX() + 1);
                        }
                        if (ind.getRecursoObj().getPosY() < ind.getPosY()) {
                            ind.setPosX(ind.getPosX() - 1);
                        }
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                    } else {
                        ind.movimientoBasic();
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                        System.out.println("Me muevo basico siendo normal");
                    }
                }
            }
            ///4. Mejoras obtenidas por los recursos de la posicion nueva
            for (int i = 0; i < x4; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                int posx = ind.getPosX();
                int posy = ind.getPosY();
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("A")) {
                    ind.setVida(ind.getVida() + modelo.getTurnosAgua());
                    System.out.println("Bebe");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("C")) {
                    ind.setVida(ind.getVida() + modelo.getTurnosComida());
                    System.out.println("come");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("B")) {
                    ind.setProbClon(ind.getProbClon() + modelo.getAumentoBiblio());
                    System.out.println("Encuentra bilbio");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("T")) {
                    ind.setProbRepro(ind.getProbRepro() + modelo.getAumentoTesoro());
                    System.out.println("Encuentra tesoro");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("P")) {
                    ind.setVida(0);
                    System.out.println("Cae en pozo");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("M")) {
                    ind.setVida(ind.getVida() - modelo.getTurnosMontania());
                    System.out.println("choque montaña");
                }
            }
            ///5. ¿Reproducciones?
            //
            ///6. ¿Clonaciones?
            for (int i = 0; i < x4; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                if (ind.meClono()) {
                    int posx2 = ind.getPosX();
                    int posy2 = ind.getPosY();

                    Individuo ind2 = new Individuo(contadorId, ind.getGeneration() + 1, ind.getVida(), ind.getProbRepro(), ind.getProbClon(), ind.getProbMuerte(), posx2, posy2, ind.getTipo());
                    contadorId++;
                    modelo.getListaInicialIndividuos().add(new ElementoInd(ind2));
                    listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind2);
                    listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                }
            }
            ///7. Máximo tres en cada casilla
            for (int l = 0; l < numCasillas; l++) {
                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos() > 3) {
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
            for (int l = 0; l < numCasillas; l++) {
                if (listaCasillas.getElemento(l).getData().getListaRecursos().getNumeroElementos() < 3) {
                    Random rand = new Random();
                    int ruleta = rand.nextInt(101);
                    if (modelo.getAparicionRecurso() >= ruleta) {
                        int ruleta2 = rand.nextInt(6);
                        boolean exit = false;
                        while (!exit) {
                            if (ruleta2 == 0 && modelo.getAparicionAgua() >= ruleta) {
                                Agua agua = new Agua(modelo.getTurnosAgua(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elAgua = new ElementoRec(agua);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elAgua);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "A");
                                exit = true;
                            } else if (ruleta2 == 1 && modelo.getAparicionPozo() >= ruleta) {
                                Pozo pozo = new Pozo(listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elPozo = new ElementoRec(pozo);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elPozo);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "P");
                                exit = true;
                            } else if (ruleta2 == 2 && modelo.getAparicionComida() >= ruleta) {
                                Comida comida = new Comida(modelo.getTurnosAgua(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elComida = new ElementoRec(comida);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elComida);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "C");
                                exit = true;
                            } else if (ruleta2 == 3 && modelo.getAparicionMontania() >= ruleta) {
                                Montania montania = new Montania(modelo.getTurnosMontania(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elMontania = new ElementoRec(montania);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elMontania);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "M");
                                exit = true;
                            } else if (ruleta2 == 4 && modelo.getAparicionBiblio() >= ruleta) {
                                Biblioteca biblio = new Biblioteca(modelo.getAumentoBiblio(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elBiblio = new ElementoRec(biblio);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elBiblio);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "B");
                                exit = true;
                            } else if (ruleta2 == 5 && modelo.getAparicionBiblio() >= ruleta) {
                                Tesoro tesoro = new Tesoro(modelo.getAumentoTesoro(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elTesoro = new ElementoRec(tesoro);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elTesoro);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "T");
                                exit = true;
                            } else {
                                ///Si no se ha generado ninguno, se sortea de nuevo.
                                ruleta = rand.nextInt(101);
                                ruleta2 = rand.nextInt(6);
                            }
                        }
                    }
                }
            }
            ///Cosas secuenciales
            contadorTurno++;
            choiceId.getItems().clear();
            for (int p = 0; p < modelo.getListaInicialIndividuos().getNumeroElementos(); p++) {
                choiceId.getItems().add(modelo.getListaInicialIndividuos().getElemento(p).getData().getId());
            }
        } else {
            System.out.println("fin partida");
        }
    }

    public void onInteractuarClick() {
        try {
            if (choiceAccion.getValue().equals("Añadir")) {
                ///Si es un recurso habrá que comprobar que no haya tres ya en esa casilla
                if (choiceClase.getValue().equals("Agua") || choiceClase.getValue().equals("Comida") || choiceClase.getValue().equals("Montaña") || choiceClase.getValue().equals("Tesoro")
                        || choiceClase.getValue().equals("Biblioteca") || choiceClase.getValue().equals("Pozo")) {
                    if (listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos() < 3) {
                        ///Un caso para cada posibilidad de añadir
                        if (choiceClase.getValue().equals("Agua")) {
                            Agua agua = new Agua(modelo.getTurnosAgua(), choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(agua));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "A");
                        } else if (choiceClase.getValue().equals("Comida")) {
                            Comida comida = new Comida(modelo.getTurnosComida(), choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(comida));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "C");
                        } else if (choiceClase.getValue().equals("Montaña")) {
                            Montania montania = new Montania(modelo.getTurnosMontania(), choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(montania));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "M");
                        } else if (choiceClase.getValue().equals("Pozo")) {
                            Pozo pozo = new Pozo(choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(pozo));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "P");
                        }
                        labelInfoInterac.setText("Recurso añadido a la posición: " + choicePosX.getValue() + "," + choicePosY.getValue());
                    } else {
                        labelInfoInterac.setText("La casilla ya está llena de recursos");
                    }
                }
//                if(choiceClase.getValue().equals("IndivBásico")){
//                    IndivBasico ind = new IndivBasico(modelo.get)
//
//                }
            }
        } catch (NullPointerException exception) {
            labelInfoInterac.setText("Error, por favor haga una selección válida");
        }

    }
}