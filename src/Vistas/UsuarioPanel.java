package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.UsuarioController;
import modelo.Usuario;
import java.util.List;

public class UsuarioPanel extends JPanel {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtContrasena;
    private JTextField txtRol;
    private JTextArea txtAreaUsuarios; // Area de texto para mostrar los usuarios
    private UsuarioController usuarioController;

    public UsuarioPanel() {
        usuarioController = new UsuarioController();
        setLayout(new BorderLayout(10, 10));

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

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

        // Crear área de texto para mostrar los usuarios
        txtAreaUsuarios = new JTextArea(10, 30);  // Tamaño del área de texto
        txtAreaUsuarios.setEditable(false); // No editable
        JScrollPane scrollPane = new JScrollPane(txtAreaUsuarios);
        add(scrollPane, BorderLayout.SOUTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Usuario");
        JButton btnActualizar = new JButton("Actualizar Usuario");
        JButton btnLeer = new JButton("Leer Usuarios");
        JButton btnEliminar = new JButton("Eliminar Usuario");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);

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

        // Acción para leer usuarios
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Usuario> usuarios = usuarioController.obtenerUsuarios();
                txtAreaUsuarios.setText(""); // Limpiar área de texto
                if (usuarios.isEmpty()) {
                    txtAreaUsuarios.append("No hay usuarios registrados.");
                } else {
                    for (Usuario usuario : usuarios) {
                        txtAreaUsuarios.append("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Rol: " + usuario.getRol() + "\n");
                    }
                }
            }
        });

        // Acción para eliminar usuario
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    usuarioController.eliminarUsuario(id);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID válido.");
                }
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
