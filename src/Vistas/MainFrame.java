package Vistas;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Gestión de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear los menús y agregar opciones
        JMenu menuProyectos = new JMenu("Proyectos");
        JMenu menuTorres = new JMenu("Torres");
        JMenu menuApartamentos = new JMenu("Apartamentos");
        JMenu menuAsesores = new JMenu("Asesores");
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuPagos = new JMenu("Pagos");
        JMenu menuVentas = new JMenu("Ventas");
        JMenu menuUsuarios = new JMenu("Usuarios"); // Nuevo menú Usuarios

        // Crear los elementos de menú para cada sección
        JMenuItem itemProyectos = new JMenuItem("Gestionar Proyectos");
        JMenuItem itemTorres = new JMenuItem("Gestionar Torres");
        JMenuItem itemApartamentos = new JMenuItem("Gestionar Apartamentos");
        JMenuItem itemAsesores = new JMenuItem("Gestionar Asesores");
        JMenuItem itemClientes = new JMenuItem("Gestionar Clientes");
        JMenuItem itemPagos = new JMenuItem("Gestionar Pagos");
        JMenuItem itemVentas = new JMenuItem("Gestionar Ventas");
        JMenuItem itemUsuarios = new JMenuItem("Gestionar Usuarios"); // Nuevo item para Usuarios

        // Agregar los elementos de menú a cada menú correspondiente
        menuProyectos.add(itemProyectos);
        menuTorres.add(itemTorres);
        menuApartamentos.add(itemApartamentos);
        menuAsesores.add(itemAsesores);
        menuClientes.add(itemClientes);
        menuPagos.add(itemPagos);
        menuVentas.add(itemVentas);
        menuUsuarios.add(itemUsuarios); // Agregar itemUsuarios al menú Usuarios

        // Agregar los menús a la barra de menú
        menuBar.add(menuProyectos);
        menuBar.add(menuTorres);
        menuBar.add(menuApartamentos);
        menuBar.add(menuAsesores);
        menuBar.add(menuClientes);
        menuBar.add(menuPagos);
        menuBar.add(menuVentas);
        menuBar.add(menuUsuarios); // Agregar menú Usuarios

        // Asignar la barra de menú a la ventana
        setJMenuBar(menuBar);

        // Crear el panel de CardLayout para cambiar entre los diferentes paneles
        JPanel mainPanel = new JPanel(new CardLayout());

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido a la Gestión de Apartamentos", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Agregar el panel de bienvenida y otros paneles CRUD individuales
        mainPanel.add(welcomePanel, "Bienvenida");
        mainPanel.add(new ProyectoPanel(), "Proyectos");
        mainPanel.add(new ApartamentoPanel(), "Apartamentos");
        mainPanel.add(new TorresPanel(), "Torres");
        mainPanel.add(new AsesoresPanel(), "Asesores");
        mainPanel.add(new ClientesPanel(), "Clientes");
        mainPanel.add(new PagosPanel(), "Pagos");
        mainPanel.add(new VentasPanel(), "Ventas");  
        mainPanel.add(new UsuarioPanel(), "Usuarios"); // Agregar el panel de Usuarios

        // Mostrar el panel de bienvenida al iniciar la aplicación
        showPanel(mainPanel, "Bienvenida");

        // Acción para cambiar el panel central según la selección del menú
        itemProyectos.addActionListener(e -> showPanel(mainPanel, "Proyectos"));
        itemTorres.addActionListener(e -> showPanel(mainPanel, "Torres"));
        itemApartamentos.addActionListener(e -> showPanel(mainPanel, "Apartamentos"));
        itemAsesores.addActionListener(e -> showPanel(mainPanel, "Asesores"));
        itemClientes.addActionListener(e -> showPanel(mainPanel, "Clientes"));
        itemPagos.addActionListener(e -> showPanel(mainPanel, "Pagos"));
        itemVentas.addActionListener(e -> showPanel(mainPanel, "Ventas"));
        itemUsuarios.addActionListener(e -> showPanel(mainPanel, "Usuarios")); // Acción para mostrar Usuarios

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
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
