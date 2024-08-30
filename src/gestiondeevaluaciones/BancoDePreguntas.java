/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author samue
 */
public class BancoDePreguntas {
    private Map<String, List<Pregunta>> preguntasPorTema;

    public BancoDePreguntas() {
        preguntasPorTema = new HashMap<>();
    }

    // Método para agregar una pregunta a un tema específico
    public void agregarPregunta(Pregunta pregunta) {
        String tema = pregunta.getTema();
        preguntasPorTema.computeIfAbsent(tema, k -> new ArrayList<>()).add(pregunta);
    }

    // Sobrecarga 1: Agregar una lista de preguntas a un tema específico
    public void agregarPreguntas(String tema, List<Pregunta> preguntas) {
        preguntasPorTema.computeIfAbsent(tema, k -> new ArrayList<>()).addAll(preguntas);
    }

    // Sobrecarga 2: Agregar preguntas con una cantidad específica a un tema
    public void agregarPreguntas(String tema, List<Pregunta> preguntas, int cantidad) {
        List<Pregunta> listaTema = preguntasPorTema.computeIfAbsent(tema, k -> new ArrayList<>());
        int limit = Math.min(cantidad, preguntas.size());
        for (int i = 0; i < limit; i++) {
            listaTema.add(preguntas.get(i));
        }
    }

    public List<Pregunta> obtenerPreguntasPorTema(String tema) {
        return preguntasPorTema.getOrDefault(tema, new ArrayList<>());
    }

    public List<String> obtenerTemas() {
        return new ArrayList<>(preguntasPorTema.keySet());
    }
}
