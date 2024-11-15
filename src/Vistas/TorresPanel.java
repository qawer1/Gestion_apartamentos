package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.TorreController;
import controlador.ProyectoController;
import modelo.Torre;
import modelo.Proyecto;
import java.util.List;

public class TorresPanel extends JPanel {
    private JTextField txtId; // Campo para ID de la torre
    private JTextField txtNumeroTorre; // Campo para el número de la torre
    private JTextField txtNumeroApartamentos; // Campo para el número de apartamentos
    private JTextArea txtTorres;
    private JComboBox<String> cmbProyectos; // JComboBox para seleccionar proyecto
    private TorreController torreController;
    private ProyectoController proyectoController;

    public TorresPanel() {
        torreController = new TorreController();
        proyectoController = new ProyectoController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("ID de la Torre:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        // JComboBox para seleccionar el proyecto
        inputPanel.add(new JLabel("Seleccionar Proyecto:"));
        cmbProyectos = new JComboBox<>();
        cargarProyectos();
        inputPanel.add(cmbProyectos);

        inputPanel.add(new JLabel("Número de Torre:"));
        txtNumeroTorre = new JTextField();
        inputPanel.add(txtNumeroTorre);

        inputPanel.add(new JLabel("Número de Apartamentos:"));
        txtNumeroApartamentos = new JTextField();
        inputPanel.add(txtNumeroApartamentos);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Disposición vertical
        JButton btnCrear = new JButton("Crear Torre");
        JButton btnLeer = new JButton("Leer Torres");
        JButton btnEliminar = new JButton("Eliminar Torre");
        JButton btnEditar = new JButton("Editar Torre");

        // Añadir botones al panel
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Añadir paneles al layout principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER); // Añadir el panel de botones al centro

        txtTorres = new JTextArea(10, 30);
        txtTorres.setEditable(false);
        add(new JScrollPane(txtTorres), BorderLayout.SOUTH); // Cambiar a la parte inferior

        // ActionListener para el botón "Crear Torre"
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idTorre = Integer.parseInt(txtId.getText());
                int idProyecto = obtenerIdProyectoSeleccionado();
                int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                int numeroApartamentos = Integer.parseInt(txtNumeroApartamentos.getText());
                torreController.crearTorre(idTorre, idProyecto, numeroTorre, numeroApartamentos);
                JOptionPane.showMessageDialog(null, "Torre creada exitosamente");
                limpiarCampos();
            }
        });

        // ActionListener para el botón "Leer Torres"
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Torre> torres = torreController.obtenerTorres();
                txtTorres.setText("");  // Limpiar el área de texto antes de listar
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
                int idTorre = Integer.parseInt(txtId.getText());
                torreController.eliminarTorre(idTorre);
                JOptionPane.showMessageDialog(null, "Torre eliminada exitosamente");
                limpiarCampos();
            }
        });

        // ActionListener para el botón "Editar Torre"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idTorre = Integer.parseInt(txtId.getText());
                int idProyecto = obtenerIdProyectoSeleccionado();
                int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                int numeroApartamentos = Integer.parseInt(txtNumeroApartamentos.getText());
                torreController.actualizarTorre(idTorre, idProyecto, numeroTorre, numeroApartamentos);
                JOptionPane.showMessageDialog(null, "Torre actualizada exitosamente");
                limpiarCampos();
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

    private void limpiarCampos() {
        txtId.setText(""); // Limpiar el campo de ID
        cmbProyectos.setSelectedIndex(0); // Restablecer la selección de proyecto
        txtNumeroTorre.setText(""); // Limpiar el campo de número de torre
        txtNumeroApartamentos.setText(""); // Limpiar el campo de número de apartamentos
    }
}
