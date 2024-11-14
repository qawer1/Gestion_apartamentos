package Vistas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import vistas.ReportesPanel;

public class MainFrame extends JFrame {
    private JLabel clientesActivosLabel;
    private JLabel ventasRealizadasLabel;
    private JLabel asesoresActivosLabel;

    public MainFrame() {
        setTitle("Gestión de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear los menús con íconos
        JMenu menuProyectos = new JMenu("Proyectos");
        menuProyectos.setIcon(new ImageIcon(getClass().getResource("/imagenes/proyectos.png")));

        JMenu menuTorres = new JMenu("Torres");
        menuTorres.setIcon(new ImageIcon(getClass().getResource("/imagenes/torres.png")));

        JMenu menuApartamentos = new JMenu("Apartamentos");
        menuApartamentos.setIcon(new ImageIcon(getClass().getResource("/imagenes/apartamento.png")));

        JMenu menuAsesores = new JMenu("Asesores");
        menuAsesores.setIcon(new ImageIcon(getClass().getResource("/imagenes/asesores.png")));

        JMenu menuClientes = new JMenu("Clientes");
        menuClientes.setIcon(new ImageIcon(getClass().getResource("/imagenes/clientes.png")));

        JMenu menuPagos = new JMenu("Pagos");
        menuPagos.setIcon(new ImageIcon(getClass().getResource("/imagenes/pagos.png")));

        JMenu menuVentas = new JMenu("Ventas");
        menuVentas.setIcon(new ImageIcon(getClass().getResource("/imagenes/ventas.png")));

        JMenu menuUsuarios = new JMenu("Usuarios");
        menuUsuarios.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios.png")));

        JMenu menuReportes = new JMenu("Reportes");
        menuReportes.setIcon(new ImageIcon(getClass().getResource("/imagenes/reportes.png")));

        JMenu menuCuenta = new JMenu("Cuenta");
        menuCuenta.setIcon(new ImageIcon(getClass().getResource("/imagenes/cuenta.png")));

        // Crear y agregar elementos a cada menú
        JMenuItem itemProyectos = new JMenuItem("Gestionar Proyectos");
        JMenuItem itemTorres = new JMenuItem("Gestionar Torres");
        JMenuItem itemApartamentos = new JMenuItem("Gestionar Apartamentos");
        JMenuItem itemAsesores = new JMenuItem("Gestionar Asesores");
        JMenuItem itemClientes = new JMenuItem("Gestionar Clientes");
        JMenuItem itemPagos = new JMenuItem("Gestionar Pagos");
        JMenuItem itemVentas = new JMenuItem("Gestionar Ventas");
        JMenuItem itemUsuarios = new JMenuItem("Gestionar Usuarios");
        JMenuItem itemReportes = new JMenuItem("Generar Reportes");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión");

        // Añadir los elementos de menú a sus respectivos menús
        menuProyectos.add(itemProyectos);
        menuTorres.add(itemTorres);
        menuApartamentos.add(itemApartamentos);
        menuAsesores.add(itemAsesores);
        menuClientes.add(itemClientes);
        menuPagos.add(itemPagos);
        menuVentas.add(itemVentas);
        menuUsuarios.add(itemUsuarios);
        menuReportes.add(itemReportes);
        menuCuenta.add(itemCerrarSesion);

        // Añadir los menús a la barra de menú
        menuBar.add(menuProyectos);
        menuBar.add(menuTorres);
        menuBar.add(menuApartamentos);
        menuBar.add(menuAsesores);
        menuBar.add(menuClientes);
        menuBar.add(menuPagos);
        menuBar.add(menuVentas);
        menuBar.add(menuUsuarios);
        menuBar.add(menuReportes);
        menuBar.add(menuCuenta);

        // Asignar la barra de menú a la ventana
        setJMenuBar(menuBar);

        // Crear el panel de CardLayout para cambiar entre los diferentes paneles
        JPanel mainPanel = new JPanel(new CardLayout());

        // Panel de bienvenida con estadísticas
        JPanel welcomePanel = new JPanel(new GridLayout(3, 1, 10, 10));
        clientesActivosLabel = new JLabel("Clientes activos: ");
        ventasRealizadasLabel = new JLabel("Ventas realizadas: ");
        asesoresActivosLabel = new JLabel("Asesores activos: ");

        clientesActivosLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ventasRealizadasLabel.setFont(new Font("Arial", Font.BOLD, 20));
        asesoresActivosLabel.setFont(new Font("Arial", Font.BOLD, 20));

        welcomePanel.add(clientesActivosLabel);
        welcomePanel.add(ventasRealizadasLabel);
        welcomePanel.add(asesoresActivosLabel);

        // Agregar el panel de bienvenida y otros paneles CRUD individuales
        mainPanel.add(welcomePanel, "Bienvenida");
        mainPanel.add(new ProyectoPanel(), "Proyectos");
        mainPanel.add(new ApartamentoPanel(), "Apartamentos");
        mainPanel.add(new TorresPanel(), "Torres");
        mainPanel.add(new AsesoresPanel(), "Asesores");
        mainPanel.add(new ClientesPanel(), "Clientes");
        mainPanel.add(new PagosPanel(), "Pagos");
        mainPanel.add(new VentasPanel(), "Ventas");
        mainPanel.add(new UsuarioPanel(), "Usuarios");
        mainPanel.add(new ReportesPanel(), "Reportes");

        showPanel(mainPanel, "Bienvenida");
        actualizarEstadisticas(); // Llama a la actualización de estadísticas al iniciar

        // Asignar acciones a cada elemento de menú
        itemProyectos.addActionListener(e -> showPanel(mainPanel, "Proyectos"));
        itemTorres.addActionListener(e -> showPanel(mainPanel, "Torres"));
        itemApartamentos.addActionListener(e -> showPanel(mainPanel, "Apartamentos"));
        itemAsesores.addActionListener(e -> showPanel(mainPanel, "Asesores"));
        itemClientes.addActionListener(e -> showPanel(mainPanel, "Clientes"));
        itemPagos.addActionListener(e -> showPanel(mainPanel, "Pagos"));
        itemVentas.addActionListener(e -> showPanel(mainPanel, "Ventas"));
        itemUsuarios.addActionListener(e -> showPanel(mainPanel, "Usuarios"));
        itemReportes.addActionListener(e -> showPanel(mainPanel, "Reportes"));

        // Acción para cerrar sesión y salir del programa
        itemCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar sesión?", "Confirmar Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Cerrar el programa
            }
        });

        // Agregar el panel principal al frame
        add(mainPanel);
    }

    private void showPanel(JPanel mainPanel, String panelName) {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    }

    private void actualizarEstadisticas() {
        int totalClientes = contarRegistros("cliente");
        int totalVentas = contarRegistros("venta");
        int totalAsesores = contarRegistros("asesor");

        clientesActivosLabel.setText("Clientes activos: " + totalClientes);
        ventasRealizadasLabel.setText("Ventas realizadas: " + totalVentas);
        asesoresActivosLabel.setText("Asesores activos: " + totalAsesores);
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
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
