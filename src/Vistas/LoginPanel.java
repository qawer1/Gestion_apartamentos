package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.UsuarioController;

public class LoginPanel extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;
    private UsuarioController usuarioController;

    public LoginPanel() {
        usuarioController = new UsuarioController();

        setTitle("Login - GESTION DE APARTAMENTOS");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel izquierdo con imagen y título
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(new Color(100, 149, 237)); // Azul para el fondo izquierdo
        leftPanel.setPreferredSize(new Dimension(200, 350));

        JLabel lblTitle = new JLabel("LOGIN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        leftPanel.add(lblTitle, BorderLayout.NORTH);

        JLabel buildingIcon = new JLabel(new ImageIcon("C:\\Users\\Sebastian\\OneDrive\\Pictures\\img\\apartamentos.png")); // Icono de edificio
        buildingIcon.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(buildingIcon, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // Panel derecho para el formulario de inicio de sesión
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(245, 245, 245));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Icono de usuario en la parte superior
        JLabel userIcon = new JLabel(new ImageIcon("C:\\Users\\Sebastian\\OneDrive\\Pictures\\img\\perfil.png")); // Icono de usuario

        userIcon.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(userIcon, gbc);

        // Campo de usuario
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel lblUserIcon = new JLabel(new ImageIcon("C:\\Users\\Sebastian\\OneDrive\\Pictures\\img\\usuario.png"));
 // Icono del campo usuario
        inputPanel.add(lblUserIcon, gbc);
        gbc.gridx++;
        txtUsername = new JTextField(15);
        inputPanel.add(txtUsername, gbc);

        // Campo de contraseña
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblPassIcon = new JLabel(new ImageIcon("C:\\Users\\Sebastian\\OneDrive\\Pictures\\img\\candado.png"));



        inputPanel.add(lblPassIcon, gbc);
        gbc.gridx++;
        txtPassword = new JPasswordField(15);
        inputPanel.add(txtPassword, gbc);

        // Mensaje de error
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        lblMessage = new JLabel("", JLabel.CENTER);
        lblMessage.setForeground(Color.RED);
        inputPanel.add(lblMessage, gbc);

        rightPanel.add(inputPanel, BorderLayout.CENTER);

        // Botón de iniciar sesión
        JPanel buttonPanel = new JPanel();
        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(btnLogin);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.CENTER);

        // Acción del botón de inicio de sesión
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (usuarioController.validarUsuario(username, password)) {
                    String rol = usuarioController.obtenerRolUsuario(username);

                    if ("admin".equalsIgnoreCase(rol)) {
                        JOptionPane.showMessageDialog(null, "Bienvenido Administrador " + username);
                        dispose();
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.setVisible(true);
                    } else if ("asesor".equalsIgnoreCase(rol)) {
                        JOptionPane.showMessageDialog(null, "Bienvenido Asesor " + username);
                        dispose();
                        MainFrameAsesor mainFrameAsesor = new MainFrameAsesor();
                        mainFrameAsesor.setVisible(true);
                    } else {
                        lblMessage.setText("Rol no reconocido.");
                    }
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
