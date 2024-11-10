package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.UsuarioController;

public class UsuarioPanel extends JPanel {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtContrasena;
    private JTextField txtRol;
    private UsuarioController usuarioController;

    public UsuarioPanel() {
        usuarioController = new UsuarioController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("ID del Usuario:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField();
        inputPanel.add(txtContrasena);

        inputPanel.add(new JLabel("Rol:"));
        txtRol = new JTextField();
        inputPanel.add(txtRol);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Usuario");
        JButton btnActualizar = new JButton("Actualizar Usuario");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnActualizar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Acción para crear usuario
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String rol = txtRol.getText();

                usuarioController.crearUsuario(id, nombre, contrasena, rol);
                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                limpiarCampos();
            }
        });

        // Acción para actualizar usuario
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String contrasena = txtContrasena.getText();
                String rol = txtRol.getText();

                usuarioController.actualizarUsuario(id, nombre, contrasena, rol);
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtContrasena.setText("");
        txtRol.setText("");
    }
}
