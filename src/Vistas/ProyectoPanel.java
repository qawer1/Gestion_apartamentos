package Vistas;

import controlador.ProyectoController;
import modelo.Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProyectoPanel extends JPanel {
    private JTextField txtIdProyecto;      // Campo para ingresar el ID del proyecto
    private JTextField txtNombre;          // Campo para ingresar el nombre del proyecto
    private JTextField txtNumeroTorre;     // Campo para ingresar el número de la torre
    private JTextArea txtProyectos;        // Área para mostrar proyectos
    private ProyectoController proyectoController;

    public ProyectoPanel() {
        proyectoController = new ProyectoController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.GRAY); // Fondo gris para el panel

        // Crear panel de entrada con GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY); // Fondo gris para panel de entrada

        // ID del Proyecto
        JLabel labelIdProyecto = new JLabel("ID del Proyecto:");
        labelIdProyecto.setForeground(Color.WHITE);
        inputPanel.add(labelIdProyecto);
        txtIdProyecto = new JTextField();
        txtIdProyecto.setForeground(Color.BLACK);
        inputPanel.add(txtIdProyecto);

        // Nombre del Proyecto
        JLabel labelNombre = new JLabel("Nombre del Proyecto:");
        labelNombre.setForeground(Color.WHITE);
        inputPanel.add(labelNombre);
        txtNombre = new JTextField();
        txtNombre.setForeground(Color.BLACK);
        inputPanel.add(txtNombre);

        // Número de Torre
        JLabel labelNumeroTorre = new JLabel("Número de Torre:");
        labelNumeroTorre.setForeground(Color.WHITE);
        inputPanel.add(labelNumeroTorre);
        txtNumeroTorre = new JTextField();
        txtNumeroTorre.setForeground(Color.BLACK);
        inputPanel.add(txtNumeroTorre);

        // Crear panel de botones con FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.GRAY); // Fondo gris para el panel de botones

        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        // Ajustar tamaño de los botones para hacerlos más compactos
        ajustarBoton(btnCrear);
        ajustarBoton(btnLeer);
        ajustarBoton(btnEliminar);
        ajustarBoton(btnEditar);

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Agregar los paneles de entrada y botones al panel principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Crear área de texto para mostrar los proyectos
        txtProyectos = new JTextArea(10, 30);
        txtProyectos.setEditable(false);
        txtProyectos.setForeground(Color.BLACK); // Cambiar color de letras a negro
        add(new JScrollPane(txtProyectos), BorderLayout.SOUTH);

        // Acción para crear proyecto
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProyecto = Integer.parseInt(txtIdProyecto.getText()); // Leer el ID del Proyecto
                    String nombre = txtNombre.getText();
                    int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());

                    proyectoController.crearProyecto(idProyecto, nombre, numeroTorre);
                    JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos.");
                }
            }
        });

        // Acción para leer proyectos
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Proyecto> proyectos = proyectoController.obtenerProyectos();
                txtProyectos.setText(""); // Limpiar el área de texto antes de listar los proyectos
                if (proyectos.isEmpty()) {
                    txtProyectos.append("No hay proyectos registrados.\n");
                } else {
                    for (Proyecto proyecto : proyectos) {
                        txtProyectos.append("ID Proyecto: " + proyecto.getIdProyecto() + 
                                             ", Nombre: " + proyecto.getNombre() + 
                                             ", Torres: " + proyecto.getNumeroTorres() + "\n");
                    }
                }
            }
        });

        // Acción para eliminar proyecto
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                    proyectoController.eliminarProyecto(idProyecto);
                    JOptionPane.showMessageDialog(null, "Proyecto eliminado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID válido.");
                }
            }
        });

        // Acción para editar proyecto
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                    String nombre = txtNombre.getText();
                    int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());

                    proyectoController.actualizarProyecto(idProyecto, nombre, numeroTorre);
                    JOptionPane.showMessageDialog(null, "Proyecto actualizado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos.");
                }
            }
        });
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtIdProyecto.setText("");
        txtNombre.setText("");
        txtNumeroTorre.setText("");
    }

    // Método para ajustar el tamaño de los botones
    private void ajustarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setPreferredSize(new Dimension(100, 25)); // Tamaño compacto similar al primer código
        boton.setMinimumSize(new Dimension(100, 25));
        boton.setMaximumSize(new Dimension(100, 25));
    }
}
