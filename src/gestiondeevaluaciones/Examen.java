package gestiondeevaluaciones;

/**
 * Clase que representa un examen, heredando de Evaluacion.
 */
public class Examen extends Evaluacion {

    public Examen(String nombre) {
        super(nombre);
    }

    // Sobreescritura del método toString para diferenciar un Examen de una Evaluación
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Examen: ").append(getNombre()).append("\nPreguntas:\n");
        for (Pregunta pregunta : getPreguntas()) {
            sb.append("- ").append(pregunta.getEnunciado()).append("\n");
        }
        sb.append("Notas:\n");
        for (Double nota : getNotas()) {
            sb.append("- ").append(nota).append("\n");
        }
        return sb.toString();
    }
}
