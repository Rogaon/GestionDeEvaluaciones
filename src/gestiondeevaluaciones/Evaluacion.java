package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una evaluación.
 */
public class Evaluacion {
    private String nombre;
    private List<Pregunta> preguntas;
    private List<Double> notas;
    
    /**
     * Constructor que inicializa una evaluación con el nombre proporcionado.
     *
     * @param nombre El nombre de la evaluación.
     */
    
    public Evaluacion(String nombre) {
        this.nombre = nombre;
        this.preguntas = new ArrayList<>();
        this.notas = new ArrayList<>();
    }
    /**
     * Obtiene el nombre de la evaluación.
     *
     * @return El nombre de la evaluación.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene la lista de preguntas asociadas a la evaluación.
     *
     * @return La lista de preguntas.
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Agrega una lista de preguntas a la evaluación.
     *
     * @param nuevasPreguntas La lista de preguntas que se agregarán.
     */
    public void agregarPreguntas(List<Pregunta> nuevasPreguntas) {
        this.preguntas.addAll(nuevasPreguntas);
    }

    /**
     * Agrega una pregunta individual a la evaluación.
     *
     * @param pregunta La pregunta que se agregará.
     */
    public void agregarPreguntas(Pregunta pregunta) {
        this.preguntas.add(pregunta);
    }
    /**
     * Registra una nota para la evaluación.
     *
     * @param nota La nota que se va a registrar.
     * @throws NotaInvalidaException Si la nota está fuera del rango permitido (1.0 - 7.0).
     */
    public void registrarNota(double nota) throws NotaInvalidaException {
        if (nota < 1.0 || nota > 7.0) {
            throw new NotaInvalidaException("La nota debe estar entre 1.0 y 7.0");
        }
        this.notas.add(nota);
    }
    /**
    * Obtiene la lista de notas registradas para la evaluación.
    *
    * @return La lista de notas.
    */
    public List<Double> getNotas() {
        return notas;
    }
    /**
     * Devuelve una representación en cadena de la evaluación, que incluye preguntas y notas.
     *
     * @return La representación en cadena de la evaluación.
     */
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
