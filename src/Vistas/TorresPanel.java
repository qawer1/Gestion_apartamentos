package Vistas;

import controlador.TorreController;
import controlador.ProyectoController;
import modelo.Torre;
import modelo.Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TorresPanel extends JPanel {
    private JTextField txtId; // Campo para ID de la torre
    private JTextField txtNumeroTorre; // Campo para el número de la torre
    private JTextField txtNumeroApartamentos; // Campo para el número de apartamentos
    private JTextArea txtTorres; // Área de texto para mostrar torres
    private JComboBox<String> cmbProyectos; // JComboBox para seleccionar proyecto
    private TorreController torreController;
    private ProyectoController proyectoController;

    public TorresPanel() {
        torreController = new TorreController();
        proyectoController = new ProyectoController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.GRAY); // Fondo gris para el panel

        // Crear panel de entrada con GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY); // Fondo gris para panel de entrada

        // ID de la Torre
        JLabel labelId = new JLabel("ID de la Torre:");
        labelId.setForeground(Color.WHITE);
        inputPanel.add(labelId);
        txtId = new JTextField();
        inputPanel.add(txtId);

        // JComboBox para seleccionar el proyecto
        JLabel labelProyecto = new JLabel("Seleccionar Proyecto:");
        labelProyecto.setForeground(Color.WHITE);
        inputPanel.add(labelProyecto);
        cmbProyectos = new JComboBox<>();
        cargarProyectos();
        inputPanel.add(cmbProyectos);

        // Número de la Torre
        JLabel labelNumeroTorre = new JLabel("Número de Torre:");
        labelNumeroTorre.setForeground(Color.WHITE);
        inputPanel.add(labelNumeroTorre);
        txtNumeroTorre = new JTextField();
        inputPanel.add(txtNumeroTorre);

        // Número de Apartamentos
        JLabel labelNumeroApartamentos = new JLabel("Número de Apartamentos:");
        labelNumeroApartamentos.setForeground(Color.WHITE);
        inputPanel.add(labelNumeroApartamentos);
        txtNumeroApartamentos = new JTextField();
        inputPanel.add(txtNumeroApartamentos);

        // Panel de botones (ahora horizontal)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Disposición horizontal
        buttonPanel.setBackground(Color.GRAY); // Fondo gris para el panel de botones

        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        // Ajustar tamaño de los botones para que sean compactos
        ajustarBoton(btnCrear);
        ajustarBoton(btnLeer);
        ajustarBoton(btnEliminar);
        ajustarBoton(btnEditar);

        // Añadir botones al panel
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Añadir paneles al layout principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER); // Añadir el panel de botones al centro

        // Área de texto para mostrar torres
        txtTorres = new JTextArea(10, 30);
        txtTorres.setEditable(false);
        txtTorres.setForeground(Color.BLACK); // Cambiar color de letras a negro
        add(new JScrollPane(txtTorres), BorderLayout.SOUTH); // Cambiar a la parte inferior

        // ActionListener para el botón "Crear Torre"
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idTorre = Integer.parseInt(txtId.getText());
                    int idProyecto = obtenerIdProyectoSeleccionado();
                    int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                    int numeroApartamentos = Integer.parseInt(txtNumeroApartamentos.getText());
                    torreController.crearTorre(idTorre, idProyecto, numeroTorre, numeroApartamentos);
                    JOptionPane.showMessageDialog(null, "Torre creada exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos.");
                }
            }
        });

        // ActionListener para el botón "Leer Torres"
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Torre> torres = torreController.obtenerTorres();
                txtTorres.setText("");  // Limpiar el área de texto antes de listar las torres
                if (torres.isEmpty()) {
                    txtTorres.append("No hay torres registradas.\n");
                } else {
                    for (Torre torre : torres) {
                        txtTorres.append(String.format("ID Torre: %d\n", torre.getIdTorre()));
                        txtTorres.append(String.format("ID Proyecto: %d\n", torre.getIdProyecto()));
                        txtTorres.append(String.format("Número Torre: %d\n", torre.getNumero_torre()));
                        txtTorres.append(String.format("Número Apartamentos: %d\n", torre.getNumeroApartamentos()));
                        txtTorres.append("----------------------------\n");
                    }
                }
            }
        });

        // ActionListener para el botón "Eliminar Torre"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idTorre = Integer.parseInt(txtId.getText());
                    torreController.eliminarTorre(idTorre);
                    JOptionPane.showMessageDialog(null, "Torre eliminada exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID válido.");
                }
            }
        });

        // ActionListener para el botón "Editar Torre"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idTorre = Integer.parseInt(txtId.getText());
                    int idProyecto = obtenerIdProyectoSeleccionado();
                    int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                    int numeroApartamentos = Integer.parseInt(txtNumeroApartamentos.getText());
                    torreController.actualizarTorre(idTorre, idProyecto, numeroTorre, numeroApartamentos);
                    JOptionPane.showMessageDialog(null, "Torre actualizada exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos.");
                }
            }
        });
    }

    // Método para cargar los proyectos en el JComboBox
    private void cargarProyectos() {
        List<Proyecto> proyectos = proyectoController.obtenerProyectos();
        cmbProyectos.removeAllItems();
        for (Proyecto proyecto : proyectos) {
            cmbProyectos.addItem(proyecto.getIdProyecto() + " - " + proyecto.getNombre());
        }
    }

    // Método para obtener el ID del proyecto seleccionado
    private int obtenerIdProyectoSeleccionado() {
        String proyectoSeleccionado = (String) cmbProyectos.getSelectedItem();
        return Integer.parseInt(proyectoSeleccionado.split(" - ")[0]); // Extraer el ID del proyecto
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtId.setText(""); // Limpiar el campo de ID
        cmbProyectos.setSelectedIndex(0); // Restablecer la selección de proyecto
        txtNumeroTorre.setText(""); // Limpiar el campo de número de torre
        txtNumeroApartamentos.setText(""); // Limpiar el campo de número de apartamentos
    }

    // Método para ajustar el tamaño de los botones
    private void ajustarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setPreferredSize(new Dimension(120, 25)); // Tamaño de botón un poco más grande
        boton.setMinimumSize(new Dimension(120, 25));
        boton.setMaximumSize(new Dimension(120, 25));
    }
}
