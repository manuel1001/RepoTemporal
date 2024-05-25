package org.example.paquete;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.Logger;
import org.example.paquete.ArbolBinario.ArbolBinario;
import org.example.paquete.ArbolBinario.ElementoA;
import org.example.paquete.ListaEnlazada.*;
import org.example.paquete.individuos.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.example.paquete.individuos.Individuo;
import org.example.paquete.recursos.Agua;
import org.example.paquete.recursos.*;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JuegoController implements GsonUtilEjemplo {
    ///Logs

    private static final Logger log = LogManager.getLogger(JuegoController.class);
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
    @FXML
    private Slider sliderTurnosVida;
    @FXML
    private Slider sliderProbClon;
    @FXML
    private Slider sliderProbRepro;
    @FXML
    private Slider sliderMejoraTipo;
    ///Boolean para no comenzar dos veces
    private boolean comenzado = false;

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
    @FXML
    private Label labelClickCasilla;
    @FXML
    private Label labelTurnoLayout;
    ///Casillas
    private ListaCasilla listaCasillas = new ListaCasilla();
    private int contadorId;
    @FXML
    private Label labelIndLayout;
    @FXML
    private Label labelRecLayout;
    private int contadorTurno = 0;
    ///Ajustes Guardar Partida
    @FXML
    private Label labelGuardadaPartida;
    @FXML
    private TextField fieldGuardadaParitda;
    @FXML
    private TextArea areaArbol;

    ///Methods
    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    @FXML
    public void onComenzarClick() {
        log.info("Inicio del método de arranque de la aplicación para mostrar un grid de forma programática");
        if (!comenzado) {
            try {
                if (this.modelo == null) {
                    this.modelo = GsonUtilEjemplo.cargarObjetoDesdeArchivo(nombreJuego, Partida.class);
                }
                ///Iniciamos el modelo y sus properties desde le Json
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
                log.info("GridPane creado");
                ///Añadimos los individuos a cada casilla, y lo representamos en las labels
                int x2 = modelo.getListaInicialIndividuos().getNumeroElementos();
                for (int i = 0; i < x2; i++) {
                    Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                    int posx = ind.getPosX();
                    int posy = ind.getPosY();
                    listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().addIndividuo(ind);
                    listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText("I");
                }
                log.info("Individuos añadidos al Gridpane");
                ///Añadimos los recursos si existian
                if(modelo.getTodosRecuros() != null){
                    int x3 = modelo.getTodosRecuros().getNumeroElementos();
                    for(int m = 0; m < x3; m++){
                        if (modelo.getTodosRecuros().getElemento(m).getData().getId().equals("A")){
                            Agua agua = new Agua(modelo.getTodosRecuros().getElemento(m).getData().getVida(),modelo.getTodosRecuros().getElemento(m).getData().getPosX(), modelo.getTodosRecuros().getElemento(m).getData().getPosY());
                            int posx = agua.getPosX();
                            int posy = agua.getPosY();
                            listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().getListaRecursos().add(new ElementoRec(agua));
                            listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText() + "A");
                        }
                        else if (modelo.getTodosRecuros().getElemento(m).getData().getId().equals("C")){
                            Comida comida = new Comida(modelo.getTodosRecuros().getElemento(m).getData().getVida(),modelo.getTodosRecuros().getElemento(m).getData().getPosX(), modelo.getTodosRecuros().getElemento(m).getData().getPosY());
                            int posx = comida.getPosX();
                            int posy = comida.getPosY();
                            listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().getListaRecursos().add(new ElementoRec(comida));
                            listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText() + "C");
                        }
                        else if (modelo.getTodosRecuros().getElemento(m).getData().getId().equals("M")){
                            Montania mont = new Montania(modelo.getTodosRecuros().getElemento(m).getData().getVida(),modelo.getTodosRecuros().getElemento(m).getData().getPosX(), modelo.getTodosRecuros().getElemento(m).getData().getPosY());
                            int posx = mont.getPosX();
                            int posy = mont.getPosY();
                            listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().getListaRecursos().add(new ElementoRec(mont));
                            listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText() + "M");
                        }
                        else if (modelo.getTodosRecuros().getElemento(m).getData().getId().equals("T")){
                            Tesoro tesoro = new Tesoro(modelo.getAumentoTesoro(),modelo.getTodosRecuros().getElemento(m).getData().getPosX(), modelo.getTodosRecuros().getElemento(m).getData().getPosY());
                            int posx = tesoro.getPosX();
                            int posy = tesoro.getPosY();
                            listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().getListaRecursos().add(new ElementoRec(tesoro));
                            listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText() + "T");
                        }
                        else if (modelo.getTodosRecuros().getElemento(m).getData().getId().equals("B")){
                            Biblioteca bilbio = new Biblioteca(modelo.getAumentoBiblio(), modelo.getTodosRecuros().getElemento(m).getData().getPosX(), modelo.getTodosRecuros().getElemento(m).getData().getPosY());
                            int posx = bilbio.getPosX();
                            int posy = bilbio.getPosY();
                            listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().getListaRecursos().add(new ElementoRec(bilbio));
                            listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText() + "B");
                        }
                        else if (modelo.getTodosRecuros().getElemento(m).getData().getId().equals("P")){
                            Pozo pozo = new Pozo(modelo.getTodosRecuros().getElemento(m).getData().getPosX(), modelo.getTodosRecuros().getElemento(m).getData().getPosY());
                            int posx = pozo.getPosX();
                            int posy = pozo.getPosY();
                            listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().getListaRecursos().add(new ElementoRec(pozo));
                            listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText() + "P");
                        }
                    }
                    log.info("Había recursos y se han cargado");
                }

                comenzado = true;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }
        else{
            labelClickCasilla.setText("Reinicia si quieres comenzar");
            log.warn("Se ha intentado comenzar por segunda vez");
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
        ///No se por qué pero hay que hacer esta corrección
        pos = pos - 1;
        labelClickCasilla.setText("Casilla pulsada: " + listaCasillas.getElemento(pos).getData().getPosX() + "," + listaCasillas.getElemento(pos).getData().getPosY());
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
        log.info("Casilla pulsada");
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
        log.info("Cargamos las properties para la modificación de datos");
    }

    @FXML
    public void onActualizarClick() throws NullPointerException {
        try {
            propiedadesModelo.commit();
            System.out.println(modelo);
            log.info("Parámetros de la partida actualizados");
        }
        catch (NullPointerException ex) {
            log.error("Se han intentado actualizar los valores sin haber pulsado comenzar");
        }
    }
    public void onBucleOrden() {
        int numInds = modelo.getListaInicialIndividuos().getNumeroElementos();
        ///Comprobamos si ha terminado la partida
        if (numInds > 1) {
            ///1.Actualizamos el tiempo de vida de cada individuo
            log.info("Comenzamos el bucle de turno");
            for (int i = 0; i < numInds; i++) {
                Individuo ind1 = modelo.getListaInicialIndividuos().getElemento(i).getData();
                if (ind1.getVida() <= 0) {
                    int posx1 = ind1.getPosX();
                    int posy1 = ind1.getPosY();
                    modelo.getListaInicialIndividuos().del(i);
                    listaCasillas.getElemento(conversorPosicion(posx1, posy1)).getData().delIndividuo(ind1);
                    listaLabels.getElemento(conversorPosicion(posx1, posy1)).getData().setText(listaLabels.getElemento(conversorPosicion(posx1, posy1)).getData().getText().replaceFirst("I", ""));
                    i--;
                    numInds--;
                } else {
                    ind1.setVida(ind1.getVida() - 1);
                }
            }
            log.info("Se actualiza el contador de vida de los individuos");
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
                                log.info("Agua borrada por turnos de vida terminados");
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Tesoro) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("T", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                                log.info("Tesoro borrado por turnos de vida terminados");
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Biblioteca) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("B", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                                log.info("Biblioteca borrada por turnos de vida terminados");
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Comida) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("C", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                                log.info("Comida borrada por turnos de vida terminados");
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Montania) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("M", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                                log.info("Montaña borrada por turnos de vida terminados");
                            } else if (listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData() instanceof Pozo) {
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText().replaceFirst("P", ""));
                                listaCasillas.getElemento(l).getData().getListaRecursos().del(h);
                                h--;
                                cuenta--;
                                log.info("Pozo borrado por turnos de vida terminados");
                            }
                        } else {
                            listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData().setDuracion(listaCasillas.getElemento(l).getData().getListaRecursos().getElemento(h).getData().getDuracion() - 1);
                        }
                    }
                }
            }
            log.info("Tiempo de vida del resto de los recursos actualizado");
            ///3. Movimiento
            int x4 = modelo.getListaInicialIndividuos().getNumeroElementos();
            for (int i = 0; i < x4; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                int posx = ind.getPosX();
                int posy = ind.getPosY();
                listaCasillas.getElemento(conversorPosicion(posx, posy)).getData().delIndividuo(ind);
                listaLabels.getElemento(conversorPosicion(posx, posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().replaceFirst("I", ""));
                if (ind.getTipo().equals("Básico")) {
                    ind.movimientoBasic(modelo.getNumeroColumnas(), modelo.getNumeroFilas());
                    int posx2 = ind.getPosX();
                    int posy2 = ind.getPosY();
                    listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                    listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                    log.info("Individuo básico de id " + ind.getId() + " se ha movido a " + posx2 + ","+ posy2);
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
                            }
                        }
                        ///Ahora nos movemos hacia él
                        if (ind.getRecursoObj().getPosX() < ind.getPosX()) {
                            ind.movimiento(-1,0);
                        }
                        if (ind.getRecursoObj().getPosX() > ind.getPosX()) {
                            ind.movimiento(1,0);
                        }
                        if (ind.getRecursoObj().getPosY() > ind.getPosY()) {
                            ind.movimiento(0,1);
                        }
                        if (ind.getRecursoObj().getPosY() < ind.getPosY()) {
                            ind.movimiento(0,-1);
                        }
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        log.info("Individuo normal de id " + ind.getId() + " se ha movido a " + posx2 + ","+ posy2);
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                    } else {
                        ind.movimientoBasic(modelo.getNumeroColumnas(), modelo.getNumeroFilas());
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                        log.warn("Un individuo normal ha hecho movimiento básico porque no había recursos");
                    }
                }
                if (ind.getTipo().equals("Avanzado")) {
                    if (contadorTurno != 0) {
                        if (ind.getRecursoObj() == null || ind.getRecursoObj().getDuracion() == 0 || (ind.getRecursoObj().getPosY() == ind.getPosY() && ind.getRecursoObj().getPosX() == ind.getPosX())) {
                            double moduloDistanciaMin = 100;
                            for (int p = 0; p < listaCasillas.getNumeroElementos(); p++) {
                                if (listaCasillas.getElemento(p).getData().getListaRecursos().getNumeroElementos() > 0) {
                                    for (int j = 0; j < listaCasillas.getElemento(p).getData().getListaRecursos().getNumeroElementos(); j++) {
                                        double disx = listaCasillas.getElemento(p).getData().getListaRecursos().getElemento(j).getData().getPosX();
                                        double disy = listaCasillas.getElemento(p).getData().getListaRecursos().getElemento(j).getData().getPosY();
                                        if (Math.sqrt((disx * disx) + (disy * disy) ) <= moduloDistanciaMin){
                                            moduloDistanciaMin = Math.sqrt((disx * disx) + (disy * disy));
                                            ind.setRecursoObj(listaCasillas.getElemento(p).getData().getListaRecursos().getElemento(j).getData());
                                        }
                                    }
                                }
                            }
                            log.info("Individuo avanzado de id " + ind.getId() + "ha cambiado su recurso objetivo, se dirige a: " + ind.getRecursoObj().getPosX() + "," + ind.getRecursoObj().getPosY() );
                        }
                        ///Ahora nos movemos hacia él
                        if (ind.getRecursoObj().getPosX() < ind.getPosX()) {
                            ind.movimiento(-1,0);
                        }
                        if (ind.getRecursoObj().getPosX() > ind.getPosX()) {
                            ind.movimiento(1,0);
                        }
                        if (ind.getRecursoObj().getPosY() > ind.getPosY()) {
                            ind.movimiento(0,1);
                        }
                        if (ind.getRecursoObj().getPosY() < ind.getPosY()) {
                            ind.movimiento(0,-1);
                        }
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                        log.info("Individuo avanzado de id " + ind.getId() + " se ha movido a " + posx2 + ","+ posy2);

                    }
                    else{
                        ind.movimientoBasic(modelo.getNumeroColumnas(), modelo.getNumeroFilas());
                        int posx2 = ind.getPosX();
                        int posy2 = ind.getPosY();
                        listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind);
                        listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                        log.warn("Un individuo avanzado ha hecho movimiento básico porque no había recursos");
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
                    log.info("Individuo con id: " + ind.getId() + " ha bebido agua");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("C")) {
                    ind.setVida(ind.getVida() + modelo.getTurnosComida());
                    log.info("Individuo con id: " + ind.getId() + " ha comido");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("B")) {
                    ind.setProbClon(ind.getProbClon() + modelo.getAumentoBiblio());
                    log.info("Individuo con id: " + ind.getId() + " ha entrado en la biblioteca");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("T")) {
                    ind.setProbRepro(ind.getProbRepro() + modelo.getAumentoTesoro());
                    log.info("Individuo con id: " + ind.getId() + " ha encontrado un tesoro");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("P")) {
                    ind.muerte();
                    log.info("Individuo con id: " + ind.getId() + " ha caido en un pozo y ha muerto trágicamente");
                }
                if (listaLabels.getElemento(conversorPosicion(posx, posy)).getData().getText().contains("M")) {
                    ind.setVida(ind.getVida() - modelo.getTurnosMontania());
                    log.info("Individuo con id: " + ind.getId() + " ha sufrido al atravesar la montaña");
                }
            }
            ///5. ¿Reproducciones?
            for(int l = 0; l < listaCasillas.getNumeroElementos(); l ++) {
                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos() > 1) {
                    for (int m = 1; m < listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos(); m++) {
                        Random rand = new Random();
                        int ruleta = rand.nextInt(101);
                        int repro1 = listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getProbRepro();
                        int repro2 = listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getProbRepro();
                        int mediaRepro = (repro1 + repro2) / 2;
                        if (ruleta <= mediaRepro) {
                            ///En caso de que coincidan en tipo, el hijo será de ese tipo
                            if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo())) {
                                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Básico")) {
                                    IndivBasico hijo = new IndivBasico((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().getRaiz().setDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getArbolGene().getRaiz());
                                    hijo.getArbolGene().getRaiz().setIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getArbolGene().getRaiz());
                                    log.info("Reproducción homotípica básica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo " + hijo.getId());
                                } else if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Normal")) {
                                    IndivNormal hijo = new IndivNormal((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().getRaiz().setDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getArbolGene().getRaiz());
                                    hijo.getArbolGene().getRaiz().setIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getArbolGene().getRaiz());
                                    log.info("Reproducción homotípica normal en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo " + hijo.getId());
                                } else if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Avanzado")) {
                                    IndivAvanzado hijo = new IndivAvanzado((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().getRaiz().setDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getArbolGene().getRaiz());
                                    hijo.getArbolGene().getRaiz().setIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getArbolGene().getRaiz());
                                    log.info("Reproducción homotípica avanzada en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo " + hijo.getId());
                                }
                                contadorId++;
                            }
                            else{
                            ///En el caso en el que el tipo difiera, estudiamos el valor de la mejora de tipo
                            ruleta = rand.nextInt(101);
                            if (ruleta <= (int) sliderMejoraTipo.getValue()) {///El tipo mejora, recorremos los tipos de mas avanzado a más básico, y si alguno coincide dejamos de buscar
                                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Avanzado") || listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo().equals("Avanzado")) {
                                    IndivAvanzado hijo = new IndivAvanzado((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().getRaiz().setDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getArbolGene().getRaiz());
                                    hijo.getArbolGene().getRaiz().setIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getArbolGene().getRaiz());
                                    log.info("Reproducción heterotípica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo avanzado " + hijo.getId());
                                } else if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Normal") || listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo().equals("Normal")) {
                                    IndivNormal hijo = new IndivNormal((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().getRaiz().setDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getArbolGene().getRaiz());
                                    hijo.getArbolGene().getRaiz().setIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getArbolGene().getRaiz());
                                    log.info("Reproducción heterotípica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo normal " + hijo.getId());
                                } else if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Básico") || listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo().equals("Básico")) {
                                    IndivBasico hijo = new IndivBasico((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().getRaiz().setDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getArbolGene().getRaiz());
                                    hijo.getArbolGene().getRaiz().setIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getArbolGene().getRaiz());
                                    log.info("Reproducción heterotípica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo básico " + hijo.getId());
                                }
                                contadorId++;
                            } else {/// Sí la probabilidad de mejora no ha salido, recorremos en dirección inversa, de más básico a más avanzado
                                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Básico") || listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo().equals("Básico")) {
                                    IndivBasico hijo = new IndivBasico((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().enlazarDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData());
                                    hijo.getArbolGene().enlazarIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData());
                                    log.info("Reproducción heterotípica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo básico " + hijo.getId());
                                } else if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Normal") || listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo().equals("Normal")) {
                                    IndivNormal hijo = new IndivNormal((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().enlazarDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData());
                                    hijo.getArbolGene().enlazarIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData());
                                    log.info("Reproducción heterotípica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo normal " + hijo.getId());
                                } else if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData().getTipo().equals("Avanzado") || listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getTipo().equals("Avanzado")) {
                                    IndivAvanzado hijo = new IndivAvanzado((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                    hijo.getArbolGene().enlazarDerecha(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(0).getData());
                                    hijo.getArbolGene().enlazarIzquierda(listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData());
                                    log.info("Reproducción heterotípica en:" + listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY()+ " ha nacido el individuo avanzado " + hijo.getId());
                                }
                                contadorId++;
                            }
                        }}
                    }
                }
            }
            ///6. ¿Clonaciones?
            for (int i = 0; i < x4; i++) {
                Individuo ind = modelo.getListaInicialIndividuos().getElemento(i).getData();
                if (ind.meClono()) {
                    int posx2 = ind.getPosX();
                    int posy2 = ind.getPosY();
                    Individuo ind2 = new Individuo(contadorId, contadorTurno, ind.getVida(), ind.getProbRepro(), ind.getProbClon(), ind.getProbMuerte(), posx2, posy2, ind.getTipo());
                    ind2.setArbolGene(new ArbolBinario(new ElementoA(ind2.getId())));
                    ///Si viene de clonación, a la derecha tendra a su progenitos
                    ind2.getArbolGene().enlazarDerecha(ind);
                    contadorId++;
                    modelo.getListaInicialIndividuos().add(new ElementoInd(ind2));
                    listaCasillas.getElemento(conversorPosicion(posx2, posy2)).getData().addIndividuo(ind2);
                    listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().setText(listaLabels.getElemento(conversorPosicion(posx2, posy2)).getData().getText() + "I");
                    log.info("El individuo de id " + ind.getId() + "se ha clonado dando lugar al individuo de id" + ind2.getId());
                }
            }
            ///7. Máximo tres en cada casilla
            for (int l = 0; l < numCasillas; l++) {
                if (listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos() > 3) {
                    log.info("En la casilla: " +listaCasillas.getElemento(l).getData().getPosX() + "," + listaCasillas.getElemento(l).getData().getPosY() + " hay más de tres individuos" );
                    while (listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos() > 3) {
                        int posEliminado = 0;
                        int vidaMinima = 10000;
                        for (int m = 0; m < listaCasillas.getElemento(l).getData().getListaIndividuos().getNumeroElementos(); m++) {
                            if (listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getVida() < vidaMinima) {
                                posEliminado = m;
                                vidaMinima = listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(m).getData().getVida();
                            }
                        }
                        log.info("Borramos el individuo de id: " + listaCasillas.getElemento(l).getData().getListaIndividuos().getElemento(posEliminado).getData().getId()+ " porque su vida era la menor");
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
                                log.info("Aparece nueva agua en " + agua.getPosX() + "," + agua.getPosY());
                                exit = true;
                            } else if (ruleta2 == 1 && modelo.getAparicionPozo() >= ruleta) {
                                Pozo pozo = new Pozo(listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elPozo = new ElementoRec(pozo);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elPozo);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "P");
                                log.info("Aparece nuevo pozo en " + pozo.getPosX() + "," + pozo.getPosY());
                                exit = true;
                            } else if (ruleta2 == 2 && modelo.getAparicionComida() >= ruleta) {
                                Comida comida = new Comida(modelo.getTurnosAgua(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elComida = new ElementoRec(comida);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elComida);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "C");
                                log.info("Aparece nueva comida en " + comida.getPosX() + "," + comida.getPosY());
                                exit = true;
                            } else if (ruleta2 == 3 && modelo.getAparicionMontania() >= ruleta) {
                                Montania montania = new Montania(modelo.getTurnosMontania(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elMontania = new ElementoRec(montania);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elMontania);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "M");
                                log.info("Aparece nueva montaña en " + montania.getPosX() + "," + montania.getPosY());
                                exit = true;
                            } else if (ruleta2 == 4 && modelo.getAparicionBiblio() >= ruleta) {
                                Biblioteca biblio = new Biblioteca(modelo.getAumentoBiblio(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elBiblio = new ElementoRec(biblio);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elBiblio);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "B");
                                log.info("Aparece nueva biblioteca en " + biblio.getPosX() + "," + biblio.getPosY());
                                exit = true;
                            } else if (ruleta2 == 5 && modelo.getAparicionBiblio() >= ruleta) {
                                Tesoro tesoro = new Tesoro(modelo.getAumentoTesoro(), listaCasillas.getElemento(l).getData().getPosX(), listaCasillas.getElemento(l).getData().getPosY());
                                ElementoRec elTesoro = new ElementoRec(tesoro);
                                listaCasillas.getElemento(l).getData().getListaRecursos().add(elTesoro);
                                listaLabels.getElemento(l).getData().setText(listaLabels.getElemento(l).getData().getText() + "T");
                                log.info("Aparece nuevo tesoro en " + tesoro.getPosX() + "," + tesoro.getPosY());
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
            labelTurnoLayout.setText("Turno:" + contadorTurno);
            //Descuento del 10% a cada individuo en reproducción y clonación.
            for(int j =0; j < modelo.getListaInicialIndividuos().getNumeroElementos(); j++){
                modelo.getListaInicialIndividuos().getElemento(j).getData().restaProbClon(10);
                modelo.getListaInicialIndividuos().getElemento(j).getData().restaProbRepro(10);
            }
            log.info("Se ha restado 10% en las probabilidades de cada individuo");
        } else {
            if (numInds == 0) {
                labelTurnoLayout.setText("Turno:" + contadorTurno + ", partida finalizada sin supervivientes");
                log.info("Partida terminada sin ningún individuo vivo");
            }
            else if(numInds ==1){
                String arbolGanador = modelo.getListaInicialIndividuos().getElemento(0).getData().getArbolGene().imprimirArbol();
                labelTurnoLayout.setText("Turno:" + contadorTurno + ", partida finalizada con id del ganador: " + modelo.getListaInicialIndividuos().getElemento(0).getData().getId());
                areaArbol.setText(arbolGanador);
                System.out.println(arbolGanador);
                log.info("Partida terminada con ganador de id: " + modelo.getListaInicialIndividuos().getElemento(0).getData());
            }
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
                            log.info("El usuario ha añadido un agua manualmente");
                        } else if (choiceClase.getValue().equals("Comida")) {
                            Comida comida = new Comida(modelo.getTurnosComida(), choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(comida));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "C");
                            log.info("El usuario ha añadido comida manualmente");
                        } else if (choiceClase.getValue().equals("Montaña")) {
                            Montania montania = new Montania(modelo.getTurnosMontania(), choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(montania));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "M");
                            log.info("El usuario ha añadido una montaña manualmente");
                        } else if (choiceClase.getValue().equals("Pozo")) {
                            Pozo pozo = new Pozo(choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(pozo));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "P");
                            log.info("El usuario ha añadido un pozo manualmente");
                        } else if (choiceClase.getValue().equals("Biblioteca")) {
                            Biblioteca biblioteca = new Biblioteca(modelo.getAumentoBiblio(),choicePosX.getValue(), choicePosY.getValue());
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().add(new ElementoRec(biblioteca));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "B");
                            log.info("El usuario ha añadido una biblioteca manualmente");
                        }
                        labelInfoInterac.setText("Recurso añadido a la posición: " + choicePosX.getValue() + "," + choicePosY.getValue());
                    } else {
                        labelInfoInterac.setText("La casilla ya está llena de recursos");
                        log.warn("El usuario ha intentado añadir un recurso pero ya estaba llena la casilla");
                    }
                } else if (choiceClase.getValue().equals("IndivBásico") || choiceClase.getValue().equals("IndivAvanzado") || choiceClase.getValue().equals("IndivNormal")) {
                    if (listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaIndividuos().getNumeroElementos() < 3) {
                        if (choiceClase.getValue().equals("IndivBásico")) {
                            IndivBasico ind = new IndivBasico((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, choicePosX.getValue(), choicePosY.getValue());
                            modelo.getListaInicialIndividuos().add(new ElementoInd(ind));
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaIndividuos().add(new ElementoInd(ind));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "I");
                            log.info("El usuario ha creado un individuo básico manualmente");
                        } else if (choiceClase.getValue().equals("IndivNormal")) {
                            IndivNormal ind = new IndivNormal((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, choicePosX.getValue(), choicePosY.getValue());
                            modelo.getListaInicialIndividuos().add(new ElementoInd(ind));
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaIndividuos().add(new ElementoInd(ind));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "I");
                            log.info("El usuario ha creado un individuo normal manualmente");
                        } else if (choiceClase.getValue().equals("IndivAvanzado")) {
                            IndivAvanzado ind = new IndivAvanzado((int) sliderProbRepro.getValue(), (int) sliderTurnosVida.getValue(), (int) sliderProbClon.getValue(), contadorId, choicePosX.getValue(), choicePosY.getValue());
                            modelo.getListaInicialIndividuos().add(new ElementoInd(ind));
                            listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaIndividuos().add(new ElementoInd(ind));
                            listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getText() + "I");
                            log.info("El usuario ha creado un individuo avanzado manualmente");
                        }
                        labelInfoInterac.setText("Individuo añadido a la posición: " + choicePosX.getValue() + "," + choicePosY.getValue());
                        contadorId++;
                    } else {
                        labelInfoInterac.setText("La casilla ya está llena de individuos");
                        log.warn("El usuario ha intentado añadir un individuo a una casilla que ya estaba llena");
                    }


                }
            } else if (choiceAccion.getValue().equals("Borrar")) {
                ///Borrada de recursos
                if (choiceClase.getValue().equals("Agua") || choiceClase.getValue().equals("Comida") || choiceClase.getValue().equals("Montaña") || choiceClase.getValue().equals("Tesoro")
                        || choiceClase.getValue().equals("Biblioteca") || choiceClase.getValue().equals("Pozo")) {
                    if (listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(), choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos() > 0) {
                        boolean actuado = false;
                        if (choiceClase.getValue().equals("Agua")) {
                            for(int k = 0; k < listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos(); k++){
                                if(listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getElemento(k).getData() instanceof Agua){
                                    actuado = true;
                                    listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().del(k);
                                    listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getText().replaceFirst("A", ""));
                                }
                                if(actuado){
                                    labelInfoInterac.setText("Aguas eliminadas de: " + choicePosX.getValue() + "," + choicePosY.getValue());
                                    log.info("El usuario ha borrado el agua de " + choicePosX.getValue() + "," + choicePosY.getValue());
                                }
                            }
                        }
                        else if (choiceClase.getValue().equals("Comida")) {
                            for(int k = 0; k < listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos(); k++){
                                if(listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getElemento(k).getData() instanceof Comida){
                                    actuado = true;
                                    listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().del(k);
                                    listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getText().replaceFirst("C", ""));
                                }
                                if(actuado){
                                    labelInfoInterac.setText("Comidas eliminadas de: " + choicePosX.getValue() + "," + choicePosY.getValue());
                                    log.info("El usuario ha borrado la comida de " + choicePosX.getValue() + "," + choicePosY.getValue());
                                }
                            }
                        }
                        else if (choiceClase.getValue().equals("Montaña")) {
                            for(int k = 0; k < listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos(); k++){
                                if(listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getElemento(k).getData() instanceof Montania){
                                    actuado = true;
                                    listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().del(k);
                                    listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getText().replaceFirst("M", ""));
                                }
                                if(actuado){
                                    labelInfoInterac.setText("Montañas eliminadas de: " + choicePosX.getValue() + "," + choicePosY.getValue());
                                    log.info("El usuario ha borrado la montaña de " + choicePosX.getValue() + "," + choicePosY.getValue());
                                }
                            }
                        }
                        else if (choiceClase.getValue().equals("Tesoro")) {
                            for(int k = 0; k < listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos(); k++){
                                if(listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getElemento(k).getData() instanceof Tesoro){
                                    actuado = true;
                                    listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().del(k);
                                    listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getText().replaceFirst("T", ""));
                                }
                                if(actuado){
                                    labelInfoInterac.setText("Tesoros eliminadas de: " + choicePosX.getValue() + "," + choicePosY.getValue());
                                    log.info("El usuario ha borrado el tesoro de " + choicePosX.getValue() + "," + choicePosY.getValue());
                                }
                            }
                        }
                        else if (choiceClase.getValue().equals("Biblioteca")) {
                            for(int k = 0; k < listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos(); k++){
                                if(listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getElemento(k).getData() instanceof Biblioteca){
                                    actuado = true;
                                    listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().del(k);
                                    listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getText().replaceFirst("B", ""));
                                }
                                if(actuado){
                                    labelInfoInterac.setText("Bibliotecas eliminadas de: " + choicePosX.getValue() + "," + choicePosY.getValue());
                                    log.info("El usuario ha borrado la biblioteca de " + choicePosX.getValue() + "," + choicePosY.getValue());
                                }
                            }
                        }
                        else if (choiceClase.getValue().equals("Pozo")) {
                            for(int k = 0; k < listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getNumeroElementos(); k++){
                                if(listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().getElemento(k).getData() instanceof Pozo){
                                    actuado = true;
                                    listaCasillas.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getListaRecursos().del(k);
                                    listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().setText(listaLabels.getElemento(conversorPosicion(choicePosX.getValue(),choicePosY.getValue())).getData().getText().replaceFirst("P", ""));
                                }
                                if(actuado){
                                    labelInfoInterac.setText("Pozos eliminadas de: " + choicePosX.getValue() + "," + choicePosY.getValue());
                                    log.info("El usuario ha borrado el pozo de " + choicePosX.getValue() + "," + choicePosY.getValue());
                                }
                            }
                        }
                        if (!actuado){
                            labelInfoInterac.setText("Seleccione un recurso que haya");
                            log.warn("El usuario ha intentado borrar un recurso en una casilla sin este");
                        }
                    }
                    else{
                        labelInfoInterac.setText("No hay recursos ahí");
                        log.warn("El usuario ha intentado borrar un recurso de una casilla vacía");
                    }
                }
                ///Borrada de individuos
                else if (choiceClase.getValue().equals("IndivBásico") || choiceClase.getValue().equals("IndivAvanzado") || choiceClase.getValue().equals("IndivNormal")) {
                    Individuo ind = null;
                    boolean exit = false;
                    int counter = 0;
                    while (!exit){
                        if(modelo.getListaInicialIndividuos().getElemento(counter).getData().getId() == choiceId.getValue()){
                            ind = modelo.getListaInicialIndividuos().getElemento(counter).getData();
                            modelo.getListaInicialIndividuos().del(counter);
                            exit =true;
                        }
                        counter++;
                    }
                    ///Lo borramos también de la casilla y de la label
                    int posx = ind.getPosX();
                    int posy = ind.getPosY();
                    for(int num = 0; num < listaCasillas.getElemento(conversorPosicion(posx,posy)).getData().getListaIndividuos().getNumeroElementos(); num++){
                        if(listaCasillas.getElemento(conversorPosicion(posx,posy)).getData().getListaIndividuos().getElemento(num).getData().getId() == ind.getId()){
                            listaCasillas.getElemento(conversorPosicion(posx,posy)).getData().getListaIndividuos().del(num);
                        }
                    }
                    listaLabels.getElemento(conversorPosicion(posx,posy)).getData().setText(listaLabels.getElemento(conversorPosicion(posx,posy)).getData().getText().replaceFirst("I", ""));
                    log.info("Individuo con id:" + choiceId.getValue() + " borrado de la casilla: " + ind.getPosX() +","+ ind.getPosY());
                }
            }
        } catch (NullPointerException exception) {
            labelInfoInterac.setText("Error, por favor haga una selección válida");
            log.warn("El usuario ha intentado hacer una interacción mal");
        }
    }
    public void onGuardarPartidaClick(){
        if (!fieldGuardadaParitda.getText().trim().isEmpty() && !Objects.equals(fieldGuardadaParitda.getText(), "")){
            ListaRecursos listaRecursos = new ListaRecursos();
            for(int i =0; i < listaCasillas.getNumeroElementos(); i++){
                if(listaCasillas.getElemento(i).getData().getListaRecursos().getNumeroElementos() > 0){
                    for(int j=0; j<listaCasillas.getElemento(i).getData().getListaRecursos().getNumeroElementos(); j++){
                        listaRecursos.add(new ElementoRec(listaCasillas.getElemento(i).getData().getListaRecursos().getElemento(j).getData()));
                    }
                }
                System.out.println("Iterando");
            }
            modelo.setTodosRecuros(listaRecursos);
            modelo.setArchivoNombre(fieldGuardadaParitda.getText());
            GsonUtilEjemplo.guardarObjetoEnArchivo(fieldGuardadaParitda.getText() + ".json", modelo);
            labelGuardadaPartida.setText("Partida guardada");
            log.info("El usuario ha guardado la partida en el turno " + contadorTurno);
        }
    }
    public void setModelo(Partida modelo){
        this.modelo = modelo;
    }
}