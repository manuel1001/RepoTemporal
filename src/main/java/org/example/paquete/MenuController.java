package org.example.paquete;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.paquete.ListaEnlazada.*;
import org.example.paquete.individuos.*;
public class MenuController implements GsonUtilEjemplo{
    private static final Logger log = LogManager.getLogger(MenuController.class);

    ///Label y sliders
    @FXML
    private Label labelNuevaPartida;
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
    private Label labelRepInd;
    @FXML
    private Slider sliderRepInd;
    @FXML
    private Label labelTurnInd;
    @FXML
    private Slider sliderTurnInd;
    @FXML
    private Label labelClonInd;
    @FXML
    private Slider sliderClonInd;
    @FXML
    private Label labelMapRows;
    @FXML
    private Slider sliderMapRows;
    @FXML
    private Label labelMapCol;
    @FXML
    private Slider sliderMapCol;
    @FXML
    private Slider sliderProbRecursos;
    @FXML
    private Label labelProbRecursos;


    ///La choiceBox
    @FXML
    private ChoiceBox<String> choiceIndividuo;
    private String[] tiposIndividuo = {"Básico", "Normal", "Avanzado"};
    ///
    ///Lista de los individuos e id de ellos
    private ListaInd listaIndividuos = new ListaInd();
    private int contIndiv = 0;
    @FXML
    private Label labelNumInd;
    @FXML
    private TextField fieldNombrePartida;
    @FXML
    public void onGuardarPlantillaClick() {
        if (fieldNombrePartida.getText().trim().isEmpty()) {
            labelNuevaPartida.textProperty().set("Selecciona un nombre válido para tu partida");
        } else {
            try{
                Partida nuevaPartida = new Partida(fieldNombrePartida.getText(), (int) sliderProbAgua.getValue(),
                        (int) sliderTurnAgua.getValue(), (int) sliderProbCom.getValue(), (int) sliderTurnCom.getValue(),
                        (int) sliderProbMont.getValue(), (int) sliderTurnMont.getValue(), (int) sliderProbBiblio.getValue(),
                        (int) sliderAumentoBiblio.getValue(), (int) sliderProbTesoro.getValue(), (int) sliderAumentoTesoro.getValue(),
                        (int) sliderProbPozo.getValue(),this.listaIndividuos, (int) sliderMapCol.getValue(), (int) sliderMapRows.getValue(), (int) sliderProbRecursos.getValue());
                String nombrePartida = nuevaPartida.getArchivoNombre() + ".json";
                GsonUtilEjemplo.guardarObjetoEnArchivo(nombrePartida, nuevaPartida);
                Partida cargaPartida = GsonUtilEjemplo.cargarObjetoDesdeArchivo(nombrePartida, Partida.class);
                String nombreCarga = cargaPartida.getArchivoNombre() + ".json";
                labelNuevaPartida.textProperty().set(nombreCarga);
            log.info("El usuario ha guardado su plantilla");
            }

            catch (FileNotFoundException exception){
                labelNuevaPartida.textProperty().set("Error al cargar partida");
            }
        }
    }
    @FXML
    public void onComenzarDeUnaClick() throws IOException {
        try {
            Partida nuevaPartida = new Partida(fieldNombrePartida.getText(), (int) sliderProbAgua.getValue(),
                    (int) sliderTurnAgua.getValue(), (int) sliderProbCom.getValue(), (int) sliderTurnCom.getValue(),
                    (int) sliderProbMont.getValue(), (int) sliderTurnMont.getValue(), (int) sliderProbBiblio.getValue(),
                    (int) sliderAumentoBiblio.getValue(), (int) sliderProbTesoro.getValue(), (int) sliderAumentoTesoro.getValue(),
                    (int) sliderProbPozo.getValue(), this.listaIndividuos, (int) sliderMapCol.getValue(), (int) sliderMapRows.getValue(), (int) sliderProbRecursos.getValue());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("juego-view.fxml"));
            Parent root = loader.load();
            JuegoController controlador2 = loader.getController();
            System.out.println("Enviado:" + labelNuevaPartida.getText());
            controlador2.setModelo(nuevaPartida);
            Stage nuevaStage = new Stage();
            nuevaStage.setTitle("Juego");
            nuevaStage.setScene(new Scene(root));
            nuevaStage.show();
            Stage esteStage = (Stage) sliderProbAgua.getScene().getWindow();
            esteStage.close();
            log.info("El usuario ha comenzado sin guardar su plantilla");
        } catch (IOException ex) {
            log.fatal("No se puede arrancar el juego");
        }
    }


    @FXML
    public void onIniciarPartidaCargadaClick() throws IOException {
        if(labelNuevaPartida.getText() == "" || labelNuevaPartida.getText() == "Selecciona un nombre válido para tu partida"  || labelNuevaPartida.getText() == "Error al cargar partida"){
            labelNuevaPartida.textProperty().set("Selecciona un nombre válido para tu partida");
            log.warn("El usuario ha introducido un nombre de partida que no existe");
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("juego-view.fxml"));
            Parent root = loader.load();
            JuegoController controlador2 = loader.getController();
            System.out.println("Enviado:" + labelNuevaPartida.getText());
            controlador2.setNombreJuego(labelNuevaPartida.getText());
            Stage nuevaStage = new Stage();
            nuevaStage.setTitle("Juego");
            nuevaStage.setScene(new Scene(root));
            nuevaStage.show();}
        Stage esteStage = (Stage) sliderProbAgua.getScene().getWindow();
        esteStage.close();
        log.info("El usuario ha iniciado una partida desde un archivo, cerrando menú");
    }
    @FXML
    public void onCargarPartidaClick() throws IOException {
        try {
            Partida cargaPartida = GsonUtilEjemplo.cargarObjetoDesdeArchivo(fieldNombrePartida.getText() + ".json", Partida.class);
            labelNuevaPartida.textProperty().set(cargaPartida.getArchivoNombre() + ".json");
        } catch (NullPointerException ex) {
            log.warn("El usuario ha introducido un nombre de partida que no existe");
        } catch (FileNotFoundException ex){
            labelNuevaPartida.textProperty().set("Error al cargar partida");
        }
    }

    public void initialize() {
        log.info("Cargando el menú");
        ///Relacionando las labels con los sliders para hacer una mejor interfaz
        labelMapCol.textProperty().bind(sliderMapCol.valueProperty().asString());
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
        labelRepInd.textProperty().bind(sliderRepInd.valueProperty().asString());
        labelTurnInd.textProperty().bind(sliderTurnInd.valueProperty().asString());
        labelClonInd.textProperty().bind(sliderClonInd.valueProperty().asString());
        labelMapRows.textProperty().bind(sliderMapRows.valueProperty().asString());
        labelMapCol.textProperty().bind(sliderMapCol.valueProperty().asString());
        labelProbRecursos.textProperty().bind(sliderProbRecursos.valueProperty().asString());
        ///Opciones de la choicebox de los individuos
        choiceIndividuo.getItems().addAll(tiposIndividuo);
    }
    @FXML
    public void onGuardarIndividuoClick() {
        if (listaIndividuos.getNumeroElementos() < 15) {
            if (choiceIndividuo.getSelectionModel().getSelectedItem() == "Básico"){
                int id = this.contIndiv + 1;
                Random rand = new Random();
                int posX = rand.nextInt(8) + 1;
                int posY = rand.nextInt(8) + 1;
                listaIndividuos.add(new ElementoInd(new IndivBasico((int) sliderRepInd.getValue(), (int) sliderTurnInd.getValue(), (int) sliderClonInd.getValue(), id, posX, posY)));
                System.out.println("guardando: id " + id + ", tipo:" + choiceIndividuo.getSelectionModel().getSelectedItem());
                System.out.println(listaIndividuos.getElemento(id-1).getData());
                contIndiv++;
            }
            else if (choiceIndividuo.getSelectionModel().getSelectedItem() == "Normal"){
                int id = this.contIndiv + 1;
                Random rand = new Random();
                int posX = rand.nextInt(8) + 1;
                int posY = rand.nextInt(8) + 1;
                listaIndividuos.add(new ElementoInd(new IndivNormal((int) sliderRepInd.getValue(), (int) sliderTurnInd.getValue(), (int) sliderClonInd.getValue(), id, posX, posY)));
                System.out.println("guardando: id " + id + ", tipo:" + choiceIndividuo.getSelectionModel().getSelectedItem());
                System.out.println(listaIndividuos.getElemento(id-1).getData());
                contIndiv++;
            }
            else if (choiceIndividuo.getSelectionModel().getSelectedItem() == "Avanzado"){
                int id = this.contIndiv + 1;
                Random rand = new Random();
                int posX = rand.nextInt(8) + 1;
                int posY = rand.nextInt(8) + 1;
                listaIndividuos.add(new ElementoInd(new IndivAvanzado((int) sliderRepInd.getValue(), (int) sliderTurnInd.getValue(), (int) sliderClonInd.getValue(), id, posX, posY)));
                System.out.println("guardando: id " + id + ", tipo:" + choiceIndividuo.getSelectionModel().getSelectedItem());
                System.out.println(listaIndividuos.getElemento(id-1).getData());
                contIndiv++;
            }
            else{
                log.warn("Error al crear un individuo");
            }
            labelNumInd.textProperty().setValue(String.valueOf(this.contIndiv));
        }
        else {
            labelNumInd.textProperty().setValue("15, individuo no admitido");
            log.warn("Capacidad inicial de individuos máxima alcanza, individuo no admitido");
        }
    }
}