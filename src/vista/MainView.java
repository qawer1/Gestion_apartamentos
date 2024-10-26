package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainView() {
        // Configurar el JFrame principal
        setTitle("Gestión de Proyectos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear los menús principales
        JMenu proyectoMenu = new JMenu("Proyecto");
        JMenu asesorMenu = new JMenu("Asesor");
        JMenu clienteMenu = new JMenu("Cliente");
        JMenu pagoMenu = new JMenu("Pago");

        // Crear los elementos del menú
        JMenuItem añadirProyecto = new JMenuItem("Añadir Proyecto");
        JMenuItem editarProyecto = new JMenuItem("Editar Proyecto");
        proyectoMenu.add(añadirProyecto);
        proyectoMenu.add(editarProyecto);

        JMenuItem añadirAsesor = new JMenuItem("Añadir Asesor");
        JMenuItem editarAsesor = new JMenuItem("Editar Asesor");
        asesorMenu.add(añadirAsesor);
        asesorMenu.add(editarAsesor);

        JMenuItem añadirCliente = new JMenuItem("Añadir Cliente");
        JMenuItem editarCliente = new JMenuItem("Editar Cliente");
        clienteMenu.add(añadirCliente);
        clienteMenu.add(editarCliente);

        JMenuItem añadirPago = new JMenuItem("Añadir Pago");
        JMenuItem editarPago = new JMenuItem("Editar Pago");
        pagoMenu.add(añadirPago);
        pagoMenu.add(editarPago);

        // Añadir los menús a la barra de menú
        menuBar.add(proyectoMenu);
        menuBar.add(asesorMenu);
        menuBar.add(clienteMenu);
        menuBar.add(pagoMenu);

        // Configurar el menú
        setJMenuBar(menuBar);

        // Crear el panel principal con CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los paneles para cada opción
        JPanel proyectoPanel = new JPanel();
        proyectoPanel.add(new JLabel("Gestión de Proyectos"));

        JPanel asesorPanel = new JPanel();
        asesorPanel.add(new JLabel("Gestión de Asesores"));

        JPanel clientePanel = new JPanel();
        clientePanel.add(new JLabel("Gestión de Clientes"));

        JPanel pagoPanel = new JPanel();
        pagoPanel.add(new JLabel("Gestión de Pagos"));

        // Añadir los paneles al mainPanel con un identificador
        mainPanel.add(proyectoPanel, "Proyecto");
        mainPanel.add(asesorPanel, "Asesor");
        mainPanel.add(clientePanel, "Cliente");
        mainPanel.add(pagoPanel, "Pago");

        // Configurar los eventos del menú
        añadirProyecto.addActionListener(e -> cardLayout.show(mainPanel, "Proyecto"));
        añadirAsesor.addActionListener(e -> cardLayout.show(mainPanel, "Asesor"));
        añadirCliente.addActionListener(e -> cardLayout.show(mainPanel, "Cliente"));
        añadirPago.addActionListener(e -> cardLayout.show(mainPanel, "Pago"));

        // Agregar el mainPanel al JFrame
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
