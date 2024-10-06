package gestiondeevaluaciones;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Clase que actúa como controlador del sistema, manejando la lógica de negocio.
 */
public final class Controlador {
    private List<Evaluacion> evaluaciones;
    private BancoDePreguntas bancoPreguntas;
    
    /**
     * Constructor que inicializa las colecciones y carga los datos.
     */
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
    
    /**
     * Exporta las evaluaciones a un archivo Excel.
     * 
     * @param nombreArchivo El nombre del archivo Excel a crear.
     */
    public void exportarEvaluacionesAExcel(String nombreArchivo) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Evaluaciones");

        // Encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nombre Evaluación");
        headerRow.createCell(1).setCellValue("Número de Preguntas");

        // Poblamos las filas con los datos de las evaluaciones
        int rowNum = 1;
        for (Evaluacion evaluacion : evaluaciones) { // Asume que tienes una lista de evaluaciones
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(evaluacion.getNombre());
            row.createCell(1).setCellValue(evaluacion.getPreguntas().size());
        }

        // Escribir el archivo
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
            workbook.write(fileOut);
            System.out.println("Archivo Excel creado: " + nombreArchivo);
        } catch (IOException e) {
        }

        // Cerrar el workbook
        try {
            workbook.close();
        } catch (IOException e) {
        }
    }
    /**
    * Método para agregar una nueva evaluación al sistema.
    */
    public void agregarEvaluacion() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            // Verificar si la evaluación ya existe
            for (Evaluacion evaluacion : evaluaciones) {
                if (evaluacion.getNombre().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(null, "La evaluación '" + nombre + "' ya existe.");
                    return; // Salir del método si ya existe
                }
            }
            // Si no existe, agregar la evaluación
            evaluaciones.add(new Evaluacion(nombre));
            JOptionPane.showMessageDialog(null, "Evaluación '" + nombre + "' agregada.");
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de evaluación inválido.");
        }
    }

    /**
     * Método para mostrar todas las evaluaciones registradas.
     */
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

    /**
     * Método para agregar preguntas al banco de preguntas.
     */
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

    /**
     * Método para mostrar todas las preguntas del banco.
     */
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

    /**
     * Método para agregar preguntas a una evaluación existente.
     */
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

    /**
     * Método para registrar una nota a una evaluación.
     */
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
            JOptionPane.showMessageDialog(null, "Formato de nota inválida.");
        } catch (NotaInvalidaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Método para eliminar una pregunta del banco.
     */
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

    /**
     * Método para eliminar una evaluación.
     */
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

    /**
     * Método para modificar una pregunta existente.
     */
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

    /**
     * Método para mostrar evaluaciones dentro de un rango de notas.
     */
    public void mostrarEvaluacionesPorNota() {
        String notaMinStr = JOptionPane.showInputDialog("Ingrese la nota mínima:");
        String notaMaxStr = JOptionPane.showInputDialog("Ingrese la nota máxima:");
        try {
            double notaMin = Double.parseDouble(notaMinStr);
            double notaMax = Double.parseDouble(notaMaxStr);
            StringBuilder sb = new StringBuilder();
            for (Evaluacion evaluacion : evaluaciones) {
                for (Double nota : evaluacion.getNotas()) {
                    if (nota >= notaMin && nota <= notaMax) {
                        sb.append(evaluacion.toString()).append("\n");
                        break;
                    }
                }
            }
            if (sb.length() == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron evaluaciones en ese rango de notas.");
            } else {
                JTextArea textArea = new JTextArea(sb.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
                JOptionPane.showMessageDialog(null, scrollPane, "Evaluaciones por Nota", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada de nota inválida.");
        }
    }

    /**
     * Método para mostrar preguntas por tema.
     */
    public void mostrarPreguntasPorTema() {
        String tema = JOptionPane.showInputDialog("Ingrese el tema:");
        if (tema == null || tema.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tema inválido.");
            return;
        }
        List<Pregunta> preguntasPorTema;
        try {
            preguntasPorTema = bancoPreguntas.obtenerPreguntasPorTema(tema);
            if (preguntasPorTema.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay preguntas para el tema: " + tema);
            } else {
                StringBuilder sb = new StringBuilder();
                for (Pregunta pregunta : preguntasPorTema) {
                    sb.append(pregunta.toString()).append("\n");
                }
                JTextArea textArea = new JTextArea(sb.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
                JOptionPane.showMessageDialog(null, scrollPane, "Preguntas por Tema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PreguntaNoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Método para generar un reporte en archivo TXT con las evaluaciones y preguntas.
     */
    public void generarReporte() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reporte.txt"))) {
            writer.println("Reporte de Evaluaciones y Preguntas");
            writer.println("====================================");
            writer.println();
            for (Evaluacion evaluacion : evaluaciones) {
                writer.println(evaluacion.toString());
                writer.println();
            }
            JOptionPane.showMessageDialog(null, "Reporte generado exitosamente en reporte.txt.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }

    /**
     * Método para guardar las evaluaciones en un archivo CSV.
     */
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
            // No es necesario mostrar mensaje aquí
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar evaluaciones: " + e.getMessage());
        }
    }

    /**
     * Método para cargar las evaluaciones desde un archivo CSV.
     */
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
                            evaluacion.agregarPreguntas(pregunta);
                        }
                    }
                }
                evaluaciones.add(evaluacion);
            }
            // No es necesario mostrar mensaje aquí
        } catch (IOException e) {
            // No es necesario mostrar mensaje aquí
        }
    }

    /**
     * Método para guardar las preguntas en un archivo CSV.
     */
    public void guardarPreguntasEnCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("preguntas.csv"))) {
            for (Pregunta pregunta : bancoPreguntas.getTodasLasPreguntas()) {
                writer.println(pregunta.getEnunciado() + "," + pregunta.getTema());
            }
            // No es necesario mostrar mensaje aquí
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar preguntas: " + e.getMessage());
        }
    }

    /**
     * Método para cargar las preguntas desde un archivo CSV.
     */
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
            // No es necesario mostrar mensaje aquí
        } catch (IOException e) {
            // No es necesario mostrar mensaje aquí
        }
    }

    /**
     * Método privado para buscar una evaluación por nombre.
     */
    private Evaluacion buscarEvaluacion(String nombre) {
        for (Evaluacion e : evaluaciones) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }
    /**
     * Método público para buscar en ambos niveles(Evaluaciones y Preguntas).
     */
    public void buscarElementoEnSistema() {
        String criterio = JOptionPane.showInputDialog("Ingrese el criterio de búsqueda:");
        Object resultado = buscarEnSistema(criterio); // Llama al método de búsqueda principal

        if (resultado instanceof Evaluacion) {
            Evaluacion evaluacion = (Evaluacion) resultado;
            JOptionPane.showMessageDialog(null, "Evaluación encontrada: " + evaluacion.getNombre());
        } else if (resultado instanceof Pregunta) {
            Pregunta pregunta = (Pregunta) resultado;
            JOptionPane.showMessageDialog(null, "Pregunta encontrada: " + pregunta.getEnunciado());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún elemento con ese criterio.");
        }
    }
    /**
    * Busca en el sistema una evaluación o una pregunta según el criterio proporcionado.
    * 
    * @param criterio El criterio de búsqueda que se utilizará para encontrar una evaluación o pregunta.
    * @return La evaluación o pregunta encontrada, o null si no se encuentra ninguna coincidencia.
    */
    public Object buscarEnSistema(String criterio) {
    // Buscar primero en las evaluaciones
    Evaluacion evaluacion = buscarEvaluacion(criterio);
    if (evaluacion != null) {
        return evaluacion;
    }
    // Buscar en el banco de preguntas
    try {
        return bancoPreguntas.buscarPregunta(criterio);
    } catch (PreguntaNoEncontradaException e) {
        System.out.println("Pregunta no encontrada: " + e.getMessage());
    }

    // Retornar null si no se encuentra ni evaluación ni pregunta
    return null;
    }
    /**
    * Muestra un gráfico de barras que representa el promedio de notas por evaluación.
    * Este método crea un conjunto de datos basado en las evaluaciones disponibles y 
    * sus respectivas notas, y luego genera un gráfico utilizando JFreeChart.
    */
    public void mostrarGraficoDeBarras() {
    // Crear un conjunto de datos
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Obtener las evaluaciones y sus notas
    for (Evaluacion evaluacion : evaluaciones) {
        double notaPromedio = calcularPromedioNotas(evaluacion.getNotas());
        dataset.addValue(notaPromedio, "Notas", evaluacion.getNombre());
    }

    // Crear el gráfico
    JFreeChart chart = ChartFactory.createBarChart(
            "Promedio de Notas por Evaluación", // Título del gráfico
            "Evaluaciones",                      // Etiqueta del eje X
            "Promedio de Notas",                 // Etiqueta del eje Y
            dataset                              // Datos
    );

    // Crear un panel para el gráfico
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

    // Crear un nuevo JFrame para mostrar el gráfico
    JFrame frame = new JFrame("Gráfico de Notas");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add(chartPanel);
    frame.pack();
    frame.setVisible(true);
}

    /**
    * Método para calcular el promedio de notas de una evaluación.
    * 
    * @param notas Lista de notas de la evaluación.
    * @return El promedio de las notas.
    */
    private double calcularPromedioNotas(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0; // Retornar 0 si no hay notas
        }
        double suma = 0.0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size(); // Retornar el promedio
    }
    
    /**
    * Método para mostrar los temas disponibles en el banco de preguntas.
    */
    public void mostrarTemas() {
        List<String> temas = bancoPreguntas.obtenerTemas();
        if (temas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay temas disponibles.");
        } else {
            StringBuilder sb = new StringBuilder("Temas disponibles:\n");
            for (String tema : temas) {
                sb.append("- ").append(tema).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}

      

