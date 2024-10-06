package gestiondeevaluaciones;

/**
 * Clase que representa una pregunta general.
 */
public class Pregunta {
    private String enunciado;
    private String tema;
    /**
     * Constructor que inicializa una pregunta con su enunciado y tema.
     * 
     * @param enunciado El enunciado de la pregunta.
     * @param tema El tema al que pertenece la pregunta.
     */
    public Pregunta(String enunciado, String tema) {
        this.enunciado = enunciado;
        this.tema = tema;
    }
    /**
     * Devuelve el enunciado de la pregunta.
     * 
     * @return El enunciado de la pregunta.
     */
    public String getEnunciado() {
        return enunciado;
    }
    /**
     * Establece un nuevo enunciado para la pregunta.
     * 
     * @param enunciado El nuevo enunciado de la pregunta.
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    /**
     * Devuelve el tema de la pregunta.
     * 
     * @return El tema de la pregunta.
     */
    public String getTema() {
        return tema;
    }
    /**
     * Establece un nuevo tema para la pregunta.
     * 
     * @param tema El nuevo tema de la pregunta.
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
    /**
     * Devuelve una representación en cadena de la pregunta.
     * 
     * @return La representación en cadena de la pregunta.
     */
    @Override
    public String toString() {
        return enunciado + " (Tema: " + tema + ")";
    }
}

