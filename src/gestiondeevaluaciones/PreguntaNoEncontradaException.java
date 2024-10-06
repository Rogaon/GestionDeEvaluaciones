package gestiondeevaluaciones;

/**
 * Clase que representa una excepción lanzada cuando no se encuentra una pregunta.
 */
public class PreguntaNoEncontradaException extends Exception {
    /**
     * Constructor que inicializa la excepción con un mensaje específico.
     *
     * @param mensaje El mensaje que describe la excepción.
     */
    public PreguntaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
