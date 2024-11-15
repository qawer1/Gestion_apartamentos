package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import vistas.ReportesPanel;

public class MainFrameAsesor extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrameAsesor() {
        setTitle("Gestión de Apartamentos - Asesor");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel principal con CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // **Aplicar un color de fondo gris al panel derecho**
        mainPanel.setBackground(new Color(80, 80, 80)); // Ajusta el tono de gris según tus preferencias

        // Agregar todos los paneles al CardLayout
        mainPanel.add(new ClientesPanel(), "Clientes");
        mainPanel.add(new VentasPanel(), "Ventas");
        mainPanel.add(new PagosPanel(), "Pagos");
        mainPanel.add(new ReportesPanel(), "Reportes");

        // Crear el menú lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(43, 43, 43));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Crear y agregar botones de menú
        menuPanel.add(createMenuButton("Clientes", "/imagenes/clientes.png", "Clientes"));
        menuPanel.add(createMenuButton("Ventas", "/imagenes/ventas.png", "Ventas"));
        menuPanel.add(createMenuButton("Pagos", "/imagenes/pagos.png", "Pagos"));
        menuPanel.add(createMenuButton("Reportes", "/imagenes/reportes.png", "Reportes"));

        // Botón de Cerrar Sesión
        JButton cerrarSesionButton = new JButton("Cerrar sesión");
        cerrarSesionButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cerrarSesionButton.setBackground(new Color(43, 43, 43));
        cerrarSesionButton.setForeground(Color.WHITE);
        cerrarSesionButton.setFocusPainted(false);
        cerrarSesionButton.setHorizontalAlignment(SwingConstants.LEFT);
        cerrarSesionButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
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

    private JButton createMenuButton(String text, String iconPath, String panelName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(43, 43, 43));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        if (iconPath != null) {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        }

        button.addActionListener((ActionEvent e) -> showPanel(panelName));
        return button;
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrameAsesor frame = new MainFrameAsesor();
            frame.setVisible(true);
        });
    }
}
