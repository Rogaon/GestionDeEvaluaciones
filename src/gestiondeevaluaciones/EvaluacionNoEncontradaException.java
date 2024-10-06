package gestiondeevaluaciones;

/**
 * Excepción personalizada para evaluaciones no encontradas.
 */
public class EvaluacionNoEncontradaException extends Exception {
    /**
     * Constructor que inicializa la excepción con un mensaje específico.
     *
     * @param mensaje El mensaje que describe la excepción.
     */
    public EvaluacionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
