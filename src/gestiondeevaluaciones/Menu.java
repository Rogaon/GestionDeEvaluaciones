/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samue
 */
public class Menu {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Crear evaluación");
            System.out.println("2. Agregar pregunta al banco");
            System.out.println("3. Agregar preguntas a una evaluación");
            System.out.println("4. Registrar nota");
            System.out.println("5. Mostrar evaluación");
            System.out.println("6. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título de la evaluación: ");
                    String titulo = scanner.nextLine();
                    sistema.crearEvaluacion(titulo);
                    break;
                case 2:
                    System.out.print("Ingrese el enunciado de la pregunta: ");
                    String enunciado = scanner.nextLine();
                    System.out.print("Ingrese el tema de la pregunta: ");
                    String tema = scanner.nextLine();
                    sistema.agregarPreguntaAlBanco(new Pregunta(enunciado, tema));
                    break;
                case 3:
                    System.out.print("Ingrese el título de la evaluación: ");
                    String tituloEvaluacion = scanner.nextLine();
                    Evaluacion evaluacion = sistema.obtenerEvaluacionPorTitulo(tituloEvaluacion);
                    if (evaluacion == null) {
                        System.out.println("Evaluación no encontrada.");
                        break;
                    }
                    System.out.print("Ingrese el tema de las preguntas: ");
                    String temaPreguntas = scanner.nextLine();
                    List<Pregunta> preguntas = sistema.obtenerPreguntasPorTema(temaPreguntas);
                    System.out.print("Ingrese la cantidad de preguntas a agregar (0 para todas): ");
                    int cantidad = scanner.nextInt();
                    if (cantidad == 0) {
                        evaluacion.agregarPreguntas(preguntas);
                    } else {
                        evaluacion.agregarPreguntas(preguntas, cantidad);
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el título de la evaluación: ");
                    String tituloEvalNota = scanner.nextLine();
                    Evaluacion evalNota = sistema.obtenerEvaluacionPorTitulo(tituloEvalNota);
                    if (evalNota == null) {
                        System.out.println("Evaluación no encontrada.");
                        break;
                    }
                    System.out.print("Ingrese la nota: ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.print("¿Desea agregar un comentario? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        System.out.print("Ingrese el comentario: ");
                        String comentario = scanner.nextLine();
                        evalNota.registrarNota(nota, comentario);
                    } else {
                        evalNota.registrarNota(nota);
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el título de la evaluación: ");
                    String tituloEvalMostrar = scanner.nextLine();
                    Evaluacion evalMostrar = sistema.obtenerEvaluacionPorTitulo(tituloEvalMostrar);
                    if (evalMostrar != null) {
                        System.out.println(evalMostrar);
                    } else {
                        System.out.println("Evaluación no encontrada.");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
