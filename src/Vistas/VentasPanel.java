package Vistas;

import controlador.VentaController;
import modelo.Venta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentasPanel extends JPanel {
    private JTextField txtPrecioTotal;
    private JTextField txtNumeroCuotas;
    private JTextField txtIntereses;
    private JTextField txtIdCliente;
    private JTextField txtIdApartamento;
    private JTextArea txtVentas;
    private VentaController ventaController;

    public VentasPanel() {
        ventaController = new VentaController();
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Precio Total:"));
        txtPrecioTotal = new JTextField();
        inputPanel.add(txtPrecioTotal);

        inputPanel.add(new JLabel("Número de Cuotas:"));
        txtNumeroCuotas = new JTextField();
        inputPanel.add(txtNumeroCuotas);

        inputPanel.add(new JLabel("Intereses:"));
        txtIntereses = new JTextField();
        inputPanel.add(txtIntereses);

        inputPanel.add(new JLabel("ID Cliente:"));
        txtIdCliente = new JTextField();
        inputPanel.add(txtIdCliente);

        inputPanel.add(new JLabel("ID Apartamento:"));
        txtIdApartamento = new JTextField();
        inputPanel.add(txtIdApartamento);

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
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                int idApartamento = Integer.parseInt(txtIdApartamento.getText());

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
                int idVenta = Integer.parseInt(txtPrecioTotal.getText()); // Usando precioTotal como ID para el ejemplo
                ventaController.eliminarVenta(idVenta);
                JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente");
                limpiarCampos();
            }
        });

        // Acción para editar venta
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idVenta = Integer.parseInt(txtPrecioTotal.getText());
                double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
                int numeroCuotas = Integer.parseInt(txtNumeroCuotas.getText());
                double intereses = Double.parseDouble(txtIntereses.getText());
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                int idApartamento = Integer.parseInt(txtIdApartamento.getText());

                ventaController.actualizarVenta(idVenta, precioTotal, numeroCuotas, intereses, idCliente, idApartamento);
                JOptionPane.showMessageDialog(null, "Venta actualizada exitosamente");
                limpiarCampos();
            }
        });
    }

    // Método para limpiar los campos después de una operación
    private void limpiarCampos() {
        txtPrecioTotal.setText("");
        txtNumeroCuotas.setText("");
        txtIntereses.setText("");
        txtIdCliente.setText("");
        txtIdApartamento.setText("");
    }
}
