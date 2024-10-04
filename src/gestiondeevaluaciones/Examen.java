package gestiondeevaluaciones;

public class Examen extends Evaluacion {

    public Examen(String nombre) {
        super(nombre);
    }

    // Sobreescritura del método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Examen: ").append(getNombre()).append("\nPreguntas:\n");
        for (Pregunta pregunta : getPreguntas()) {
            sb.append("- ").append(pregunta.getEnunciado()).append("\n");
        }
        return sb.toString();
    }
}