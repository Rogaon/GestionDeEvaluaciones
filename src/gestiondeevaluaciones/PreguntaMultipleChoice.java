package gestiondeevaluaciones;

import java.util.List;

/**
 * Clase que representa una pregunta de opción múltiple, heredando de Pregunta.
 */
public class PreguntaMultipleChoice extends Pregunta {
    private List<String> opciones;
    private String respuestaCorrecta;
    
    /**
     * Constructor que inicializa una pregunta de opción múltiple.
     * 
     * @param enunciado El enunciado de la pregunta.
     * @param tema El tema al que pertenece la pregunta.
     * @param opciones La lista de opciones para la respuesta.
     * @param respuestaCorrecta La respuesta correcta de la pregunta.
     */
    public PreguntaMultipleChoice(String enunciado, String tema, List<String> opciones, String respuestaCorrecta) {
        super(enunciado, tema);
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }
    
    /**
     * Devuelve la lista de opciones de la pregunta.
     * 
     * @return La lista de opciones.
     */
    public List<String> getOpciones() {
        return opciones;
    }
    
    /**
     * Devuelve la respuesta correcta de la pregunta.
     * 
     * @return La respuesta correcta.
     */
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
    
    /**
     * Sobreescritura del método toString para incluir las opciones.
     * 
     * @return La representación en cadena de la pregunta de opción múltiple,
     * incluyendo el enunciado y las opciones disponibles.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nOpciones:\n");
        for (String opcion : opciones) {
            sb.append("- ").append(opcion).append("\n");
        }
        return sb.toString();
    }
}

