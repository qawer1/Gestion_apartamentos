package Vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Crear los elementos de menú para cada sección
        JMenuItem itemProyectos = new JMenuItem("Gestionar Proyectos");
        JMenuItem itemTorres = new JMenuItem("Gestionar Torres");
        JMenuItem itemApartamentos = new JMenuItem("Gestionar Apartamentos");
        JMenuItem itemAsesores = new JMenuItem("Gestionar Asesores");
        JMenuItem itemClientes = new JMenuItem("Gestionar Clientes");
        JMenuItem itemPagos = new JMenuItem("Gestionar Pagos");
        JMenuItem itemVentas = new JMenuItem("Gestionar Ventas");

        // Agregar los elementos de menú a cada menú correspondiente
        menuProyectos.add(itemProyectos);
        menuTorres.add(itemTorres);
        menuApartamentos.add(itemApartamentos);
        menuAsesores.add(itemAsesores);
        menuClientes.add(itemClientes);
        menuPagos.add(itemPagos);
        menuVentas.add(itemVentas);

        // Agregar los menús a la barra de menú
        menuBar.add(menuProyectos);
        menuBar.add(menuTorres);
        menuBar.add(menuApartamentos);
        menuBar.add(menuAsesores);
        menuBar.add(menuClientes);
        menuBar.add(menuPagos);
        menuBar.add(menuVentas);

        // Asignar la barra de menú a la ventana
        setJMenuBar(menuBar);

        // Panel central donde se mostrarán las opciones CRUD o la pantalla de bienvenida
        JPanel mainPanel = new JPanel(new CardLayout());

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido a la Gestión de Apartamentos", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Agregar el panel de bienvenida y otros paneles CRUD individuales
        mainPanel.add(welcomePanel, "Bienvenida");
        mainPanel.add(new ApartamentoPanel(), "Apartamentos");
        mainPanel.add(new JLabel("Gestión de Proyectos - CRUD aquí"), "Proyectos");
        mainPanel.add(new JLabel("Gestión de Torres - CRUD aquí"), "Torres");
        mainPanel.add(new JLabel("Gestión de Asesores - CRUD aquí"), "Asesores");
        mainPanel.add(new JLabel("Gestión de Clientes - CRUD aquí"), "Clientes");
        mainPanel.add(new JLabel("Gestión de Pagos - CRUD aquí"), "Pagos");
        mainPanel.add(new JLabel("Gestión de Ventas - CRUD aquí"), "Ventas");

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

// Panel de ejemplo para la gestión de Apartamentos (puedes modificar este panel)
class ApartamentoPanel extends JPanel {
    public ApartamentoPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Gestión de Apartamentos - CRUD aquí", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
