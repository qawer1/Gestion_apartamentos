package Vistas;

import controlador.PagoController;
import modelo.Pago;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PagosPanel extends JPanel {
    private JTextField txtValorPago;
    private JTextField txtFecha;
    private JTextField txtCedulaCliente;
    private JTextField txtCedulaAsesor;
    private JTextArea txtPagos;
    private PagoController pagoController;

    public PagosPanel() {
        pagoController = new PagoController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        inputPanel.add(new JLabel("Valor Pago:"));
        txtValorPago = new JTextField();
        inputPanel.add(txtValorPago);

        inputPanel.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        inputPanel.add(txtFecha);

        inputPanel.add(new JLabel("Cédula Cliente:"));
        txtCedulaCliente = new JTextField();
        inputPanel.add(txtCedulaCliente);

        inputPanel.add(new JLabel("Cédula Asesor:"));
        txtCedulaAsesor = new JTextField();
        inputPanel.add(txtCedulaAsesor);

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

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();
                int cedulaCliente = Integer.parseInt(txtCedulaCliente.getText());
                int cedulaAsesor = Integer.parseInt(txtCedulaAsesor.getText());

                pagoController.crearPago(valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago creado exitosamente");
                limpiarCampos();
            }
        });

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

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPago = Integer.parseInt(txtValorPago.getText()); // Usando valorPago como ID para el ejemplo
                pagoController.eliminarPago(idPago);
                JOptionPane.showMessageDialog(null, "Pago eliminado exitosamente");
                limpiarCampos();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idPago = Integer.parseInt(txtValorPago.getText());
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();
                int cedulaCliente = Integer.parseInt(txtCedulaCliente.getText());
                int cedulaAsesor = Integer.parseInt(txtCedulaAsesor.getText());

                pagoController.actualizarPago(idPago, valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtValorPago.setText("");
        txtFecha.setText("");
        txtCedulaCliente.setText("");
        txtCedulaAsesor.setText("");
    }
}
