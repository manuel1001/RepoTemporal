package com.example.demojavafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    static int contadorDeVentanasHijas = 0;

    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **/
    @FXML
    private Label welcomeText;
    @FXML
    private Label labelTextoEjemplo;
    @FXML
    private Label labelValorSlider;
    @FXML
    private Slider miSlider;


    /**
     * Propiedades "bindeadas" que permite interconectar elementos visuales
     **/
    protected StringProperty texto = new SimpleStringProperty("No Hay Nada");
    protected IntegerProperty medida = new SimpleIntegerProperty(0);




    /** Modelo de datos **/

    private ParameterDataModel parametrosData = new ParameterDataModel(7, 10, "Juanito");
    private ParameterDataModelProperties modeloParaGUICompartido = new ParameterDataModelProperties(parametrosData);



    /** Métodos de respuesta a eventos: El GUI llama a estos métodos del controlador para realizar operaciones **/
    /**
     * La convención es llamarlos on+TipoControl+operacionalaqueresponde :
     * onMiBotonEjemploClick indica que es un "manejador de evento de tipo click" del botón MiBotonEjemplo del interfaz
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        texto.set("Recambiamos una propiedad");
    }

    @FXML
    protected void onMiBotonEjemploClick() {
        welcomeText.setText("Establecemos un texto de ejemplo");
        texto.set("Cambiamos una propiedad!");
    }

    @FXML
    protected String getTexto() {
        return texto.toString();
    }


    @FXML
    protected void onMiBotonNuevaVentajaClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello! Ventana hija: " + contadorDeVentanasHijas++);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    protected void onMiBotonNuevaVentanaParametrosClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("parameters-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 420, 340);
            stage.setTitle("Establezca parámetros: ");
            stage.setScene(scene);
            ParameterController p = fxmlLoader.getController();
            p.loadUserData(this.modeloParaGUICompartido); //Carga los datos del modelo en el gui, todas las ventanas comparten el mismo en este caso
            p.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador\n");
        labelTextoEjemplo.textProperty().bind(texto);
        miSlider.valueProperty().bindBidirectional(medida);
        labelValorSlider.textProperty().bind(medida.asString());
    }
}