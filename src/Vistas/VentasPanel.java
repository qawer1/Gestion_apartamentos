package Vistas;

import controlador.VentaController;
import controlador.ClienteController;
import controlador.ApartamentoController;
import modelo.Venta;
import modelo.Cliente;
import modelo.Apartamento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentasPanel extends JPanel {
    private JTextField txtIdVenta; // Campo para el ID de la venta
    private JTextField txtPrecioTotal;
    private JTextField txtNumeroCuotas;
    private JTextField txtIntereses;
    private JComboBox<String> cmbClientes;
    private JComboBox<String> cmbApartamentos;
    private JTextArea txtVentas;
    private VentaController ventaController;
    private ClienteController clienteController;
    private ApartamentoController apartamentoController;

    public VentasPanel() {
        ventaController = new VentaController();
        clienteController = new ClienteController();
        apartamentoController = new ApartamentoController();
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        inputPanel.add(new JLabel("ID Venta:"));
        txtIdVenta = new JTextField();
        inputPanel.add(txtIdVenta);

        inputPanel.add(new JLabel("Precio Total:"));
        txtPrecioTotal = new JTextField();
        inputPanel.add(txtPrecioTotal);

        inputPanel.add(new JLabel("Número de Cuotas:"));
        txtNumeroCuotas = new JTextField();
        inputPanel.add(txtNumeroCuotas);

        inputPanel.add(new JLabel("Intereses:"));
        txtIntereses = new JTextField();
        inputPanel.add(txtIntereses);

        // Panel para seleccionar el cliente y apartamento mediante JComboBox
        inputPanel.add(new JLabel("Seleccionar Cliente:"));
        cmbClientes = new JComboBox<>();
        cargarClientes();
        inputPanel.add(cmbClientes);

        inputPanel.add(new JLabel("Seleccionar Apartamento:"));
        cmbApartamentos = new JComboBox<>();
        cargarApartamentos();
        inputPanel.add(cmbApartamentos);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Venta");
        JButton btnLeer = new JButton("Leer Ventas");
        JButton btnEliminar = new JButton("Eliminar Venta");
        JButton btnEditar = new JButton("Editar Venta");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Agregar paneles al layout
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Área de texto para mostrar ventas
        txtVentas = new JTextArea(10, 30);
        txtVentas.setEditable(false);
        add(new JScrollPane(txtVentas), BorderLayout.SOUTH);

        // Acción para crear venta
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
                int numeroCuotas = Integer.parseInt(txtNumeroCuotas.getText());
                double intereses = Double.parseDouble(txtIntereses.getText());
                
                // Obtener el cliente y apartamento seleccionados
                String clienteSeleccionado = (String) cmbClientes.getSelectedItem();
                int idCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[0]); // Asumimos que el formato es "ID - Nombre"
                
                String apartamentoSeleccionado = (String) cmbApartamentos.getSelectedItem();
                int idApartamento = Integer.parseInt(apartamentoSeleccionado.split(" - ")[0]);

                ventaController.crearVenta(precioTotal, numeroCuotas, intereses, idCliente, idApartamento);
                JOptionPane.showMessageDialog(null, "Venta creada exitosamente");
                limpiarCampos();
            }
        });

        // Acción para leer todas las ventas
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Venta> ventas = ventaController.obtenerVentas();
                txtVentas.setText("");
                for (Venta venta : ventas) {
                    txtVentas.append("ID Venta: " + venta.getIdVenta() +
                            ", Precio Total: " + venta.getPrecioTotal() +
                            ", Número de Cuotas: " + venta.getNumeroCuotas() +
                            ", Intereses: " + venta.getIntereses() +
                            ", ID Cliente: " + venta.getIdCliente() +
                            ", ID Apartamento: " + venta.getIdApartamento() + "\n");
                }
            }
        });

        // Acción para eliminar venta
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idVenta = Integer.parseInt(txtIdVenta.getText()); // Ahora usamos el ID de la venta
                ventaController.eliminarVenta(idVenta);
                JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente");
                limpiarCampos();
            }
        });

        // Acción para editar venta
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idVenta = Integer.parseInt(txtIdVenta.getText()); // Ahora usamos el ID de la venta
                double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
                int numeroCuotas = Integer.parseInt(txtNumeroCuotas.getText());
                double intereses = Double.parseDouble(txtIntereses.getText());

                String clienteSeleccionado = (String) cmbClientes.getSelectedItem();
                int idCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);

                String apartamentoSeleccionado = (String) cmbApartamentos.getSelectedItem();
                int idApartamento = Integer.parseInt(apartamentoSeleccionado.split(" - ")[0]);

                ventaController.actualizarVenta(idVenta, precioTotal, numeroCuotas, intereses, idCliente, idApartamento);
                JOptionPane.showMessageDialog(null, "Venta actualizada exitosamente");
                limpiarCampos();
            }
        });
    }

    // Método para cargar los clientes en el JComboBox
    private void cargarClientes() {
        List<Cliente> clientes = clienteController.obtenerClientes();
        for (Cliente cliente : clientes) {
            cmbClientes.addItem(cliente.getCedula() + " - " + cliente.getNombre());
        }
    }

    // Método para cargar los apartamentos en el JComboBox
    private void cargarApartamentos() {
        List<Apartamento> apartamentos = apartamentoController.obtenerApartamentos();
        for (Apartamento apartamento : apartamentos) {
            cmbApartamentos.addItem(apartamento.getID_apartamento() + " - " + apartamento.getNumero_apartamento());
        }
    }

    // Método para limpiar los campos después de una operación
    private void limpiarCampos() {
        txtIdVenta.setText("");  // Limpiar el campo de ID Venta
        txtPrecioTotal.setText("");
        txtNumeroCuotas.setText("");
        txtIntereses.setText("");
        cmbClientes.setSelectedIndex(0);
        cmbApartamentos.setSelectedIndex(0);
    }
}