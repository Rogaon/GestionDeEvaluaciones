package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Evaluacion {
    private String titulo;
    private List<Pregunta> preguntas;
    private List<Double> notas;
    private Map<Double, String> comentarios;

    public Evaluacion(String titulo) {
        this.titulo = titulo;
        this.preguntas = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.comentarios = new HashMap<>();
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
        notas.add(nota);
    }

    public void registrarNota(double nota, String comentario) {
        notas.add(nota);
        comentarios.put(nota, comentario);
    }
    
    public boolean modificarNota(double notaAntigua, double notaNueva, String nuevoComentario) {
        int index = notas.indexOf(notaAntigua);
        if (index != -1) {
            notas.set(index, notaNueva);
            if (nuevoComentario != null) {
                comentarios.remove(notaAntigua);
                comentarios.put(notaNueva, nuevoComentario);
            }
            return true;
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
        for (Double nota : notas) {
            sb.append("- Nota: ").append(nota).append(", Comentario: ").append(comentarios.getOrDefault(nota, "Sin comentario")).append("\n");
        }
        return sb.toString();
    }
}