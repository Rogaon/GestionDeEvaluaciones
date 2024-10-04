package gestiondeevaluaciones;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestionEvaluaciones extends JFrame {
    private BancoDePreguntas bancoPreguntas;
    private List<Evaluacion> evaluaciones;

    public SistemaGestionEvaluaciones() {
        bancoPreguntas = new BancoDePreguntas();
        evaluaciones = new ArrayList<>();

        // Configurar la ventana principal
        setTitle("Sistema de Gestión de Evaluaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con el título
        JLabel tituloLabel = new JLabel("Sistema de Gestión de Evaluaciones", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(tituloLabel, BorderLayout.NORTH);

        // Crear el panel principal con un GridBagLayout para organizar los botones
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Botones
        JButton btnAgregarPreguntas = new JButton("Agregar Preguntas al Banco");
        JButton btnMostrarPreguntas = new JButton("Mostrar Preguntas por Tema");
        JButton btnAgregarEvaluacion = new JButton("Agregar Evaluación");
        JButton btnEliminarPregunta = new JButton("Eliminar Pregunta");
        JButton btnEliminarEvaluacion = new JButton("Eliminar Evaluación");
        JButton btnModificarPregunta = new JButton("Modificar Pregunta");
        JButton btnModificarEvaluacion = new JButton("Modificar Evaluación");
        JButton btnAgregarNota = new JButton("Agregar Nota a Evaluación");
        JButton btnMostrarPreguntaEspecifica = new JButton("Mostrar Pregunta Específica");
        JButton btnMostrarEvaluaciones = new JButton("Mostrar Todas las Evaluaciones");
        JButton btnMostrarEvaluacionEspecifica = new JButton("Mostrar Evaluación Específica");
        JButton btnMostrarTodasPreguntas = new JButton("Mostrar Todas las Preguntas");

        // Añadir los botones con coordenadas en el GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panelCentral.add(btnAgregarPreguntas, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panelCentral.add(btnMostrarPreguntas, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelCentral.add(btnAgregarEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panelCentral.add(btnEliminarPregunta, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelCentral.add(btnEliminarEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panelCentral.add(btnModificarPregunta, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelCentral.add(btnModificarEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panelCentral.add(btnAgregarNota, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelCentral.add(btnMostrarPreguntaEspecifica, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panelCentral.add(btnMostrarEvaluaciones, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelCentral.add(btnMostrarEvaluacionEspecifica, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        panelCentral.add(btnMostrarTodasPreguntas, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones de acción
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalir = new JButton("Salir");
        panelInferior.add(btnSalir);
        add(panelInferior, BorderLayout.SOUTH);

        // Configurar las acciones de los botones
        btnAgregarPreguntas.addActionListener(e -> bancoPreguntas.agregarPreguntas());

        btnMostrarPreguntas.addActionListener(e -> {
            String tema = JOptionPane.showInputDialog("Ingrese el tema:");
            bancoPreguntas.mostrarPreguntasPorTema(tema);
        });

        btnAgregarEvaluacion.addActionListener(e -> {
            String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
            Evaluacion evaluacion = new Evaluacion(nombreEvaluacion);
            evaluaciones.add(evaluacion);
            JOptionPane.showMessageDialog(null, "Evaluación agregada: " + nombreEvaluacion);
        });

        btnEliminarPregunta.addActionListener(e -> {
            String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a eliminar:");
            bancoPreguntas.eliminarPregunta(enunciado);
            JOptionPane.showMessageDialog(null, "Pregunta eliminada: " + enunciado);
        });

        btnEliminarEvaluacion.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación a eliminar:");
            evaluaciones.removeIf(evaluacion -> evaluacion.getNombre().equalsIgnoreCase(nombre));
            JOptionPane.showMessageDialog(null, "Evaluación eliminada: " + nombre);
        });

        btnModificarPregunta.addActionListener(e -> {
            String enunciadoOriginal = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a modificar:");
            String nuevoEnunciado = JOptionPane.showInputDialog("Ingrese el nuevo enunciado:");
            String nuevoTema = JOptionPane.showInputDialog("Ingrese el nuevo tema:");
            bancoPreguntas.modificarPregunta(enunciadoOriginal, nuevoEnunciado, nuevoTema);
        });

        btnModificarEvaluacion.addActionListener(e -> {
            String nombreOriginal = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación a modificar:");
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la evaluación:");
            for (Evaluacion evaluacion : evaluaciones) {
                if (evaluacion.getNombre().equalsIgnoreCase(nombreOriginal)) {
                    evaluacion.modificarEvaluacion(nuevoNombre);
                    JOptionPane.showMessageDialog(null, "Evaluación modificada.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Evaluación no encontrada.");
        });

        btnAgregarNota.addActionListener(e -> {
            String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
            String notaStr = JOptionPane.showInputDialog("Ingrese la nota:");
            try {
                double nota = Double.parseDouble(notaStr);
                for (Evaluacion evaluacion : evaluaciones) {
                    if (evaluacion.getNombre().equalsIgnoreCase(nombreEvaluacion)) {
                        evaluacion.agregarNota(nota);
                        JOptionPane.showMessageDialog(null, "Nota agregada a la evaluación " + nombreEvaluacion);
                        return;
                    }
                }
                throw new EvaluacionNoEncontradaException("Evaluación no encontrada: " + nombreEvaluacion);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La nota ingresada no es válida.");
            } catch (NotaInvalidaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (EvaluacionNoEncontradaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        // Mostrar todas las evaluaciones
        btnMostrarEvaluaciones.addActionListener(e -> {
            if (evaluaciones.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay evaluaciones registradas.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Evaluacion evaluacion : evaluaciones) {
                    sb.append(evaluacion.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Todas las Evaluaciones", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Mostrar evaluación específica
        btnMostrarEvaluacionEspecifica.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
            for (Evaluacion evaluacion : evaluaciones) {
                if (evaluacion.getNombre().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(null, evaluacion.toString(), "Evaluación", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Evaluación no encontrada.");
        });

        // Mostrar todas las preguntas
        btnMostrarTodasPreguntas.addActionListener(e -> {
            List<Pregunta> preguntas = bancoPreguntas.getPreguntas();
            if (preguntas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay preguntas en el banco.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Pregunta pregunta : preguntas) {
                    sb.append(pregunta.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Todas las Preguntas", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnMostrarPreguntaEspecifica.addActionListener(e -> {
            String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a mostrar:");
            try {
                bancoPreguntas.mostrarPreguntaEspecifica(enunciado);
            } catch (PreguntaNoEncontradaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaGestionEvaluaciones();
    }
}
