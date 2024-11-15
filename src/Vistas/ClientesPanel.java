package Vistas;

import controlador.ClienteController;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientesPanel extends JPanel {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtSisben;
    private JTextField txtSubsidioMinisterio;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextArea txtClientes;
    private ClienteController clienteController;

    public ClientesPanel() {
        clienteController = new ClienteController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.GRAY);

        // Crear panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY);

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

        // SISBEN
        JLabel labelSisben = new JLabel("SISBEN:");
        labelSisben.setForeground(Color.WHITE);
        inputPanel.add(labelSisben);
        txtSisben = new JTextField();
        txtSisben.setForeground(Color.BLACK);
        inputPanel.add(txtSisben);

        // Subsidio Ministerio
        JLabel labelSubsidioMinisterio = new JLabel("Subsidio Ministerio:");
        labelSubsidioMinisterio.setForeground(Color.WHITE);
        inputPanel.add(labelSubsidioMinisterio);
        txtSubsidioMinisterio = new JTextField();
        txtSubsidioMinisterio.setForeground(Color.BLACK);
        inputPanel.add(txtSubsidioMinisterio);

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
        buttonPanel.setBackground(Color.GRAY);

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

        // Crear área de texto para mostrar los clientes
        txtClientes = new JTextArea(10, 30);
        txtClientes.setEditable(false);
        txtClientes.setForeground(Color.BLACK); // Cambiar color de letras a negro
        add(new JScrollPane(txtClientes), BorderLayout.SOUTH);

        // Acción para crear cliente
        btnCrear.addActionListener(e -> {
            try {
                int cedula = Integer.parseInt(txtCedula.getText());
                String nombre = txtNombre.getText();
                String sisben = txtSisben.getText();
                String subsidioMinisterio = txtSubsidioMinisterio.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();

                clienteController.crearCliente(cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correo);
                JOptionPane.showMessageDialog(null, "Cliente creado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para leer clientes
        btnLeer.addActionListener(e -> {
            List<Cliente> clientes = clienteController.obtenerClientes();
            txtClientes.setText("");  // Limpiar el área de texto antes de listar
            if (clientes.isEmpty()) {
                txtClientes.append("No hay clientes registrados.\n");
            } else {
                for (Cliente cliente : clientes) {
                    txtClientes.append(String.format("Cédula: %d\n", cliente.getCedula()));
                    txtClientes.append(String.format("Nombre: %s\n", cliente.getNombre()));
                    txtClientes.append(String.format("SISBEN: %s\n", cliente.getSisben()));
                    txtClientes.append(String.format("Subsidio Ministerio: %s\n", cliente.getSubsidioMinisterio()));
                    txtClientes.append(String.format("Dirección: %s\n", cliente.getDireccion()));
                    txtClientes.append(String.format("Teléfono: %s\n", cliente.getTelefono()));
                    txtClientes.append(String.format("Correo: %s\n", cliente.getCorreoElectronico()));
                    txtClientes.append("----------------------------\n");
                }
            }
        });

        // Acción para eliminar cliente
        btnEliminar.addActionListener(e -> {
            try {
                int cedula = Integer.parseInt(txtCedula.getText());
                clienteController.eliminarCliente(cedula);
                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa una cédula válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para editar cliente
        btnEditar.addActionListener(e -> {
            try {
                int cedula = Integer.parseInt(txtCedula.getText());
                String nombre = txtNombre.getText();
                String sisben = txtSisben.getText();
                String subsidioMinisterio = txtSubsidioMinisterio.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();

                clienteController.actualizarCliente(cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correo);
                JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtSisben.setText("");
        txtSubsidioMinisterio.setText("");
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
