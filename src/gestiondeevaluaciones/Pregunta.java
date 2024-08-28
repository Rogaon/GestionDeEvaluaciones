/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

/**
 *
 * @author samue
 */
public class Pregunta {
    private String enunciado;
    private String tema;
    
    public Pregunta(String enunciado, String tema){
    this.enunciado = enunciado;
    this.tema = tema;
    }
    
    public String getEnunciado(){
        return enunciado;
    }
    
    public void setEnunciado(){
        this.enunciado = enunciado;
    }
    
    public String getTema(){
        return tema;
    }
    
    public void setTema(){
        this.tema = tema;
    }
}
