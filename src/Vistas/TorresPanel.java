package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.TorreController;
import modelo.Torre;
import java.util.List;

public class TorresPanel extends JPanel {
    private JTextField txtId; // Campo para ID de la torre
    private JTextField txtIdProyecto; // Campo para ID del proyecto
    private JTextField txtNumeroTorre; // Campo para el número de la torre
    private JTextField txtNumeroApartamentos; // Campo para el número de apartamentos
    private JTextArea txtTorres;
    private TorreController torreController;

    public TorresPanel() {
        torreController = new TorreController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("ID de la Torre:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("ID del Proyecto:"));
        txtIdProyecto = new JTextField();
        inputPanel.add(txtIdProyecto);

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
                int idTorre = Integer.parseInt(txtId.getText()); // Ahora incluye el ID de la Torre
                int idProyecto = Integer.parseInt(txtIdProyecto.getText());
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
                int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                int numeroApartamentos = Integer.parseInt(txtNumeroApartamentos.getText());
                torreController.actualizarTorre(idTorre, idProyecto, numeroTorre, numeroApartamentos);
                JOptionPane.showMessageDialog(null, "Torre actualizada exitosamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtId.setText(""); // Limpiar el campo de ID
        txtIdProyecto.setText(""); // Limpiar el campo de ID de proyecto
        txtNumeroTorre.setText(""); // Limpiar el campo de número de torre
        txtNumeroApartamentos.setText(""); // Limpiar el campo de número de apartamentos
    }
}
