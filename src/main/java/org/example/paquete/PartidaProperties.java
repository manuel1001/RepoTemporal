package org.example.paquete;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PartidaProperties {
    ///Modelo
    private Partida original;

    ///Properties
    private IntegerProperty aparicionAgua = new SimpleIntegerProperty();
    private IntegerProperty turnosAgua = new SimpleIntegerProperty();
    private IntegerProperty aparicionComida = new SimpleIntegerProperty();
    private IntegerProperty turnosComida = new SimpleIntegerProperty();
    private IntegerProperty aparicionMontania = new SimpleIntegerProperty();
    private IntegerProperty turnosMontania = new SimpleIntegerProperty();
    private IntegerProperty aparicionBiblio = new SimpleIntegerProperty();
    private IntegerProperty aumentoBiblio = new SimpleIntegerProperty();
    private IntegerProperty aparicionTesoro = new SimpleIntegerProperty();
    private IntegerProperty aumentoTesoro = new SimpleIntegerProperty();
    private IntegerProperty aparicionPozo = new SimpleIntegerProperty();


    ///Métodos de properties implementados desde el ejemplo
    public PartidaProperties(Partida original){setOriginal(original);}
    public void rollback(){
        aparicionAgua.set(original.getAparicionAgua());
        turnosAgua.set(original.getTurnosAgua());
        aparicionComida.set(original.getAparicionComida());
        turnosComida.set(original.getTurnosComida());
        aparicionMontania.set(original.getAparicionMontania());
        turnosMontania.set(original.getTurnosMontania());
        aparicionBiblio.set(original.getAparicionBiblio());
        aumentoBiblio.set(original.getAumentoBiblio());
        aparicionTesoro.set(original.getAparicionTesoro());
        aumentoTesoro.set(original.getAumentoTesoro());
        aparicionPozo.set(original.getAparicionPozo());
    }
    public void setOriginal(Partida original){
        this.original = original;
        rollback(); //Se inicializan los properties.
    }
    public void commit() {
        if (original != null) {
            original.setTurnosComida(turnosComida.get());
            original.setAparicionComida(aparicionComida.get());
            original.setTurnosAgua(turnosAgua.get());
            original.setAparicionAgua(aparicionAgua.get());
            original.setTurnosMontania(turnosMontania.get());
            original.setAparicionMontania(aparicionMontania.get());
            original.setAparicionBiblio(aparicionBiblio.get());
            original.setAumentoBiblio(aumentoBiblio.get());
            original.setAparicionTesoro(aparicionTesoro.get());
            original.setAumentoTesoro(aumentoTesoro.get());
            original.setAparicionPozo(aparicionPozo.get());
        } else {
            // Manejo de error: objeto original es nulo
            System.err.println("Error: El objeto original es nulo.");
        }
    }
    public IntegerProperty turnosAguaProperty() {
        return turnosAgua;
    }

    // Método para la propiedad aparicionAgua
    public IntegerProperty aparicionAguaProperty() {
        return aparicionAgua;
    }

    // Método para la propiedad turnosComida
    public IntegerProperty turnosComidaProperty() {
        return turnosComida;
    }

    // Método para la propiedad aparicionComida
    public IntegerProperty aparicionComidaProperty() {
        return aparicionComida;
    }

    // Método para la propiedad turnosMontania
    public IntegerProperty turnosMontaniaProperty() {
        return turnosMontania;
    }

    // Método para la propiedad aparicionMontania
    public IntegerProperty aparicionMontaniaProperty() {
        return aparicionMontania;
    }

    // Método para la propiedad aparicionBiblio
    public IntegerProperty aparicionBiblioProperty() {
        return aparicionBiblio;
    }

    // Método para la propiedad aumentoBiblio
    public IntegerProperty aumentoBiblioProperty() {
        return aumentoBiblio;
    }

    // Método para la propiedad aparicionTesoro
    public IntegerProperty aparicionTesoroProperty() {
        return aparicionTesoro;
    }

    // Método para la propiedad aumentoTesoro
    public IntegerProperty aumentoTesoroProperty() {
        return aumentoTesoro;
    }

    // Método para la propiedad aparicionPozo
    public IntegerProperty aparicionPozoProperty() {
        return aparicionPozo;
    }
}

