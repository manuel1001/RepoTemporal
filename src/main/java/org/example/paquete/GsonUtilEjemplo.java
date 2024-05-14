package org.example.paquete;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public interface GsonUtilEjemplo {
    // Método para guardar un objeto en un archivo JSON
    static <T> void guardarObjetoEnArchivo(String rutaArchivo, T objeto) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar un objeto desde un archivo JSON
    static <T> T cargarObjetoDesdeArchivo(String rutaArchivo, Class<T> clase) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(rutaArchivo) ;
        return gson.fromJson(reader, clase);
    }
}