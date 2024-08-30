/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samue
 */
public class SistemaGestion {
    private List<Evaluacion> evaluaciones;
    private BancoDePreguntas bancoDePreguntas;

    public SistemaGestion() {
        this.evaluaciones = new ArrayList<>();
        this.bancoDePreguntas = new BancoDePreguntas();
    }

    public void crearEvaluacion(String titulo) {
        evaluaciones.add(new Evaluacion(titulo));
    }

    public Evaluacion obtenerEvaluacionPorTitulo(String titulo) {
        for (Evaluacion evaluacion : evaluaciones) {
            if (evaluacion.getTitulo().equalsIgnoreCase(titulo)) {
                return evaluacion;
            }
        }
        return null;
    }

    public boolean eliminarEvaluacion(String titulo) {
        Evaluacion evaluacion = obtenerEvaluacionPorTitulo(titulo);
        if (evaluacion != null) {
            evaluaciones.remove(evaluacion);
            return true;
        }
        return false;
    }
        
    public void agregarPreguntaAlBanco(Pregunta pregunta) {
        bancoDePreguntas.agregarPregunta(pregunta);
    }

    public List<Pregunta> obtenerPreguntasPorTema(String tema) {
        return bancoDePreguntas.obtenerPreguntasPorTema(tema);
    }

    public List<String> obtenerTemas() {
        return bancoDePreguntas.obtenerTemas();
    }
    
    public boolean eliminarPregunta(String enunciado, String tema) {
        List<Pregunta> preguntas = bancoDePreguntas.obtenerPreguntasPorTema(tema);
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getEnunciado().equalsIgnoreCase(enunciado)) {
                preguntas.remove(pregunta);
                return true;
            }
        }
        return false;
    }
}
