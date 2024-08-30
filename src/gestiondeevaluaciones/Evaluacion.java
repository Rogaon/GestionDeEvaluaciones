package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;

public class Evaluacion {
    private String titulo;
    private List<Pregunta> preguntas;
    private List<NotaComentario> notasComentarios;

    public Evaluacion(String titulo) {
        this.titulo = titulo;
        this.preguntas = new ArrayList<>();
        this.notasComentarios = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    // Método sobrecargado 1: Agregar todas las preguntas de un tema a la evaluación
    public void agregarPreguntas(List<Pregunta> preguntasDelTema) {
        preguntas.addAll(preguntasDelTema);
    }

    // Método sobrecargado 2: Agregar una cantidad específica de preguntas del tema a la evaluación
    public void agregarPreguntas(List<Pregunta> preguntasDelTema, int cantidad) {
        if (cantidad > preguntasDelTema.size()) {
            System.out.println("La cantidad solicitada supera el número de preguntas disponibles.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            preguntas.add(preguntasDelTema.get(i));
        }
    }

    public void registrarNota(double nota) {
        notasComentarios.add(new NotaComentario(nota, null));
    }

    public void registrarNota(double nota, String comentario) {
        notasComentarios.add(new NotaComentario(nota, comentario));
    }
    
    public boolean modificarNota(double notaAntigua, double notaNueva, String nuevoComentario) {
        for (NotaComentario nc : notasComentarios) {
            if (nc.getNota() == notaAntigua) {
                nc.setNota(notaNueva);
                if (nuevoComentario != null) {
                    nc.setComentario(nuevoComentario);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evaluacion: ").append(titulo).append("\n");
        sb.append("Preguntas:\n");
        for (Pregunta pregunta : preguntas) {
            sb.append("- ").append(pregunta.getEnunciado()).append("\n");
        }
        sb.append("Notas y Comentarios:\n");
        for (NotaComentario nc : notasComentarios) {
            sb.append("- Nota: ").append(nc.getNota()).append(", Comentario: ").append(nc.getComentario() != null ? nc.getComentario() : "Sin comentario").append("\n");
        }
        return sb.toString();
    }

    private class NotaComentario {
        private double nota;
        private String comentario;

        public NotaComentario(double nota, String comentario) {
            this.nota = nota;
            this.comentario = comentario;
        }

        public double getNota() {
            return nota;
        }

        public void setNota(double nota) {
            this.nota = nota;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }
    }
}