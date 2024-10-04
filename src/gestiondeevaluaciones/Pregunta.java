package gestiondeevaluaciones;

/**
 * Clase que representa una pregunta general.
 */
public class Pregunta {
    private String enunciado;
    private String tema;

    public Pregunta(String enunciado, String tema) {
        this.enunciado = enunciado;
        this.tema = tema;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return enunciado + " (Tema: " + tema + ")";
    }
}

