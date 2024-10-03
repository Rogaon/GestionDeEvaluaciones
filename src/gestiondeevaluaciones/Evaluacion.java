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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    public void agregarNota(double nota) throws NotaInvalidaException {
        if (nota < 1.0 || nota > 7.0) {
            throw new NotaInvalidaException("Nota inválida: " + nota + ". Debe estar entre 1.0 y 7.0.");
        }
        notas.add(nota);
    }

    public void eliminarPregunta(String enunciado) {
        preguntas.removeIf(pregunta -> pregunta.getEnunciado().equalsIgnoreCase(enunciado));
    }

    public void modificarEvaluacion(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    @Override
    public String toString() {
        return "Evaluación: " + nombre + ", Preguntas = " + preguntas + ", Notas = " + notas;
    }
}