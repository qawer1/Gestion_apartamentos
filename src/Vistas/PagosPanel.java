package Vistas;

import controlador.PagoController;
import controlador.ClienteController;
import controlador.AsesorController;
import modelo.Pago;
import modelo.Cliente;
import modelo.Asesor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PagosPanel extends JPanel {
    private JTextField txtIDPago;
    private JTextField txtValorPago;
    private JTextField txtFecha;
    private JComboBox<String> cmbClientes;
    private JComboBox<String> cmbAsesores;
    private JTextArea txtPagos;
    private PagoController pagoController;
    private ClienteController clienteController;
    private AsesorController asesorController;

    public PagosPanel() {
        // Inicializar los controladores
        pagoController = new PagoController();
        clienteController = new ClienteController();
        asesorController = new AsesorController();

        // Configuración del panel principal
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(400, 300)); // Limitar tamaño del panel

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY);

        // Configurar campos de entrada y etiquetas
        JLabel labelIDPago = new JLabel("ID Pago:");
        labelIDPago.setForeground(Color.WHITE);
        inputPanel.add(labelIDPago);
        txtIDPago = new JTextField();
        inputPanel.add(txtIDPago);

        JLabel labelValorPago = new JLabel("Valor Pago:");
        labelValorPago.setForeground(Color.WHITE);
        inputPanel.add(labelValorPago);
        txtValorPago = new JTextField();
        inputPanel.add(txtValorPago);

        JLabel labelFecha = new JLabel("Fecha:");
        labelFecha.setForeground(Color.WHITE);
        inputPanel.add(labelFecha);
        txtFecha = new JTextField();
        inputPanel.add(txtFecha);

        JLabel labelCliente = new JLabel("Seleccionar Cliente:");
        labelCliente.setForeground(Color.WHITE);
        inputPanel.add(labelCliente);
        cmbClientes = new JComboBox<>();
        cargarClientes();
        inputPanel.add(cmbClientes);

        JLabel labelAsesor = new JLabel("Seleccionar Asesor:");
        labelAsesor.setForeground(Color.WHITE);
        inputPanel.add(labelAsesor);
        cmbAsesores = new JComboBox<>();
        cargarAsesores();
        inputPanel.add(cmbAsesores);

        // Panel de botones con FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.GRAY);

        // Crear botones y agregarles configuración de tamaño
        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        ajustarBoton(btnCrear);
        ajustarBoton(btnLeer);
        ajustarBoton(btnEliminar);
        ajustarBoton(btnEditar);

        // Agregar botones al panel de botones
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Agregar paneles al panel principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Configuración del área de texto para mostrar resultados
        txtPagos = new JTextArea(10, 30);
        txtPagos.setEditable(false);
        add(new JScrollPane(txtPagos), BorderLayout.SOUTH);

        // Acción para crear pago
        btnCrear.addActionListener(e -> {
            try {
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();

                String clienteSeleccionado = (String) cmbClientes.getSelectedItem();
                int cedulaCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);

                String asesorSeleccionado = (String) cmbAsesores.getSelectedItem();
                int cedulaAsesor = Integer.parseInt(asesorSeleccionado.split(" - ")[0]);

                int ID_Pago = (int) (Math.random() * 10000);

                pagoController.crearPago(ID_Pago, valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago creado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para leer pagos
btnLeer.addActionListener(e -> {
    List<Pago> pagos = pagoController.obtenerPagos();
    txtPagos.setText("");  // Limpiar el área de texto antes de listar los pagos

    if (pagos.isEmpty()) {
        txtPagos.append("No hay pagos registrados.\n");
    } else {
        for (Pago pago : pagos) {
            // Agregar información de cada pago
            txtPagos.append(String.format("ID Pago: %d\n", pago.getID_Pago()));
            txtPagos.append(String.format("Valor: %.2f\n", pago.getValorPago()));
            txtPagos.append(String.format("Fecha: %s\n", pago.getFecha()));
            txtPagos.append(String.format("Cédula Cliente: %d\n", pago.getCedula_cliente()));
            txtPagos.append(String.format("Cédula Asesor: %d\n", pago.getCedula_asesor()));
            txtPagos.append("----------------------------\n");
        }
    }
});

        // Acción para eliminar pago
        btnEliminar.addActionListener(e -> {
            try {
                int idPago = Integer.parseInt(txtIDPago.getText());
                pagoController.eliminarPago(idPago);
                JOptionPane.showMessageDialog(null, "Pago eliminado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para editar pago
        btnEditar.addActionListener(e -> {
            try {
                int idPago = Integer.parseInt(txtIDPago.getText());
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();

                String clienteSeleccionado = (String) cmbClientes.getSelectedItem();
                int cedulaCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);

                String asesorSeleccionado = (String) cmbAsesores.getSelectedItem();
                int cedulaAsesor = Integer.parseInt(asesorSeleccionado.split(" - ")[0]);

                pagoController.actualizarPago(idPago, valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago actualizado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para cargar la lista de clientes en el combo box
    private void cargarClientes() {
        List<Cliente> clientes = clienteController.obtenerClientes();
        for (Cliente cliente : clientes) {
            cmbClientes.addItem(cliente.getCedula() + " - " + cliente.getNombre());
        }
    }

    // Método para cargar la lista de asesores en el combo box
    private void cargarAsesores() {
        List<Asesor> asesores = asesorController.obtenerAsesores();
        for (Asesor asesor : asesores) {
            cmbAsesores.addItem(asesor.getCedula() + " - " + asesor.getNombre());
        }
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtIDPago.setText("");
        txtValorPago.setText("");
        txtFecha.setText("");
        cmbClientes.setSelectedIndex(0);
        cmbAsesores.setSelectedIndex(0);
    }

    // Método para ajustar el tamaño de los botones
    private void ajustarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setPreferredSize(new Dimension(100, 25)); // Tamaño compacto
        boton.setMinimumSize(new Dimension(100, 25));
        boton.setMaximumSize(new Dimension(100, 25));
    }
}
