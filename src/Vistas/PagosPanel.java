package Vistas;

import controlador.PagoController;
import modelo.Pago;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PagosPanel extends JPanel {
    private JTextField txtIDPago;
    private JTextField txtValorPago;
    private JTextField txtFecha;
    private JTextField txtCedulaCliente;
    private JTextField txtCedulaAsesor;
    private JTextArea txtPagos;
    private PagoController pagoController;

    public PagosPanel() {
        pagoController = new PagoController();
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

        // Crear Pago
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorPago = Double.parseDouble(txtValorPago.getText());
                String fecha = txtFecha.getText();
                int cedulaCliente = Integer.parseInt(txtCedulaCliente.getText());
                int cedulaAsesor = Integer.parseInt(txtCedulaAsesor.getText());

                // Generar ID_Pago manualmente (puedes ajustarlo si es necesario)
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
                int cedulaCliente = Integer.parseInt(txtCedulaCliente.getText());
                int cedulaAsesor = Integer.parseInt(txtCedulaAsesor.getText());

                pagoController.actualizarPago(idPago, valorPago, fecha, cedulaCliente, cedulaAsesor);
                JOptionPane.showMessageDialog(null, "Pago actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        txtIDPago.setText("");
        txtValorPago.setText("");
        txtFecha.setText("");
        txtCedulaCliente.setText("");
        txtCedulaAsesor.setText("");
    }
}