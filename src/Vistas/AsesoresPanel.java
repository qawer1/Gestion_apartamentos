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
        setBackground(Color.GRAY); // Agregar color de fondo gris

        // Crear panel de entrada con GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY); // Fondo gris

        // Cédula
        JLabel labelCedula = new JLabel("Cédula:");
        labelCedula.setForeground(Color.WHITE);
        inputPanel.add(labelCedula);
        txtCedula = new JTextField();
        txtCedula.setForeground(Color.BLACK);
        inputPanel.add(txtCedula);

        // Nombre
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setForeground(Color.WHITE);
        inputPanel.add(labelNombre);
        txtNombre = new JTextField();
        txtNombre.setForeground(Color.BLACK);
        inputPanel.add(txtNombre);

        // Dirección
        JLabel labelDireccion = new JLabel("Dirección:");
        labelDireccion.setForeground(Color.WHITE);
        inputPanel.add(labelDireccion);
        txtDireccion = new JTextField();
        txtDireccion.setForeground(Color.BLACK);
        inputPanel.add(txtDireccion);

        // Teléfono
        JLabel labelTelefono = new JLabel("Teléfono:");
        labelTelefono.setForeground(Color.WHITE);
        inputPanel.add(labelTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setForeground(Color.BLACK);
        inputPanel.add(txtTelefono);

        // Correo Electrónico
        JLabel labelCorreo = new JLabel("Correo Electrónico:");
        labelCorreo.setForeground(Color.WHITE);
        inputPanel.add(labelCorreo);
        txtCorreo = new JTextField();
        txtCorreo.setForeground(Color.BLACK);
        inputPanel.add(txtCorreo);

        // Crear panel de botones con FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.GRAY); // Fondo gris

        JButton btnCrear = new JButton("Crear ");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        // Ajuste de tamaño de botones para que sean más pequeños
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

        // Crear área de texto para mostrar los asesores
        txtAsesores = new JTextArea(10, 30);
        txtAsesores.setEditable(false);
        txtAsesores.setForeground(Color.BLACK); // Cambiar color de letras a negro
        add(new JScrollPane(txtAsesores), BorderLayout.SOUTH);

        // Acción para crear asesor
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

        // Acción para leer asesores
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Asesor> asesores = asesorController.obtenerAsesores();
                txtAsesores.setText(""); // Limpiar el área de texto antes de listar
                if (asesores.isEmpty()) {
                    txtAsesores.append("No hay asesores registrados.\n");
                } else {
                    for (Asesor asesor : asesores) {
                        txtAsesores.append("Cédula: " + asesor.getCedula() +
                                           ", Nombre: " + asesor.getNombre() +
                                           ", Dirección: " + asesor.getDireccion() +
                                           ", Teléfono: " + asesor.getTelefono() +
                                           ", Correo: " + asesor.getCorreo_electronico() + "\n");
                        txtAsesores.append("---------------------------------\n"); // Separación entre asesores
                    }
                }
            }
        });

        // Acción para eliminar asesor
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

        // Acción para editar asesor
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
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }

    // Método para ajustar el tamaño de los botones
    private void ajustarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setPreferredSize(new Dimension(100, 25)); // Tamaño compacto similar al primer código
        boton.setMinimumSize(new Dimension(100, 25));
        boton.setMaximumSize(new Dimension(100, 25));
    }
}
