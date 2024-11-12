package Vistas;

import javax.swing.*;
import java.awt.*;
import vistas.ReportesPanel;

public class MainFrameAsesor extends JFrame {
    public MainFrameAsesor() {
        setTitle("Gestión de Apartamentos - Asesor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear los menús y agregar opciones
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuPagos = new JMenu("Pagos");
        JMenu menuVentas = new JMenu("Ventas");
        JMenu menuReportes = new JMenu("Reportes");  // Menú para reportes
        JMenu menuCuenta = new JMenu("Cuenta");

        // Crear los elementos de menú para cada sección
        JMenuItem itemClientes = new JMenuItem("Gestionar Clientes");
        JMenuItem itemPagos = new JMenuItem("Gestionar Pagos");
        JMenuItem itemVentas = new JMenuItem("Gestionar Ventas");
        JMenuItem itemReportes = new JMenuItem("Generar Reportes"); // Item para reportes
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión");

        // Agregar los elementos de menú a cada menú correspondiente
        menuClientes.add(itemClientes);
        menuPagos.add(itemPagos);
        menuVentas.add(itemVentas);
        menuReportes.add(itemReportes);  // Agregar el menú de reportes
        menuCuenta.add(itemCerrarSesion);

        // Agregar los menús a la barra de menú
        menuBar.add(menuClientes);
        menuBar.add(menuPagos);
        menuBar.add(menuVentas);
        menuBar.add(menuReportes);  // Añadir el menú de reportes a la barra de menú
        menuBar.add(menuCuenta);

        // Asignar la barra de menú a la ventana
        setJMenuBar(menuBar);

        // Crear el panel de CardLayout para cambiar entre los diferentes paneles
        JPanel mainPanel = new JPanel(new CardLayout());

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido a la Gestión de Apartamentos - Asesor", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Agregar el panel de bienvenida y otros paneles CRUD individuales
        mainPanel.add(welcomePanel, "Bienvenida");
        mainPanel.add(new ClientesPanel(), "Clientes");
        mainPanel.add(new PagosPanel(), "Pagos");
        mainPanel.add(new VentasPanel(), "Ventas");

        // Agregar el nuevo panel de reportes
        mainPanel.add(new ReportesPanel(), "Reportes");

        // Mostrar el panel de bienvenida al iniciar la aplicación
        showPanel(mainPanel, "Bienvenida");

        // Acción para cambiar el panel central según la selección del menú
        itemClientes.addActionListener(e -> showPanel(mainPanel, "Clientes"));
        itemPagos.addActionListener(e -> showPanel(mainPanel, "Pagos"));
        itemVentas.addActionListener(e -> showPanel(mainPanel, "Ventas"));

        // Acción para mostrar el panel de reportes
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

    // Método para mostrar el panel seleccionado
    private void showPanel(JPanel mainPanel, String panelName) {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrameAsesor frame = new MainFrameAsesor();
            frame.setVisible(true);
        });
    }
}
