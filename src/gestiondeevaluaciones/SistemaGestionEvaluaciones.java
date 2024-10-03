package gestiondeevaluaciones;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestionEvaluaciones extends JFrame {
    private BancoDePreguntas bancoPreguntas;
    private List<Evaluacion> evaluaciones;

    public SistemaGestionEvaluaciones() {
        bancoPreguntas = new BancoDePreguntas();
        evaluaciones = new ArrayList<>();

        setTitle("Sistema de Gestión de Evaluaciones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        // Botones
        JButton btnAgregarPreguntas = new JButton("Agregar Preguntas");
        JButton btnMostrarPreguntas = new JButton("Mostrar Preguntas por Tema");
        JButton btnAgregarEvaluacion = new JButton("Agregar Evaluación");
        JButton btnEliminarPregunta = new JButton("Eliminar Pregunta");
        JButton btnEliminarEvaluacion = new JButton("Eliminar Evaluación");
        JButton btnModificarPregunta = new JButton("Modificar Pregunta");
        JButton btnModificarEvaluacion = new JButton("Modificar Evaluación");
        JButton btnAgregarNota = new JButton("Agregar Nota a Evaluación");
        JButton btnMostrarPreguntaEspecifica = new JButton("Mostrar Pregunta Específica");

        // Añadir botones al panel
        panel.add(btnAgregarPreguntas);
        panel.add(btnMostrarPreguntas);
        panel.add(btnAgregarEvaluacion);
        panel.add(btnEliminarPregunta);
        panel.add(btnEliminarEvaluacion);
        panel.add(btnModificarPregunta);
        panel.add(btnModificarEvaluacion);
        panel.add(btnAgregarNota);
        panel.add(btnMostrarPreguntaEspecifica);

        // Acciones de los botones
        btnAgregarPreguntas.addActionListener(e -> bancoPreguntas.agregarPreguntas());

        btnMostrarPreguntas.addActionListener(e -> {
            String tema = JOptionPane.showInputDialog("Ingrese el tema:");
            bancoPreguntas.mostrarPreguntasPorTema(tema);
        });

        btnAgregarEvaluacion.addActionListener(e -> {
            String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
            Evaluacion evaluacion = new Evaluacion(nombreEvaluacion);
            evaluaciones.add(evaluacion);
            System.out.println("Evaluación agregada: " + nombreEvaluacion);
        });

        btnEliminarPregunta.addActionListener(e -> {
            String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a eliminar:");
            bancoPreguntas.eliminarPregunta(enunciado);
            System.out.println("Pregunta eliminada: " + enunciado);
        });

        btnEliminarEvaluacion.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación a eliminar:");
            evaluaciones.removeIf(evaluacion -> evaluacion.getNombre().equalsIgnoreCase(nombre));
            System.out.println("Evaluación eliminada: " + nombre);
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
                    System.out.println("Evaluación modificada.");
                }
            }
        });

        btnAgregarNota.addActionListener(e -> {
            String nombreEvaluacion = JOptionPane.showInputDialog("Ingrese el nombre de la evaluación:");
            String notaStr = JOptionPane.showInputDialog("Ingrese la nota:");
            try {
                double nota = Double.parseDouble(notaStr);
                for (Evaluacion evaluacion : evaluaciones) {
                    if (evaluacion.getNombre().equalsIgnoreCase(nombreEvaluacion)) {
                        evaluacion.agregarNota(nota);
                        System.out.println("Nota agregada a la evaluación " + nombreEvaluacion);
                        return;
                    }
                }
                throw new EvaluacionNoEncontradaException("Evaluación no encontrada: " + nombreEvaluacion);
            } catch (NumberFormatException ex) {
                System.out.println("La nota ingresada no es válida.");
            } catch (NotaInvalidaException ex) {
                System.out.println(ex.getMessage());
            } catch (EvaluacionNoEncontradaException ex) {
                System.out.println(ex.getMessage());
            }
        });

        btnMostrarPreguntaEspecifica.addActionListener(e -> {
            String enunciado = JOptionPane.showInputDialog("Ingrese el enunciado de la pregunta a mostrar:");
            try {
                bancoPreguntas.mostrarPreguntaEspecifica(enunciado);
            } catch (PreguntaNoEncontradaException ex) {
                System.out.println(ex.getMessage());
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaGestionEvaluaciones();
    }
}
