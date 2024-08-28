/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samue
 */
public class Menu{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaGestion sistema = new SistemaGestion();

        int opcion;

        do {
            System.out.println("1. Agregar Pregunta al Banco");
            System.out.println("2. Agregar Preguntas en Lote al Banco");
            System.out.println("3. Crear Evaluación desde Banco de Preguntas");
            System.out.println("4. Registrar Nota en Evaluación");
            System.out.println("5. Registrar Nota con Comentario en Evaluación");
            System.out.println("6. Mostrar Evaluaciones");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Agregar Pregunta al Banco
                    System.out.print("Ingrese el enunciado de la pregunta: ");
                    String enunciado = scanner.nextLine();
                    System.out.print("Ingrese el tema de la pregunta: ");
                    String tema = scanner.nextLine();
                    Pregunta nuevaPregunta = new Pregunta(enunciado, tema);
                    sistema.getBancoDePreguntas().agregarPregunta(nuevaPregunta);
                    System.out.println("Pregunta agregada exitosamente.");
                    break;

                case 2:
                    // Agregar Preguntas en Lote al Banco
                    List<Pregunta> preguntasLote = new ArrayList<>();
                    System.out.print("¿Cuántas preguntas desea agregar?: ");
                    int numPreguntas = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    for (int i = 0; i < numPreguntas; i++) {
                        System.out.print("Ingrese el enunciado de la pregunta: ");
                        enunciado = scanner.nextLine();
                        System.out.print("Ingrese el tema de la pregunta: ");
                        tema = scanner.nextLine();
                        preguntasLote.add(new Pregunta(enunciado, tema));
                    }

                    sistema.getBancoDePreguntas().agregarPregunta(preguntasLote);
                    System.out.println("Preguntas agregadas exitosamente.");
                    break;

                case 3:
                    // Crear Evaluación desde Banco de Preguntas
                    System.out.print("Ingrese el título de la evaluación: ");
                    String tituloEvaluacion = scanner.nextLine();
                    Evaluacion nuevaEvaluacion = new Evaluacion(tituloEvaluacion);

                    System.out.print("Ingrese el tema para seleccionar preguntas: ");
                    String temaSeleccionado = scanner.nextLine();
                    List<Pregunta> preguntasSeleccionadas = sistema.getBancoDePreguntas().obtenerPreguntasPorTema(temaSeleccionado);

                    for (Pregunta pregunta : preguntasSeleccionadas){
                        nuevaEvaluacion.agregarPregunta(pregunta);
                    }

                    sistema.agregarEvaluacion(nuevaEvaluacion);
                    System.out.println("Evaluación creada exitosamente.");
                    break;

                case 4:
                    // Registrar Nota en Evaluación
                    System.out.print("Ingrese el título de la evaluación: ");
                    String titulo = scanner.nextLine();
                    Evaluacion evaluacion = sistema.obtenerEvaluacion(titulo);

                    if (evaluacion != null){
                        System.out.print("Ingrese la nota: ");
                        double nota = scanner.nextDouble();
                        evaluacion.registrarNota(nota);
                        System.out.println("Nota registrada exitosamente.");
                    } else{
                        System.out.println("Evaluación no encontrada.");
                    }
                    break;

                case 5:
                    // Registrar Nota con Comentario en Evaluación
                    System.out.print("Ingrese el título de la evaluación: ");
                    titulo = scanner.nextLine();
                    evaluacion = sistema.obtenerEvaluacion(titulo);

                    if (evaluacion != null){
                        System.out.print("Ingrese la nota: ");
                        double nota = scanner.nextDouble();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Ingrese el comentario: ");
                        String comentario = scanner.nextLine();
                        evaluacion.registrarNota(nota, comentario);
                        System.out.println("Nota con comentario registrada exitosamente.");
                    } else {
                        System.out.println("Evaluación no encontrada.");
                    }
                    break;

                case 6:
                    // Mostrar Evaluaciones
                    sistema.mostrarEvaluaciones();
                    break;

                case 7:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while(opcion != 7);

        scanner.close();
    }
}
