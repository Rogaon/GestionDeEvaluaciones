package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDePreguntas {
    private Map<String, List<Pregunta>> preguntasPorTema;

    public BancoDePreguntas() {
        this.preguntasPorTema = new HashMap<>();
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntasPorTema.computeIfAbsent(pregunta.getTema(), k -> new ArrayList<>()).add(pregunta);
    }

    public void agregarPreguntas(String tema, List<Pregunta> preguntas) {
        preguntasPorTema.computeIfAbsent(tema, k -> new ArrayList<>()).addAll(preguntas);
    }

    public void agregarPreguntas(String tema, List<Pregunta> preguntas, int cantidad) {
        if (cantidad > preguntas.size()) {
            System.out.println("La cantidad solicitada supera el número de preguntas disponibles.");
            return;
        }

        List<Pregunta> preguntasDelTema = preguntasPorTema.computeIfAbsent(tema, k -> new ArrayList<>());
        for (int i = 0; i < cantidad; i++) {
            preguntasDelTema.add(preguntas.get(i));
        }
    }

    public List<Pregunta> obtenerPreguntasPorTema(String tema) {
        return preguntasPorTema.getOrDefault(tema, new ArrayList<>());
    }

    public List<String> obtenerTemas() {
        return new ArrayList<>(preguntasPorTema.keySet());
    }

    public boolean eliminarPregunta(String enunciado, String tema) {
        List<Pregunta> preguntas = preguntasPorTema.get(tema);
        if (preguntas != null) {
            return preguntas.removeIf(p -> p.getEnunciado().equals(enunciado));
        }
        return false;
    }
}
