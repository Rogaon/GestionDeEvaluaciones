package gestiondeevaluaciones;

/**
 * Excepción personalizada para notas inválidas.
 */
public class NotaInvalidaException extends Exception {
    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

