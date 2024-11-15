package Vistas;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class MainFrameAsesor extends JFrame {
    private JPanel mainPanel;
    private JLabel clientesActivosLabel;
    private JLabel ventasRealizadasLabel;
    private CardLayout cardLayout;

    public MainFrameAsesor() {
        // Aplicar FlatLaf para un estilo moderno
        FlatDarkLaf.setup();

        setTitle("Gestión de Apartamentos - Asesor");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        // Crear el panel principal con CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        clientesActivosLabel = new JLabel("Clientes activos: ");
        ventasRealizadasLabel = new JLabel("Ventas realizadas: ");

        clientesActivosLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ventasRealizadasLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomePanel.add(clientesActivosLabel);
        welcomePanel.add(ventasRealizadasLabel);

        mainPanel.add(welcomePanel, "Bienvenida");
        mainPanel.add(new ClientesPanel(), "Clientes");
        mainPanel.add(new VentasPanel(), "Ventas");
        mainPanel.add(new PagosPanel(), "Pagos");
        mainPanel.add(new ReportesPanel(), "Reportes");

        actualizarEstadisticas();
        showPanel("Bienvenida");

        // Crear el menú lateral estilizado
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(60, 63, 65));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Crear botones de menú con FlatLaf
        menuPanel.add(createMenuButton("Clientes", "Clientes"));
        menuPanel.add(createMenuButton("Ventas", "Ventas"));
        menuPanel.add(createMenuButton("Pagos", "Pagos"));
        menuPanel.add(createMenuButton("Reportes", "Reportes"));

        // Botón de Cerrar Sesión con estilo
        JButton cerrarSesionButton = new JButton("Cerrar sesión");
        cerrarSesionButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cerrarSesionButton.setBackground(new Color(220, 53, 69));
        cerrarSesionButton.setForeground(Color.WHITE);
        cerrarSesionButton.setFocusPainted(false);
        cerrarSesionButton.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 10));
        cerrarSesionButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar sesión?", "Confirmar Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(cerrarSesionButton);

        // Añadir el menú lateral y el panel principal al frame
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text, String panelName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(69, 73, 74));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 10));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener((ActionEvent e) -> showPanel(panelName));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(96, 125, 139)); // Hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(69, 73, 74)); // Original color
            }
        });
        return button;
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private void actualizarEstadisticas() {
        int totalClientes = contarRegistros("cliente");
        int totalVentas = contarRegistros("venta");
        clientesActivosLabel.setText("Clientes activos: " + totalClientes);
        ventasRealizadasLabel.setText("Ventas realizadas: " + totalVentas);
    }

    private int contarRegistros(String tabla) {
        int total = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "Case18283022");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tabla)) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrameAsesor frame = new MainFrameAsesor();
            frame.setVisible(true);
        });
    }
}
