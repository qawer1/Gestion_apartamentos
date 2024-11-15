package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ApartamentoController;
import modelo.Apartamento;
import java.util.List;

public class ApartamentoPanel extends JPanel {
    private JTextField txtID;
    private JComboBox<Integer> cbIDTorre;
    private JTextField txtNumeroApartamento;
    private JTextField txtValorApartamento;
    private JTextField txtTipoUnidad;
    private JTextField txtArea;
    private JTextField txtMatricula;
    private JTextField txtEstadoVenta;
    private JTextArea txtApartamentos;
    private ApartamentoController apartamentoController;

    public ApartamentoPanel() {
        apartamentoController = new ApartamentoController();
        setLayout(new BorderLayout(10, 10));

        // Establecer color de fondo gris para el panel principal
        setBackground(Color.GRAY);

        // Crear el panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY);  // Fondo gris para el panel de entrada
        inputPanel.add(crearEtiqueta("ID del Apartamento:"));
        txtID = new JTextField();
        inputPanel.add(txtID);

        inputPanel.add(crearEtiqueta("ID Torre:"));
        cbIDTorre = new JComboBox<>();
        cargarTorres();
        inputPanel.add(cbIDTorre);

        inputPanel.add(crearEtiqueta("Número del Apartamento:"));
        txtNumeroApartamento = new JTextField();
        inputPanel.add(txtNumeroApartamento);

        inputPanel.add(crearEtiqueta("Valor del Apartamento:"));
        txtValorApartamento = new JTextField();
        inputPanel.add(txtValorApartamento);

        inputPanel.add(crearEtiqueta("Tipo de Unidad:"));
        txtTipoUnidad = new JTextField();
        inputPanel.add(txtTipoUnidad);

        inputPanel.add(crearEtiqueta("Área:"));
        txtArea = new JTextField();
        inputPanel.add(txtArea);

        inputPanel.add(crearEtiqueta("Matrícula:"));
        txtMatricula = new JTextField();
        inputPanel.add(txtMatricula);

        inputPanel.add(crearEtiqueta("Estado de Venta:"));
        txtEstadoVenta = new JTextField();
        inputPanel.add(txtEstadoVenta);

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(Color.GRAY);  // Fondo gris para el panel de botones
        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        ajustarBoton(btnCrear);
        ajustarBoton(btnLeer);
        ajustarBoton(btnEliminar);
        ajustarBoton(btnEditar);

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        txtApartamentos = new JTextArea(10, 30);
        txtApartamentos.setEditable(false);
        txtApartamentos.setBackground(Color.LIGHT_GRAY);  // Fondo gris claro para el área de texto
        add(new JScrollPane(txtApartamentos), BorderLayout.SOUTH);

        // Agregar acciones a los botones
        btnCrear.addActionListener(e -> {
            try {
                int idApartamento = Integer.parseInt(txtID.getText());
                int idTorre = (Integer) cbIDTorre.getSelectedItem();
                int numero = Integer.parseInt(txtNumeroApartamento.getText());
                double valor = Double.parseDouble(txtValorApartamento.getText());
                String tipoUnidad = txtTipoUnidad.getText();
                String area = txtArea.getText();
                String matricula = txtMatricula();
                String estadoVenta = txtEstadoVenta.getText();

                apartamentoController.crearApartamento(idApartamento, idTorre, numero, valor, tipoUnidad, area, matricula, estadoVenta);
                JOptionPane.showMessageDialog(null, "Apartamento creado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLeer.addActionListener(e -> {
            List<Apartamento> apartamentos = apartamentoController.obtenerApartamentos();
            txtApartamentos.setText("");
            for (Apartamento apartamento : apartamentos) {
                txtApartamentos.append("ID: " + apartamento.getID_apartamento() +
                        ", Torre ID: " + apartamento.getID_torre() +
                        ", Número: " + apartamento.getNumero_apartamento() +
                        ", Valor: " + apartamento.getValorApartamento() +
                        ", Tipo: " + apartamento.getTipoUnidad() +
                        ", Área: " + apartamento.getArea() +
                        ", Matrícula: " + apartamento.getMatricula() +
                        ", Estado de Venta: " + apartamento.getEstadoVenta() + "\n");
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int idApartamento = Integer.parseInt(txtID.getText());
                apartamentoController.eliminarApartamento(idApartamento);
                JOptionPane.showMessageDialog(null, "Apartamento eliminado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEditar.addActionListener(e -> {
            try {
                int idApartamento = Integer.parseInt(txtID.getText());
                int idTorre = (Integer) cbIDTorre.getSelectedItem();
                int numero = Integer.parseInt(txtNumeroApartamento.getText());
                double valor = Double.parseDouble(txtValorApartamento.getText());
                String tipoUnidad = txtTipoUnidad.getText();
                String area = txtArea.getText();
                String matricula = txtMatricula.getText();
                String estadoVenta = txtEstadoVenta.getText();

                apartamentoController.actualizarApartamento(idApartamento, idTorre, numero, valor, tipoUnidad, area, matricula, estadoVenta);
                JOptionPane.showMessageDialog(null, "Apartamento actualizado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto, SwingConstants.RIGHT);
        etiqueta.setForeground(Color.WHITE); // Texto blanco para mejor contraste
        return etiqueta;
    }

    private void cargarTorres() {
        List<Integer> idsTorres = apartamentoController.obtenerIdsTorres();
        for (Integer id : idsTorres) {
            cbIDTorre.addItem(id);
        }
    }

    private void limpiarCampos() {
        txtID.setText("");
        cbIDTorre.setSelectedIndex(0);
        txtNumeroApartamento.setText("");
        txtValorApartamento.setText("");
        txtTipoUnidad.setText("");
        txtArea.setText("");
        txtMatricula.setText("");
        txtEstadoVenta.setText("");
    }

    private void ajustarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setPreferredSize(new Dimension(100, 30));
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setVerticalAlignment(SwingConstants.CENTER);
    }
}
