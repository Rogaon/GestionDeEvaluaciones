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
public class SistemaGestion{
    private BancoDePreguntas bancoDePreguntas;
    private List<Evaluacion> evaluaciones;

    public SistemaGestion(){
        this.bancoDePreguntas = new BancoDePreguntas();
        this.evaluaciones = new ArrayList<>();
    }

    public BancoDePreguntas getBancoDePreguntas(){
        return bancoDePreguntas;
    }

    public void agregarEvaluacion(Evaluacion evaluacion){
        evaluaciones.add(evaluacion);
    }

    public Evaluacion obtenerEvaluacion(String titulo){
        for (Evaluacion evaluacion : evaluaciones){
            if (evaluacion.getTitulo().equalsIgnoreCase(titulo)){
                return evaluacion;
            }
        }
        return null;
    }

    public void mostrarEvaluaciones(){
        for (Evaluacion evaluacion : evaluaciones) {
            System.out.println(evaluacion);
        }
    }    
}
