package Vistas;

import controlador.AsesorController;
import modelo.Asesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AsesoresPanel extends JPanel {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextArea txtAsesores;
    private AsesorController asesorController;

    public AsesoresPanel() {
        asesorController = new AsesorController();
        setLayout(new BorderLayout(10, 10));

        // Crear panel de entrada con GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        inputPanel.add(txtCedula);

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        inputPanel.add(txtDireccion);

        inputPanel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        inputPanel.add(txtTelefono);

        inputPanel.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        inputPanel.add(txtCorreo);

        // Panel de botones con disposición vertical
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Disposición vertical
        JButton btnCrear = new JButton("Crear Asesor");
        JButton btnLeer = new JButton("Leer Asesores");
        JButton btnEliminar = new JButton("Eliminar Asesor");
        JButton btnEditar = new JButton("Editar Asesor");

        // Añadir botones al panel
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Añadir paneles al layout principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER); // Añadir el panel de botones al centro

        // Crear área de texto para mostrar los asesores
        txtAsesores = new JTextArea(10, 30);
        txtAsesores.setEditable(false);
        add(new JScrollPane(txtAsesores), BorderLayout.SOUTH); // Cambiar a la parte inferior

        // Acción para el botón "Crear Asesor"
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cedula = Integer.parseInt(txtCedula.getText());
                    String nombre = txtNombre.getText();
                    String direccion = txtDireccion.getText();
                    String telefono = txtTelefono.getText();
                    String correo = txtCorreo.getText();

                    asesorController.crearAsesor(cedula, nombre, direccion, telefono, correo);
                    JOptionPane.showMessageDialog(null, "Asesor creado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para el botón "Leer Asesores"
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Asesor> asesores = asesorController.obtenerAsesores();
                txtAsesores.setText("");  // Limpiar el área de texto antes de listar
                if (asesores.isEmpty()) {
                    txtAsesores.append("No hay asesores registrados.\n");
                } else {
                    for (Asesor asesor : asesores) {
                        txtAsesores.append(String.format("Cédula: %d\n", asesor.getCedula()));
                        txtAsesores.append(String.format("Nombre: %s\n", asesor.getNombre()));
                        txtAsesores.append(String.format("Dirección: %s\n", asesor.getDireccion()));
                        txtAsesores.append(String.format("Teléfono: %s\n", asesor.getTelefono()));
                        txtAsesores.append(String.format("Correo: %s\n", asesor.getCorreo_electronico()));
                        txtAsesores.append("-------------------------------\n");  // Separación entre asesores
                    }
                }
            }
        });

        // Acción para el botón "Eliminar Asesor"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cedula = Integer.parseInt(txtCedula.getText());
                    asesorController.eliminarAsesor(cedula);
                    JOptionPane.showMessageDialog(null, "Asesor eliminado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa una cédula válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para el botón "Editar Asesor"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cedula = Integer.parseInt(txtCedula.getText());
                    String nombre = txtNombre.getText();
                    String direccion = txtDireccion.getText();
                    String telefono = txtTelefono.getText();
                    String correo = txtCorreo.getText();

                    asesorController.actualizarAsesor(cedula, nombre, direccion, telefono, correo);
                    JOptionPane.showMessageDialog(null, "Asesor actualizado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtCedula.setText(""); // Limpiar el campo de cédula
        txtNombre.setText(""); // Limpiar el campo de nombre
        txtDireccion.setText(""); // Limpiar el campo de dirección
        txtTelefono.setText(""); // Limpiar el campo de teléfono
        txtCorreo.setText(""); // Limpiar el campo de correo
    }
}
