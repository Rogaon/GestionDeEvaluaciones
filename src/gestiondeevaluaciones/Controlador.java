package gestiondeevaluaciones;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Evaluacion> evaluaciones;
    private BancoDePreguntas bancoPreguntas;

    public Controlador() {
        evaluaciones = new ArrayList<>();
        bancoPreguntas = new BancoDePreguntas();

        // Datos iniciales para el banco de preguntas
        bancoPreguntas.agregarPregunta(new Pregunta("¿Cuál es la capital de Francia?", "Geografía"));
        bancoPreguntas.agregarPregunta(new Pregunta("¿Cuál es el resultado de 2+2?", "Matemáticas"));
        bancoPreguntas.agregarPregunta(new Pregunta("¿Quién escribió 'Cien Años de Soledad'?", "Literatura"));

        // Datos iniciales para evaluaciones
        Evaluacion evalGeografia = new Evaluacion("Evaluación de Geografía");
        Evaluacion evalMatematicas = new Evaluacion("Evaluación de Matemáticas");

        try {
            evalGeografia.agregarPreguntas(bancoPreguntas.obtenerPreguntasPorTema("Geografía"));
            evalMatematicas.agregarPreguntas(bancoPreguntas.obtenerPreguntasPorTema("Matemáticas"));
        } catch (PreguntaNoEncontradaException e) {
            // Manejo de excepciones
        }

        evaluaciones.add(evalGeografia);
        evaluaciones.add(evalMatematicas);

        // Cargar datos al iniciar
        cargarEvaluacionesDesdeCSV();
        cargarPreguntasDesdeCSV();

        // Guardar datos al salir
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarEvaluacionesEnCSV();
            guardarPreguntasEnCSV();
        }));
    }

    public void agregarEvaluacion() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            evaluaciones.add(new Evaluacion(nombre));
            JOptionPane.showMessageDialog(null, "Evaluación '" + nombre + "' agregada.");
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de evaluación inválido.");
        }
    }

    public void mostrarEvaluaciones() {
        if (evaluaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay evaluaciones registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Evaluacion evaluacion : evaluaciones) {
            sb.append(evaluacion.toString()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Evaluaciones", JOptionPane.INFORMATION_MESSAGE);
    }

    public void agregarPreguntasAlBanco() {
        String tema = JOptionPane.showInputDialog("Ingrese el tema:");
        if (tema == null || tema.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tema inválido.");
            return;
        }
        String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de preguntas a agregar:");
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            for (int i = 0; i < cantidad; i++) {
                String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta " + (i + 1) + ":");
                if (enunciado != null && !enunciado.trim().isEmpty()) {
                    bancoPreguntas.agregarPregunta(new Pregunta(enunciado, tema));
                } else {
                    JOptionPane.showMessageDialog(null, "Enunciado inválido.");
                    i--;
                }
            }
            JOptionPane.showMessageDialog(null, "Preguntas agregadas al banco.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
        }
    }

    public void mostrarPreguntas() {
        List<Pregunta> todasPreguntas = bancoPreguntas.getTodasLasPreguntas();
        if (todasPreguntas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay preguntas en el banco.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Pregunta pregunta : todasPreguntas) {
            sb.append(pregunta.toString()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Preguntas", JOptionPane.INFORMATION_MESSAGE);
    }

    public void agregarPreguntasAEvaluacion() {
        String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
        Evaluacion evaluacion = buscarEvaluacion(nombreEvaluacion);

        if (evaluacion == null) {
            JOptionPane.showMessageDialog(null, "Evaluación no encontrada.");
            return;
        }

        String tema = JOptionPane.showInputDialog("Ingrese el tema de las preguntas:");
        List<Pregunta> preguntasDelTema;
        try {
            preguntasDelTema = bancoPreguntas.obtenerPreguntasPorTema(tema);
        } catch (PreguntaNoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de preguntas a agregar (máximo: " + preguntasDelTema.size() + "):");
        try {
            int cantidad = Integer.parseInt(cantidadStr);

            if (cantidad > preguntasDelTema.size() || cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                return;
            }

            List<Pregunta> preguntasSeleccionadas = preguntasDelTema.subList(0, cantidad);
            evaluacion.agregarPreguntas(preguntasSeleccionadas);
            JOptionPane.showMessageDialog(null, "Preguntas agregadas a la evaluación.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
        }
    }

    public void registrarNotaAEvaluacion() {
        String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
        Evaluacion evaluacion = buscarEvaluacion(nombreEvaluacion);

        if (evaluacion == null) {
            JOptionPane.showMessageDialog(null, "Evaluación no encontrada.");
            return;
        }

        String notaStr = JOptionPane.showInputDialog("Ingrese la nota (1.0 - 7.0):");
        try {
            double nota = Double.parseDouble(notaStr);
            evaluacion.registrarNota(nota);
            JOptionPane.showMessageDialog(null, "Nota registrada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de nota inválido.");
        } catch (NotaInvalidaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void guardarEvaluacionesEnCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("evaluaciones.csv"))) {
            for (Evaluacion evaluacion : evaluaciones) {
                writer.print(evaluacion.getNombre());
                for (Pregunta pregunta : evaluacion.getPreguntas()) {
                    writer.print("," + pregunta.getEnunciado() + ":" + pregunta.getTema());
                }
                // Guardar notas
                if (!evaluacion.getNotas().isEmpty()) {
                    writer.print(",Notas:");
                    for (Double nota : evaluacion.getNotas()) {
                        writer.print(nota + ";");
                    }
                }
                writer.println();
            }
            JOptionPane.showMessageDialog(null, "Evaluaciones guardadas exitosamente en evaluaciones.csv.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar evaluaciones: " + e.getMessage());
        }
    }

    public void cargarEvaluacionesDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("evaluaciones.csv"))) {
            String linea;
            evaluaciones.clear();
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                Evaluacion evaluacion = new Evaluacion(partes[0]);
                for (int i = 1; i < partes.length; i++) {
                    if (partes[i].startsWith("Notas:")) {
                        String notasStr = partes[i].substring(6); // Omitir "Notas:"
                        String[] notasArray = notasStr.split(";");
                        for (String notaStr : notasArray) {
                            if (!notaStr.isEmpty()) {
                                try {
                                    double nota = Double.parseDouble(notaStr);
                                    evaluacion.registrarNota(nota);
                                } catch (NumberFormatException | NotaInvalidaException e) {
                                    // Ignorar notas inválidas
                                }
                            }
                        }
                    } else {
                        String[] preguntaParte = partes[i].split(":");
                        if (preguntaParte.length == 2) {
                            Pregunta pregunta = new Pregunta(preguntaParte[0], preguntaParte[1]);
                            evaluacion.agregarPreguntas(List.of(pregunta));
                        }
                    }
                }
                evaluaciones.add(evaluacion);
            }
            JOptionPane.showMessageDialog(null, "Evaluaciones cargadas exitosamente desde evaluaciones.csv.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar evaluaciones: " + e.getMessage());
        }
    }

    public void guardarPreguntasEnCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("preguntas.csv"))) {
            for (Pregunta pregunta : bancoPreguntas.getTodasLasPreguntas()) {
                writer.println(pregunta.getEnunciado() + "," + pregunta.getTema());
            }
            JOptionPane.showMessageDialog(null, "Preguntas guardadas exitosamente en preguntas.csv.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar preguntas: " + e.getMessage());
        }
    }

    public void cargarPreguntasDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("preguntas.csv"))) {
            String linea;
            bancoPreguntas = new BancoDePreguntas();
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    Pregunta pregunta = new Pregunta(partes[0], partes[1]);
                    bancoPreguntas.agregarPregunta(pregunta);
                }
            }
            JOptionPane.showMessageDialog(null, "Preguntas cargadas exitosamente desde preguntas.csv.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar preguntas: " + e.getMessage());
        }
    }

     // método para eliminar una pregunta
    public void eliminarPregunta() {
        String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a eliminar:");
        if (enunciado == null || enunciado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enunciado inválido.");
            return;
        }
        try {
            bancoPreguntas.eliminarPregunta(enunciado);
            JOptionPane.showMessageDialog(null, "Pregunta eliminada exitosamente.");
        } catch (PreguntaNoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //  método para eliminar una evaluación
    public void eliminarEvaluacion() {
        String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación a eliminar:");
        if (nombreEvaluacion == null || nombreEvaluacion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre de evaluación inválido.");
            return;
        }
        Evaluacion evaluacion = buscarEvaluacion(nombreEvaluacion);
        if (evaluacion != null) {
            evaluaciones.remove(evaluacion);
            JOptionPane.showMessageDialog(null, "Evaluación eliminada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Evaluación no encontrada.");
        }
    }

    
    private Evaluacion buscarEvaluacion(String nombre) {
        for (Evaluacion e : evaluaciones) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }
    
    public void modificarPregunta() {
        String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a modificar:");
        if (enunciado == null || enunciado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enunciado inválido.");
            return;
        }
        try {
            Pregunta pregunta = bancoPreguntas.buscarPregunta(enunciado);
            String nuevoEnunciado = JOptionPane.showInputDialog("Ingrese el nuevo enunciado (deje en blanco para mantener):");
            String nuevoTema = JOptionPane.showInputDialog("Ingrese el nuevo tema (deje en blanco para mantener):");
            if (nuevoEnunciado != null && !nuevoEnunciado.trim().isEmpty()) {
                pregunta.setEnunciado(nuevoEnunciado);
            }
            if (nuevoTema != null && !nuevoTema.trim().isEmpty()) {
                String temaAnterior = pregunta.getTema();
                pregunta.setTema(nuevoTema);
                bancoPreguntas.actualizarTemaPregunta(pregunta, temaAnterior);
            }
            JOptionPane.showMessageDialog(null, "Pregunta modificada exitosamente.");
        } catch (PreguntaNoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }   

}

