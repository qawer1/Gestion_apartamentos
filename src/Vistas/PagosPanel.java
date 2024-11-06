package Vistas;

import controlador.PagoController;
import controlador.ClienteController;
import controlador.AsesorController;
import modelo.Pago;
import modelo.Cliente;
import modelo.Asesor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        pagoController = new PagoController();
        clienteController = new ClienteController();
        asesorController = new AsesorController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        // Añadir campo ID de pago
        inputPanel.add(new JLabel("ID Pago:"));
        txtIDPago = new JTextField();
        inputPanel.add(txtIDPago);

        inputPanel.add(new JLabel("Valor Pago:"));
        txtValorPago = new JTextField();
        inputPanel.add(txtValorPago);

        inputPanel.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        inputPanel.add(txtFecha);

        // Paneles para seleccionar cliente y asesor mediante JComboBox
        inputPanel.add(new JLabel("Seleccionar Cliente:"));
        cmbClientes = new JComboBox<>();
        cargarClientes();
        inputPanel.add(cmbClientes);

        inputPanel.add(new JLabel("Seleccionar Asesor:"));
        cmbAsesores = new JComboBox<>();
        cargarAsesores();
        inputPanel.add(cmbAsesores);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Pago");
        JButton btnLeer = new JButton("Leer Pagos");
        JButton btnEliminar = new JButton("Eliminar Pago");
        JButton btnEditar = new JButton("Editar Pago");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        txtPagos = new JTextArea(10, 30);
        txtPagos.setEditable(false);
        add(new JScrollPane(txtPagos), BorderLayout.SOUTH);

        // Crear Pago
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();
                
                // Obtener el cliente y asesor seleccionados
                String clienteSeleccionado = (String) cmbClientes.getSelectedItem();
                int cedulaCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[0]); // Extraer la cédula del cliente
                
                String asesorSeleccionado = (String) cmbAsesores.getSelectedItem();
                int cedulaAsesor = Integer.parseInt(asesorSeleccionado.split(" - ")[0]); // Extraer la cédula del asesor

                // Generar ID_Pago manualmente (puedes mejorarlo si es necesario)
                int ID_Pago = (int) (Math.random() * 10000); // Generación simple de ID (puedes mejorarlo)

                pagoController.crearPago(ID_Pago, valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago creado exitosamente");
                limpiarCampos();
            }
        });

        // Leer Pagos
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Pago> pagos = pagoController.obtenerPagos();
                txtPagos.setText("");
                for (Pago pago : pagos) {
                    txtPagos.append("ID Pago: " + pago.getID_Pago() +
                                    ", Valor: " + pago.getValorPago() +
                                    ", Fecha: " + pago.getFecha() +
                                    ", Cédula Cliente: " + pago.getCedula_cliente() +
                                    ", Cédula Asesor: " + pago.getCedula_asesor() + "\n");
                }
            }
        });

        // Eliminar Pago
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPago = Integer.parseInt(txtIDPago.getText()); // Usamos el ID_Pago aquí
                pagoController.eliminarPago(idPago);
                JOptionPane.showMessageDialog(null, "Pago eliminado exitosamente");
                limpiarCampos();
            }
        });

        // Editar Pago
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPago = Integer.parseInt(txtIDPago.getText());  // Obtener ID_Pago de su campo respectivo
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();

                String clienteSeleccionado = (String) cmbClientes.getSelectedItem();
                int cedulaCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);

                String asesorSeleccionado = (String) cmbAsesores.getSelectedItem();
                int cedulaAsesor = Integer.parseInt(asesorSeleccionado.split(" - ")[0]);

                pagoController.actualizarPago(idPago, valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    // Método para cargar los clientes en el JComboBox
    private void cargarClientes() {
        List<Cliente> clientes = clienteController.obtenerClientes();
        for (Cliente cliente : clientes) {
            cmbClientes.addItem(cliente.getCedula() + " - " + cliente.getNombre()); // Formato "Cedula - Nombre"
        }
    }

    // Método para cargar los asesores en el JComboBox
    private void cargarAsesores() {
        List<Asesor> asesores = asesorController.obtenerAsesores();
        for (Asesor asesor : asesores) {
            cmbAsesores.addItem(asesor.getCedula() + " - " + asesor.getNombre()); // Formato "Cedula - Nombre"
        }
    }

    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        txtIDPago.setText("");
        txtValorPago.setText("");
        txtFecha.setText("");
        cmbClientes.setSelectedIndex(0);  // Restablecer el JComboBox de clientes
        cmbAsesores.setSelectedIndex(0);  // Restablecer el JComboBox de asesores
    }
}
