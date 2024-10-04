package gestiondeevaluaciones;

/**
 * Excepción personalizada para evaluaciones no encontradas.
 */
public class EvaluacionNoEncontradaException extends Exception {
    public EvaluacionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
