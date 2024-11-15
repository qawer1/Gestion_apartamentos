package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import vistas.ReportesPanel;


public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Gestión de Apartamentos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel principal con CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // ** Crear un panel gris de inicio **
        JPanel inicioPanel = new JPanel();
        inicioPanel.setBackground(new Color(169, 169, 169));  
        mainPanel.add(inicioPanel, "Inicio");  

        // Agregar todos los otros paneles al CardLayout
        mainPanel.add(new ProyectoPanel(), "Proyectos");
        mainPanel.add(new TorresPanel(), "Torres");
        mainPanel.add(new ApartamentoPanel(), "Apartamentos");
        mainPanel.add(new AsesoresPanel(), "Asesores");
        mainPanel.add(new ClientesPanel(), "Clientes");
        mainPanel.add(new PagosPanel(), "Pagos");
        mainPanel.add(new VentasPanel(), "Ventas");
        mainPanel.add(new UsuarioPanel(), "Usuarios");
        mainPanel.add(new ReportesPanel(), "Reportes");

        // Mostrar el panel gris de inicio cuando se ejecute
        cardLayout.show(mainPanel, "Inicio");

        // Crear el menú lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(43, 43, 43));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Crear y agregar botones de menú
        menuPanel.add(createMenuButton("Proyectos", "/imagenes/proyectos.png", "Proyectos"));
        menuPanel.add(createMenuButton("Torres", "/imagenes/torres.png", "Torres"));
        menuPanel.add(createMenuButton("Apartamentos", "/imagenes/apartamento.png", "Apartamentos"));
        menuPanel.add(createMenuButton("Asesores", "/imagenes/asesores.png", "Asesores"));
        menuPanel.add(createMenuButton("Clientes", "/imagenes/clientes.png", "Clientes"));
        menuPanel.add(createMenuButton("Pagos", "/imagenes/pagos.png", "Pagos"));
        menuPanel.add(createMenuButton("Ventas", "/imagenes/ventas.png", "Ventas"));
        menuPanel.add(createMenuButton("Usuarios", "/imagenes/usuarios.png", "Usuarios"));
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
            // Redimensionar el icono a un tamaño uniforme
            Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        }

        // Acción para mostrar el panel correspondiente
        button.addActionListener((ActionEvent e) -> showPanel(panelName));

        return button;
    }

    private void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
