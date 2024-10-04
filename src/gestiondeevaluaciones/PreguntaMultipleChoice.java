package gestiondeevaluaciones;

import java.util.List;

/**
 * Clase que representa una pregunta de opción múltiple, heredando de Pregunta.
 */
public class PreguntaMultipleChoice extends Pregunta {
    private List<String> opciones;
    private String respuestaCorrecta;

    public PreguntaMultipleChoice(String enunciado, String tema, List<String> opciones, String respuestaCorrecta) {
        super(enunciado, tema);
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    // Sobreescritura del método toString para incluir las opciones
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

