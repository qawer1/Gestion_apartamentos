package Vistas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import vistas.ReportesPanel;

public class MainFrameAsesor extends JFrame {
    private JLabel clientesActivosLabel;
    private JLabel ventasRealizadasLabel;

    public MainFrameAsesor() {
        setTitle("Gestión de Apartamentos - Asesor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú de Clientes
        JMenu menuClientes = new JMenu("Clientes");
        menuClientes.setIcon(new ImageIcon(getClass().getResource("/imagenes/clientes.png")));
        JMenuItem itemClientes = new JMenuItem("Gestionar Clientes");
        menuClientes.add(itemClientes);

        // Menú de Ventas
        JMenu menuVentas = new JMenu("Ventas");
        menuVentas.setIcon(new ImageIcon(getClass().getResource("/imagenes/ventas.png")));
        JMenuItem itemVentas = new JMenuItem("Gestionar Ventas");
        menuVentas.add(itemVentas);

        // Menú de Pagos
        JMenu menuPagos = new JMenu("Pagos");
        menuPagos.setIcon(new ImageIcon(getClass().getResource("/imagenes/pagos.png")));
        JMenuItem itemPagos = new JMenuItem("Gestionar Pagos");
        menuPagos.add(itemPagos);

        // Menú de Reportes
        JMenu menuReportes = new JMenu("Reportes");
        menuReportes.setIcon(new ImageIcon(getClass().getResource("/imagenes/reportes.png")));
        JMenuItem itemReportes = new JMenuItem("Generar Reportes");
        menuReportes.add(itemReportes);

        // Menú de Cuenta
        JMenu menuCuenta = new JMenu("Cuenta");
        menuCuenta.setIcon(new ImageIcon(getClass().getResource("/imagenes/cuenta.png")));
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión");
        menuCuenta.add(itemCerrarSesion);

        // Agregar los menús a la barra de menú
        menuBar.add(menuClientes);
        menuBar.add(menuVentas);
        menuBar.add(menuPagos);
        menuBar.add(menuReportes);
        menuBar.add(menuCuenta);
        setJMenuBar(menuBar);

        // Crear el panel de CardLayout para cambiar entre los diferentes paneles
        JPanel mainPanel = new JPanel(new CardLayout());

        // Panel de bienvenida con estadísticas
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

        // Mostrar el panel de bienvenida al iniciar la aplicación
        showPanel(mainPanel, "Bienvenida");
        actualizarEstadisticas(); // Llama a la actualización de estadísticas al iniciar

        // Acciones para cambiar el panel según la selección del menú
        itemClientes.addActionListener(e -> showPanel(mainPanel, "Clientes"));
        itemVentas.addActionListener(e -> showPanel(mainPanel, "Ventas"));
        itemPagos.addActionListener(e -> showPanel(mainPanel, "Pagos"));
        itemReportes.addActionListener(e -> showPanel(mainPanel, "Reportes"));

        // Acción para cerrar sesión
        itemCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar sesión?", "Confirmar Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Cerrar el programa
            }
        });

        add(mainPanel);
    }

    private void showPanel(JPanel mainPanel, String panelName) {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
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
