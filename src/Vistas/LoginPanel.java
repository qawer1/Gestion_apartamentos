package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.UsuarioController;  // Asegúrate de tener un controlador que valide las credenciales.

public class LoginPanel extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;
    private UsuarioController usuarioController;  // Controlador para manejar la validación de usuario.

    public LoginPanel() {
        usuarioController = new UsuarioController();

        setTitle("Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 245));
        add(panel);

        // Panel superior con título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel title = new JLabel("Bienvenido", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        panel.add(titlePanel, BorderLayout.NORTH);

        // Panel central para campos de usuario y contraseña
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Usuario:"));
        txtUsername = new JTextField(20);
        inputPanel.add(txtUsername);

        inputPanel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField(20);
        inputPanel.add(txtPassword);

        lblMessage = new JLabel("", JLabel.CENTER);
        lblMessage.setForeground(Color.RED);
        inputPanel.add(lblMessage);

        panel.add(inputPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(btnLogin);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Acción del botón de inicio de sesión
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                if (usuarioController.validarUsuario(username, password)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + username);
                    dispose();  // Cierra la ventana de login.
                    new MainFrame();  // Abre la MainFrame si las credenciales son correctas.
                } else {
                    lblMessage.setText("Credenciales incorrectas. Intente de nuevo.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPanel login = new LoginPanel();
            login.setVisible(true);
        });
    }
}
