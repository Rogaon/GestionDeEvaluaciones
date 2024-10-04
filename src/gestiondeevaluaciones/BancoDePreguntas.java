package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDePreguntas {
    private Map<String, List<Pregunta>> preguntasPorTema;

    public BancoDePreguntas() {
        preguntasPorTema = new HashMap<>();
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntasPorTema.computeIfAbsent(pregunta.getTema(), k -> new ArrayList<>()).add(pregunta);
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
}
