package com.example.demojavafx;

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


public class MenuController implements GsonUtilEjemplo{

    ///Label y sliders
    @FXML
    public Label labelNuevaPartida;
    @FXML
    public Label labelProbAgua;
    @FXML
    public Slider sliderProbAgua;
    @FXML
    public Label labelTurnAgua;
    @FXML
    public Slider sliderTurnAgua;
    @FXML
    public Label labelProbMont;
    @FXML
    public Slider sliderProbMont;
    @FXML
    public Label labelTurnMon;
    @FXML
    public Slider sliderTurnMont;
    @FXML
    public Label labelProbCom;
    @FXML
    public Slider sliderProbCom;
    @FXML
    public Label labelTurnCom;
    @FXML
    public Slider sliderTurnCom;
    @FXML
    public Label labelProbTesoro;
    @FXML
    public Slider sliderProbTesoro;
    @FXML
    public Label labelAumentoTesoro;
    @FXML
    public Slider sliderAumentoTesoro;
    @FXML
    public Label labelProbBiblio;
    @FXML
    public Slider sliderProbBiblio;
    @FXML
    public Label labelAumentoBiblio;
    @FXML
    public Slider sliderAumentoBiblio;
    @FXML
    public Label labelProbPozo;
    @FXML
    public Slider sliderProbPozo;
    @FXML
    public Label labelRepInd;
    @FXML
    public Slider sliderRepInd;
    @FXML
    public Label labelTurnInd;
    @FXML
    public Slider sliderTurnInd;
    @FXML
    public Label labelClonInd;
    @FXML
    public Slider sliderClonInd;
    @FXML
    public Label labelMapRows;
    @FXML
    public Slider sliderMapRows;
    @FXML
    public Label labelMapCol;
    @FXML
    public Slider sliderMapCol;

    ///La choiceBox
    @FXML
    public ChoiceBox<String> choiceIndividuo;
    public String[] tiposIndividuo = {"Básico", "Normal", "Avanzado"};
    ///
    @FXML
    public TextField fieldNombrePartida;
    @FXML
    public void onNuevaPartidaClick() {
        if (fieldNombrePartida.getText().trim().isEmpty()) {
            labelNuevaPartida.textProperty().set("Selecciona un nombre válido para tu partida");
        } else {
            try{
            Partida nuevaPartida = new Partida(fieldNombrePartida.getText(), (int) sliderProbAgua.getValue(),
                    (int) sliderTurnAgua.getValue(), (int) sliderProbCom.getValue(), (int) sliderTurnCom.getValue(),
                    (int) sliderProbMont.getValue(), (int) sliderTurnMont.getValue(), (int) sliderProbBiblio.getValue(),
                    (int) sliderAumentoBiblio.getValue(), (int) sliderProbTesoro.getValue(), (int) sliderAumentoTesoro.getValue(),
                    (int) sliderProbPozo.getValue(), (int) sliderMapCol.getValue(), (int) sliderMapRows.getValue());
            String nombrePartida = nuevaPartida.getArchivoNombre() + ".json";
            GsonUtilEjemplo.guardarObjetoEnArchivo(nombrePartida, nuevaPartida);
            Partida cargaPartida = GsonUtilEjemplo.cargarObjetoDesdeArchivo(nombrePartida, Partida.class);
            String nombreCarga = cargaPartida.getArchivoNombre() + ".json";
            labelNuevaPartida.textProperty().set(nombreCarga);}
            catch (FileNotFoundException exception){
                labelNuevaPartida.textProperty().set("Error al cargar partida");
            }
        }
    }

//    @FXML
//    public void onIniciarPartidaClick() throws IOException {
//        if(labelNuevaPartida.getText() == "" || labelNuevaPartida.getText() == "Selecciona un nombre válido para tu partida"  || labelNuevaPartida.getText() == "Error al cargar partida"){
//            labelNuevaPartida.textProperty().set("Selecciona un nombre válido para tu partida");
//        }
//        else{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("juego-view.fxml"));
//        Parent root = loader.load();
//        JuegoController controlador2 = loader.getController();
//        System.out.println("Enviado:" + labelNuevaPartida.getText());
//        controlador2.setNombreJuego(labelNuevaPartida.getText());
//        Stage nuevaStage = new Stage();
//        nuevaStage.setTitle("Nueva Escena");
//        nuevaStage.setScene(new Scene(root));
//        nuevaStage.show();}
//    }
    public void onCargarPartidaClick() throws IOException {
        try {
            Partida cargaPartida = GsonUtilEjemplo.cargarObjetoDesdeArchivo(fieldNombrePartida.getText() + ".json", Partida.class);
            labelNuevaPartida.textProperty().set(cargaPartida.getArchivoNombre() + ".json");
        } catch (NullPointerException ex) {
            labelNuevaPartida.textProperty().set("Error al cargar partida");
        } catch (FileNotFoundException ex){
            labelNuevaPartida.textProperty().set("Error al cargar partida");
        }
    }

    public void initialize() {
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
        ///Opciones de la choicebox de los individuos
        choiceIndividuo.getItems().addAll(tiposIndividuo);
    }

    public void onGuardarIndividuoClick() {
        System.out.println("guardando");
    }
}