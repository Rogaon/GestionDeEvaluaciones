package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa el banco de preguntas.
 */
public class BancoDePreguntas {
    private Map<String, List<Pregunta>> preguntasPorTema;

    public BancoDePreguntas() {
        preguntasPorTema = new HashMap<>();
    }

    // Método original para agregar una pregunta
    public void agregarPregunta(Pregunta pregunta) {
        preguntasPorTema.computeIfAbsent(pregunta.getTema(), k -> new ArrayList<>()).add(pregunta);
    }

    // Sobrecarga del método para agregar una lista de preguntas
    public void agregarPregunta(List<Pregunta> preguntas) {
        for (Pregunta pregunta : preguntas) {
            agregarPregunta(pregunta);
        }
    }

    public List<Pregunta> obtenerPreguntasPorTema(String tema) throws PreguntaNoEncontradaException {
        List<Pregunta> preguntas = preguntasPorTema.get(tema);
        if (preguntas == null || preguntas.isEmpty()) {
            throw new PreguntaNoEncontradaException("No hay preguntas disponibles para el tema: " + tema);
        }
        return preguntas;
    }

    public List<Pregunta> getTodasLasPreguntas() {
        List<Pregunta> todasPreguntas = new ArrayList<>();
        for (List<Pregunta> preguntas : preguntasPorTema.values()) {
            todasPreguntas.addAll(preguntas);
        }
        return todasPreguntas;
    }

    public void eliminarPregunta(String enunciado) throws PreguntaNoEncontradaException {
        boolean encontrada = false;
        for (List<Pregunta> preguntas : preguntasPorTema.values()) {
            if (preguntas.removeIf(p -> p.getEnunciado().equalsIgnoreCase(enunciado))) {
                encontrada = true;
            }
        }
        if (!encontrada) {
            throw new PreguntaNoEncontradaException("Pregunta no encontrada: " + enunciado);
        }
    }

    public Pregunta buscarPregunta(String enunciado) throws PreguntaNoEncontradaException {
        for (List<Pregunta> preguntas : preguntasPorTema.values()) {
            for (Pregunta p : preguntas) {
                if (p.getEnunciado().equalsIgnoreCase(enunciado)) {
                    return p;
                }
            }
        }
        throw new PreguntaNoEncontradaException("Pregunta no encontrada: " + enunciado);
    }

    public void actualizarTemaPregunta(Pregunta pregunta, String temaAnterior) {
        if (!temaAnterior.equals(pregunta.getTema())) {
            preguntasPorTema.get(temaAnterior).remove(pregunta);
            agregarPregunta(pregunta);
        }
    }
}
