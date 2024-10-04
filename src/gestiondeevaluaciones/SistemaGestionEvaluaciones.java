package gestiondeevaluaciones;

import javax.swing.*;
import java.awt.*;

public class SistemaGestionEvaluaciones extends JFrame {
    private Controlador controlador;

    public SistemaGestionEvaluaciones() {
        controlador = new Controlador();
        inicializarInterfazGrafica();
    }

    private void inicializarInterfazGrafica() {
        setTitle("Sistema de Gestión de Evaluaciones");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Esquema de colores
        Color fondoColor = new Color(240, 240, 240);
        Color botonColor = new Color(70, 130, 180);
        Color textoBotonColor = Color.WHITE;
        Color tituloFondoColor = new Color(173, 216, 230);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(fondoColor);

        // Panel del título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(tituloFondoColor);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel tituloLabel = new JLabel("Sistema de Gestión de Evaluaciones", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(Color.BLACK);

        panelTitulo.add(tituloLabel);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(fondoColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Creación de botones
        JButton btnAgregarEvaluacion = crearBoton("Agregar Evaluación", botonColor, textoBotonColor);
        JButton btnMostrarEvaluaciones = crearBoton("Mostrar Evaluaciones", botonColor, textoBotonColor);
        JButton btnEliminarEvaluacion = crearBoton("Eliminar Evaluación", botonColor, textoBotonColor);
        JButton btnAgregarPreguntaBanco = crearBoton("Agregar Preguntas al Banco", botonColor, textoBotonColor);
        JButton btnMostrarPreguntas = crearBoton("Mostrar Preguntas", botonColor, textoBotonColor);
        JButton btnEliminarPregunta = crearBoton("Eliminar Pregunta", botonColor, textoBotonColor);
        JButton btnAgregarPreguntasEvaluacion = crearBoton("Agregar Preguntas a Evaluación", botonColor, textoBotonColor);
        JButton btnRegistrarNota = crearBoton("Registrar Nota a Evaluación", botonColor, textoBotonColor);
        JButton btnGuardarEvaluaciones = crearBoton("Guardar Evaluaciones en CSV", botonColor, textoBotonColor);
        JButton btnCargarEvaluaciones = crearBoton("Cargar Evaluaciones desde CSV", botonColor, textoBotonColor);
        JButton btnGuardarPreguntas = crearBoton("Guardar Preguntas en CSV", botonColor, textoBotonColor);
        JButton btnCargarPreguntas = crearBoton("Cargar Preguntas desde CSV", botonColor, textoBotonColor);
        JButton btnSalir = crearBoton("Salir", new Color(220, 53, 69), textoBotonColor);

        // Añadir botones al panel central
        gbc.gridx = 0; gbc.gridy = 0;
        panelCentral.add(btnAgregarEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panelCentral.add(btnMostrarEvaluaciones, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelCentral.add(btnEliminarEvaluacion, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panelCentral.add(btnRegistrarNota, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelCentral.add(btnAgregarPreguntaBanco, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panelCentral.add(btnMostrarPreguntas, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelCentral.add(btnEliminarPregunta, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panelCentral.add(btnAgregarPreguntasEvaluacion, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelCentral.add(btnGuardarEvaluaciones, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panelCentral.add(btnCargarEvaluaciones, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelCentral.add(btnGuardarPreguntas, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        panelCentral.add(btnCargarPreguntas, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        panelCentral.add(btnSalir, gbc);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        add(panelPrincipal);

        // Asignar acciones a los botones
        btnAgregarEvaluacion.addActionListener(e -> controlador.agregarEvaluacion());
        btnMostrarEvaluaciones.addActionListener(e -> controlador.mostrarEvaluaciones());
        btnEliminarEvaluacion.addActionListener(e -> controlador.eliminarEvaluacion());
        btnAgregarPreguntaBanco.addActionListener(e -> controlador.agregarPreguntasAlBanco());
        btnMostrarPreguntas.addActionListener(e -> controlador.mostrarPreguntas());
        btnEliminarPregunta.addActionListener(e -> controlador.eliminarPregunta());
        btnAgregarPreguntasEvaluacion.addActionListener(e -> controlador.agregarPreguntasAEvaluacion());
        btnRegistrarNota.addActionListener(e -> controlador.registrarNotaAEvaluacion());
        btnGuardarEvaluaciones.addActionListener(e -> controlador.guardarEvaluacionesEnCSV());
        btnCargarEvaluaciones.addActionListener(e -> controlador.cargarEvaluacionesDesdeCSV());
        btnGuardarPreguntas.addActionListener(e -> controlador.guardarPreguntasEnCSV());
        btnCargarPreguntas.addActionListener(e -> controlador.cargarPreguntasDesdeCSV());
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

    public static void main(String[] args) {
        new SistemaGestionEvaluaciones();
    }
}




