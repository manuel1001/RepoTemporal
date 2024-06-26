module org.example.proyectomigrado {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.apache.logging.log4j;


    opens org.example.paquete to javafx.fxml,com.google.gson;
    exports org.example.paquete;
    opens org.example.paquete.ListaEnlazada to javafx.fxml,com.google.gson;
    opens org.example.paquete.individuos to javafx.fxml,com.google.gson;
    opens org.example.paquete.recursos to javafx.fxml,com.google.gson;
    opens org.example.paquete.ArbolBinario to javafx.fxml,com.google.gson;
    opens org.example.paquete.Grafo to javafx.fxml,com.google.gson;


}