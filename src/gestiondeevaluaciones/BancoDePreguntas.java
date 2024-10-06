package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa el banco de preguntas.
 */
public class BancoDePreguntas {
    
    //Lista para almacenar las preguntas
    private Map<String, List<Pregunta>> preguntasPorTema;
    
    /**
     * Constructor que inicializa el banco de preguntas.
     */
    public BancoDePreguntas() {
        preguntasPorTema = new HashMap<>();
    }

    /**
     * Agrega una nueva pregunta al banco, clasificándola por tema.
     * 
     * @param pregunta La pregunta que se va a agregar.
     */
    public void agregarPregunta(Pregunta pregunta) {
        preguntasPorTema.computeIfAbsent(pregunta.getTema(), k -> new ArrayList<>()).add(pregunta);
    }

    /**
     * Agrega una lista de preguntas al banco.
     * 
     * @param preguntas Lista de preguntas a agregar.
     */
    public void agregarPregunta(List<Pregunta> preguntas) {
        for (Pregunta pregunta : preguntas) {
            agregarPregunta(pregunta);
        }
    }
    /**
     * Devuelve las preguntas almacenadas de un tema específico.
     * 
     * @param tema El tema por el que se buscarán las preguntas.
     * @return Lista de preguntas asociadas al tema.
     * @throws PreguntaNoEncontradaException Si no hay preguntas para el tema dado.
     */
    public List<Pregunta> obtenerPreguntasPorTema(String tema) throws PreguntaNoEncontradaException {
        List<Pregunta> preguntas = preguntasPorTema.get(tema);
        if (preguntas == null || preguntas.isEmpty()) {
            throw new PreguntaNoEncontradaException("No hay preguntas disponibles para el tema: " + tema);
        }
        return preguntas;
    }
    /**
     * Devuelve todas las preguntas almacenadas en el banco.
     * 
     * @return Lista con todas las preguntas.
     */
    public List<Pregunta> getTodasLasPreguntas() {
        List<Pregunta> todasPreguntas = new ArrayList<>();
        for (List<Pregunta> preguntas : preguntasPorTema.values()) {
            todasPreguntas.addAll(preguntas);
        }
        return todasPreguntas;
    }
    /**
     * Elimina una pregunta del banco por su enunciado.
     * 
     * @param enunciado El enunciado de la pregunta a eliminar.
     * @throws PreguntaNoEncontradaException Si no se encuentra una pregunta con el enunciado dado.
     */
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
    /**
     * Busca una pregunta en el banco por su enunciado.
     * 
     * @param enunciado El enunciado de la pregunta a buscar.
     * @return La pregunta encontrada.
     * @throws PreguntaNoEncontradaException Si no se encuentra ninguna pregunta con el enunciado dado.
     */
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
    /**
     * Actualiza el tema de una pregunta, moviéndola a una nueva categoría si es necesario.
     * 
     * @param pregunta La pregunta que se va a mover.
     * @param temaAnterior El tema anterior de la pregunta.
     */
    public void actualizarTemaPregunta(Pregunta pregunta, String temaAnterior) {
        if (!temaAnterior.equals(pregunta.getTema())) {
            preguntasPorTema.get(temaAnterior).remove(pregunta);
            agregarPregunta(pregunta);
        }
    }
    
    /**
    * Obtiene la lista de temas disponibles en el banco de preguntas.
    * 
    * @return Lista de temas.
    */
    public List<String> obtenerTemas() {
        return new ArrayList<>(preguntasPorTema.keySet());
    }
}
