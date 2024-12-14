package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {
    private static final String ARCHIVO = "resources/notas_estudiantes.txt";

    // Método para añadir un estudiante al archivo
    public void añadirEstudiante(Estudiante estudiante) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(estudiante.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al añadir el estudiante: " + e.getMessage());
        }
    }

    // Método para mostrar todos los estudiantes del archivo
    public void mostrarEstudiantes() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante.getNombre() + " - " + estudiante.getNota());
            }
        }
    }

    // Método para buscar un estudiante por nombre
    public void buscarEstudiante(String nombre) {
        List<Estudiante> estudiantes = leerEstudiantes();
        boolean encontrado = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Estudiante encontrado: " + estudiante.getNombre() + " - " + estudiante.getNota());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante no encontrado.");
        }
    }

    // Método para calcular la media de las notas de todos los estudiantes
    public void calcularMedia() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            double totalNotas = 0;
            for (Estudiante estudiante : estudiantes) {
                totalNotas += estudiante.getNota();
            }
            double media = totalNotas / estudiantes.size();
            System.out.println("La media de las notas es: " + media);
        }
    }

    // Método privado para leer todos los estudiantes del archivo
    private List<Estudiante> leerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    String nombre = datos[0];
                    double nota = Double.parseDouble(datos[1]);
                    Estudiante estudiante = new Estudiante(nombre, nota);
                    estudiantes.add(estudiante);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return estudiantes;
    }
}
