/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samue
 */
public class BancoDePreguntas {
    private List<Pregunta> preguntas;
    
    public void agregarPregunta(Pregunta pregunta){
        preguntas.add(pregunta);
    }
    
    public List<Pregunta> obtenerPreguntasPorTema(String tema){
        List<Pregunta> preguntasPorTema = new ArrayList<>();
        for(Pregunta pregunta : preguntas){
            if(pregunta.getTema().equalsIgnoreCase(tema)){
                preguntasPorTema.add(pregunta);
            }
        }
        return preguntasPorTema;
    }
    
    public List<Pregunta> getPreguntas(){
        return preguntas;
    }
}
