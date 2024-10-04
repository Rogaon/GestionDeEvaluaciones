package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;

public class Evaluacion {
    private String nombre;
    private List<Pregunta> preguntas;
    private List<Double> notas;

    public Evaluacion(String nombre) {
        this.nombre = nombre;
        this.preguntas = new ArrayList<>();
        this.notas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void agregarPreguntas(List<Pregunta> nuevasPreguntas) {
        this.preguntas.addAll(nuevasPreguntas);
    }

    public void registrarNota(double nota) throws NotaInvalidaException {
        if (nota < 1.0 || nota > 7.0) {
            throw new NotaInvalidaException("La nota debe estar entre 1.0 y 7.0");
        }
        this.notas.add(nota);
    }

    public List<Double> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evaluación: ").append(nombre).append("\nPreguntas:\n");
        for (Pregunta pregunta : preguntas) {
            sb.append("- ").append(pregunta.getEnunciado()).append("\n");
        }
        sb.append("Notas:\n");
        for (Double nota : notas) {
            sb.append("- ").append(nota).append("\n");
        }
        return sb.toString();
    }
}
