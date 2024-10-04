package gestiondeevaluaciones;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestionEvaluaciones extends JFrame {
    private List<Evaluacion> evaluaciones;
    private BancoDePreguntas bancoPreguntas;

    public SistemaGestionEvaluaciones() {
        evaluaciones = new ArrayList<>();
        bancoPreguntas = new BancoDePreguntas();
        inicializarInterfazGrafica();
    }

    private void inicializarInterfazGrafica() {
        setTitle("Sistema de Gestión de Evaluaciones");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Establecer un esquema de color sobrio
        Color fondoColor = new Color(240, 240, 240); // Color de fondo gris claro
        Color botonColor = new Color(70, 130, 180);  // Color de los botones azul acero
        Color textoBotonColor = Color.WHITE;

        // Panel principal con margen y fondo
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(fondoColor);

        // Etiqueta de título
        JLabel tituloLabel = new JLabel("Sistema de Gestión de Evaluaciones", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH);

        // Panel central para los botones con GridBagLayout
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(fondoColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Botones con estilo coherente
        JButton btnAgregarEvaluacion = crearBoton("Agregar Evaluación", botonColor, textoBotonColor);
        JButton btnMostrarEvaluaciones = crearBoton("Mostrar Evaluaciones", botonColor, textoBotonColor);
        JButton btnAgregarPreguntaBanco = crearBoton("Agregar Preguntas al Banco", botonColor, textoBotonColor);
        JButton btnMostrarPreguntas = crearBoton("Mostrar Preguntas", botonColor, textoBotonColor);
        JButton btnAgregarPreguntasEvaluacion = crearBoton("Agregar Preguntas a Evaluación", botonColor, textoBotonColor);
        JButton btnRegistrarNota = crearBoton("Registrar Nota a Evaluación", botonColor, textoBotonColor);
        JButton btnGuardarEvaluaciones = crearBoton("Guardar Evaluaciones en CSV", botonColor, textoBotonColor);
        JButton btnCargarEvaluaciones = crearBoton("Cargar Evaluaciones desde CSV", botonColor, textoBotonColor);
        JButton btnGuardarPreguntas = crearBoton("Guardar Preguntas en CSV", botonColor, textoBotonColor);
        JButton btnCargarPreguntas = crearBoton("Cargar Preguntas desde CSV", botonColor, textoBotonColor);
        JButton btnSalir = crearBoton("Salir", new Color(220, 53, 69), textoBotonColor);

        // Añadir botones al panel central en orden coherente
        gbc.gridx = 0; gbc.gridy = 0;
        panelCentral.add(btnAgregarEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panelCentral.add(btnMostrarEvaluaciones, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelCentral.add(btnAgregarPreguntaBanco, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panelCentral.add(btnMostrarPreguntas, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelCentral.add(btnAgregarPreguntasEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panelCentral.add(btnRegistrarNota, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelCentral.add(btnGuardarEvaluaciones, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panelCentral.add(btnCargarEvaluaciones, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelCentral.add(btnGuardarPreguntas, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panelCentral.add(btnCargarPreguntas, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panelCentral.add(btnSalir, gbc);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        add(panelPrincipal);

        // Asignar acciones a los botones (sin cambios en la funcionalidad)
        btnAgregarEvaluacion.addActionListener(e -> agregarEvaluacion());
        btnMostrarEvaluaciones.addActionListener(e -> mostrarEvaluaciones());
        btnAgregarPreguntaBanco.addActionListener(e -> agregarPreguntasAlBanco());
        btnMostrarPreguntas.addActionListener(e -> mostrarPreguntas());
        btnAgregarPreguntasEvaluacion.addActionListener(e -> agregarPreguntasAEvaluacion());
        btnRegistrarNota.addActionListener(e -> registrarNotaAEvaluacion());
        btnGuardarEvaluaciones.addActionListener(e -> guardarEvaluacionesEnCSV());
        btnCargarEvaluaciones.addActionListener(e -> cargarEvaluacionesDesdeCSV());
        btnGuardarPreguntas.addActionListener(e -> guardarPreguntasEnCSV());
        btnCargarPreguntas.addActionListener(e -> cargarPreguntasDesdeCSV());
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Método auxiliar para crear botones con estilo
    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        return boton;
    }


    private void agregarEvaluacion() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            evaluaciones.add(new Evaluacion(nombre));
            JOptionPane.showMessageDialog(null, "Evaluación '" + nombre + "' agregada.");
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de evaluación inválido.");
        }
    }

    private void mostrarEvaluaciones() {
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

    private void agregarPreguntasAlBanco() {
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

    private void mostrarPreguntas() {
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

    private void agregarPreguntasAEvaluacion() {
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

    private void registrarNotaAEvaluacion() {
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

    private Evaluacion buscarEvaluacion(String nombre) {
        for (Evaluacion e : evaluaciones) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    // Métodos para guardar y cargar evaluaciones y preguntas en CSV

    private void guardarEvaluacionesEnCSV() {
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

    private void cargarEvaluacionesDesdeCSV() {
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

    private void guardarPreguntasEnCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("preguntas.csv"))) {
            for (Pregunta pregunta : bancoPreguntas.getTodasLasPreguntas()) {
                writer.println(pregunta.getEnunciado() + "," + pregunta.getTema());
            }
            JOptionPane.showMessageDialog(null, "Preguntas guardadas exitosamente en preguntas.csv.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar preguntas: " + e.getMessage());
        }
    }

    private void cargarPreguntasDesdeCSV() {
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

    public static void main(String[] args) {
        new SistemaGestionEvaluaciones();
    }
}



