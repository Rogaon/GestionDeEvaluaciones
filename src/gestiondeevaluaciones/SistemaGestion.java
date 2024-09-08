/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SistemaGestion {
    private Map<String, Evaluacion> evaluaciones;  // Usar Map en lugar de List
    private BancoDePreguntas bancoDePreguntas;

    public SistemaGestion() {
        this.evaluaciones = new HashMap<>();
        this.bancoDePreguntas = new BancoDePreguntas();
        
        // Datos iniciales
        crearEvaluacion("Evaluacion 1");
        crearEvaluacion("Evaluacion 2");
    }

    public void crearEvaluacion(String titulo) {
        evaluaciones.put(titulo, new Evaluacion(titulo));
    }

    public Evaluacion obtenerEvaluacionPorTitulo(String titulo) {
        return evaluaciones.get(titulo);
    }

    public boolean eliminarEvaluacion(String titulo) {
        return evaluaciones.remove(titulo) != null;
    }

    public void agregarPreguntaAlBanco(Pregunta pregunta) {
        bancoDePreguntas.agregarPregunta(pregunta);
    }

    public List<Pregunta> obtenerPreguntasPorTema(String tema) {
        return bancoDePreguntas.obtenerPreguntasPorTema(tema);
    }

    public List<String> obtenerTemas() {
        return bancoDePreguntas.obtenerTemas();
    }

    public boolean eliminarPregunta(String enunciado, String tema) {
        return bancoDePreguntas.eliminarPregunta(enunciado, tema);
    }

    public void mostrarOpcionesMenu(Scanner scanner) {
        System.out.println("1. Crear evaluación");
        System.out.println("2. Agregar pregunta al banco");
        System.out.println("3. Agregar preguntas a una evaluación");
        System.out.println("4. Registrar nota");
        System.out.println("5. Mostrar evaluación");
        System.out.println("6. Eliminar evaluación");
        System.out.println("7. Eliminar pregunta");
        System.out.println("8. Modificar nota");
        System.out.println("9. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el título de la evaluación: ");
                String titulo = scanner.nextLine();
                crearEvaluacion(titulo);
                break;
            case 2:
                System.out.print("Ingrese el enunciado de la pregunta: ");
                String enunciado = scanner.nextLine();
                System.out.print("Ingrese el tema de la pregunta: ");
                String tema = scanner.nextLine();
                agregarPreguntaAlBanco(new Pregunta(enunciado, tema));
                break;
            case 3:
                System.out.print("Ingrese el título de la evaluación: ");
                String tituloEvaluacion = scanner.nextLine();
                Evaluacion evaluacion = obtenerEvaluacionPorTitulo(tituloEvaluacion);
                if (evaluacion == null) {
                    System.out.println("Evaluación no encontrada.");
                    break;
                }
                System.out.print("Ingrese el tema de las preguntas: ");
                String temaPreguntas = scanner.nextLine();
                List<Pregunta> preguntas = obtenerPreguntasPorTema(temaPreguntas);
                System.out.print("Ingrese la cantidad de preguntas a agregar (0 para todas): ");
                int cantidad = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                if (cantidad == 0) {
                    evaluacion.agregarPreguntas(preguntas);
                } else {
                    evaluacion.agregarPreguntas(preguntas, cantidad);
                }
                break;
            case 4:
                System.out.print("Ingrese el título de la evaluación: ");
                String tituloEvalNota = scanner.nextLine();
                Evaluacion evalNota = obtenerEvaluacionPorTitulo(tituloEvalNota);
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
                Evaluacion evalMostrar = obtenerEvaluacionPorTitulo(tituloEvalMostrar);
                if (evalMostrar != null) {
                    System.out.println(evalMostrar);
                } else {
                    System.out.println("Evaluación no encontrada.");
                }
                break;
            case 6:
                System.out.print("Ingrese el título de la evaluación a eliminar: ");
                String tituloEliminar = scanner.nextLine();
                if (eliminarEvaluacion(tituloEliminar)) {
                    System.out.println("Evaluación eliminada exitosamente.");
                } else {
                    System.out.println("Evaluación no encontrada.");
                }
                break;
            case 7:
                System.out.print("Ingrese el enunciado de la pregunta a eliminar: ");
                String enunciadoEliminar = scanner.nextLine();
                System.out.print("Ingrese el tema de la pregunta: ");
                String temaEliminar = scanner.nextLine();
                if (eliminarPregunta(enunciadoEliminar, temaEliminar)) {
                    System.out.println("Pregunta eliminada exitosamente.");
                } else {
                    System.out.println("Pregunta no encontrada.");
                }
                break;
            case 8:
                System.out.print("Ingrese el título de la evaluación: ");
                String tituloEvalModificar = scanner.nextLine();
                Evaluacion evalModificar = obtenerEvaluacionPorTitulo(tituloEvalModificar);
                if (evalModificar == null) {
                    System.out.println("Evaluación no encontrada.");
                    break;
                }
                System.out.print("Ingrese la nota antigua: ");
                double notaAntigua = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                System.out.print("Ingrese la nueva nota: ");
                double notaNueva = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea
                System.out.print("Ingrese el nuevo comentario (puede dejar en blanco): ");
                String nuevoComentario = scanner.nextLine();
                if (evalModificar.modificarNota(notaAntigua, notaNueva, nuevoComentario)) {
                    System.out.println("Nota modificada exitosamente.");
                } else {
                    System.out.println("Nota no encontrada.");
                }
                break;
            case 9:
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
