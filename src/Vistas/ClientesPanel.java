package Vistas;

import controlador.ClienteController;
import modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        inputPanel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        inputPanel.add(txtCedula);

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("SISBEN:"));
        txtSisben = new JTextField();
        inputPanel.add(txtSisben);

        inputPanel.add(new JLabel("Subsidio Ministerio:"));
        txtSubsidioMinisterio = new JTextField();
        inputPanel.add(txtSubsidioMinisterio);

        inputPanel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        inputPanel.add(txtDireccion);

        inputPanel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        inputPanel.add(txtTelefono);

        inputPanel.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        inputPanel.add(txtCorreo);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Cliente");
        JButton btnLeer = new JButton("Leer Clientes");
        JButton btnEliminar = new JButton("Eliminar Cliente");
        JButton btnEditar = new JButton("Editar Cliente");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        txtClientes = new JTextArea(10, 30);
        txtClientes.setEditable(false);
        add(new JScrollPane(txtClientes), BorderLayout.SOUTH);

        // Crear Cliente
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Leer Clientes
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> clientes = clienteController.obtenerClientes();
                txtClientes.setText("");
                for (Cliente cliente : clientes) {
                    txtClientes.append("Cédula: " + cliente.getCedula() +
                                       ", Nombre: " + cliente.getNombre() +
                                       ", SISBEN: " + cliente.getSisben() +
                                       ", Subsidio Ministerio: " + cliente.getSubsidioMinisterio() +
                                       ", Dirección: " + cliente.getDireccion() +
                                       ", Teléfono: " + cliente.getTelefono() +
                                       ", Correo: " + cliente.getCorreoElectronico() + "\n");
                }
            }
        });

        // Eliminar Cliente
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cedula = Integer.parseInt(txtCedula.getText());
                clienteController.eliminarCliente(cedula);
                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
                limpiarCampos();
            }
        });

        // Editar Cliente
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtSisben.setText("");
        txtSubsidioMinisterio.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}
