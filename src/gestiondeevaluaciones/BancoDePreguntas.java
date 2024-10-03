package gestiondeevaluaciones;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDePreguntas {
    private List<Pregunta> preguntas;

    public BancoDePreguntas() {
        this.preguntas = new ArrayList<>();
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    public void agregarPreguntas() {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de preguntas a agregar:"));
        for (int i = 0; i < cantidad; i++) {
            String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta " + (i + 1) + ":");
            String tema = JOptionPane.showInputDialog("Ingrese el tema de la pregunta " + (i + 1) + ":");
            agregarPregunta(new Pregunta(enunciado, tema));
        }
    }

    public void mostrarPreguntasPorTema(String tema) {
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getTema().equalsIgnoreCase(tema)) {
                System.out.println(pregunta);
            }
        }
    }

    public void eliminarPregunta(String enunciado) {
        preguntas.removeIf(pregunta -> pregunta.getEnunciado().equalsIgnoreCase(enunciado));
    }

    public void modificarPregunta(String enunciadoOriginal, String nuevoEnunciado, String nuevoTema) {
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getEnunciado().equalsIgnoreCase(enunciadoOriginal)) {
                pregunta.setEnunciado(nuevoEnunciado);
                pregunta.setTema(nuevoTema);
                System.out.println("Pregunta modificada.");
                return;
            }
        }
        System.out.println("Pregunta no encontrada.");
    }

    public void mostrarPreguntaEspecifica(String enunciado) throws PreguntaNoEncontradaException {
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getEnunciado().equalsIgnoreCase(enunciado)) {
                System.out.println(pregunta);
                return;
            }
        }
        throw new PreguntaNoEncontradaException("Pregunta no encontrada: " + enunciado);
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
}