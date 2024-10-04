package gestiondeevaluaciones;

/**
 * Excepción personalizada para preguntas no encontradas.
 */
public class PreguntaNoEncontradaException extends Exception {
    public PreguntaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
