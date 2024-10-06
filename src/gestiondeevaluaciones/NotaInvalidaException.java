package gestiondeevaluaciones;

/**
 * Clase que representa una excepción lanzada cuando una nota es inválida.
 */
public class NotaInvalidaException extends Exception {
    /**
     * Constructor que inicializa la excepción con un mensaje específico.
     *
     * @param mensaje El mensaje que describe la excepción.
     */
    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

