package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Evaluacion {
    private String titulo;
    private List<Pregunta> preguntas;
    private List<Double> notas;
    private Map<Double, String> comentarios;  // Mapa para almacenar los comentarios asociados a las notas

    
    public Evaluacion(String titulo){
        this.titulo = titulo;
        this.preguntas = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.comentarios = new HashMap<>();
    }
    
    public void agregarPregunta(Pregunta pregunta){
        preguntas.add(pregunta);
    }
    
    // Sobrecarga para registrar una nota con comentario
    public void registrarNota(double nota, String comentario){
        notas.add(nota);
        comentarios.put(nota, comentario);  // Guardar la nota y su comentario asociado
    }
    
    public void registrarNota(double nota){
        notas.add(nota);
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public List<Pregunta> getPreguntas(){
        return preguntas;
    }
    
    public List<Double> getNotas(){
        return notas;
    }
    
     public Map<Double, String> getComentarios(){
        return comentarios;
    }
     
    @Override
    //metodo de sobreescritura para convertir un objeto 
    //Evaluacion en una cadena de texto que resume su contenido de forma legible
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Evaluacion: ").append(titulo).append("\n");
        sb.append("Preguntas:\n");
        for (Pregunta pregunta : preguntas){
            sb.append("- ").append(pregunta.getEnunciado()).append("\n");
        }
        sb.append("Notas y Comentarios:\n");
        for (Double nota : notas){
            sb.append("- Nota: ").append(nota).append(", Comentario: ").append(comentarios.getOrDefault(nota, "Sin comentario")).append("\n");
        }
        return sb.toString();
    } 
    
}
