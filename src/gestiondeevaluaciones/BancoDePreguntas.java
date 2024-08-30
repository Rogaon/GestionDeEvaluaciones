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

    public void agregarPregunta(Pregunta pregunta) {
        String tema = pregunta.getTema();
        if (!preguntasPorTema.containsKey(tema)) {
            preguntasPorTema.put(tema, new ArrayList<>());
        }
        preguntasPorTema.get(tema).add(pregunta);
    }

    public List<Pregunta> obtenerPreguntasPorTema(String tema) {
        return preguntasPorTema.getOrDefault(tema, new ArrayList<>());
    }

    public List<String> obtenerTemas() {
        return new ArrayList<>(preguntasPorTema.keySet());
    }
}
