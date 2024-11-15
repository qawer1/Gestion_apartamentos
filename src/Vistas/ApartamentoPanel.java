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

        // Crear el panel de entrada
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;  // Centrar los componentes

        inputPanel.add(new JLabel("ID del Apartamento:"), getGBC(0, 0));
        txtID = new JTextField(15);
        inputPanel.add(txtID, getGBC(1, 0));

        inputPanel.add(new JLabel("ID Torre:"), getGBC(0, 1));
        cbIDTorre = new JComboBox<>();
        cargarTorres();
        inputPanel.add(cbIDTorre, getGBC(1, 1));

        inputPanel.add(new JLabel("Número del Apartamento:"), getGBC(0, 2));
        txtNumeroApartamento = new JTextField(15);
        inputPanel.add(txtNumeroApartamento, getGBC(1, 2));

        inputPanel.add(new JLabel("Valor del Apartamento:"), getGBC(0, 3));
        txtValorApartamento = new JTextField(15);
        inputPanel.add(txtValorApartamento, getGBC(1, 3));

        inputPanel.add(new JLabel("Tipo de Unidad:"), getGBC(0, 4));
        txtTipoUnidad = new JTextField(15);
        inputPanel.add(txtTipoUnidad, getGBC(1, 4));

        inputPanel.add(new JLabel("Área:"), getGBC(0, 5));
        txtArea = new JTextField(15);
        inputPanel.add(txtArea, getGBC(1, 5));

        inputPanel.add(new JLabel("Matrícula:"), getGBC(0, 6));
        txtMatricula = new JTextField(15);
        inputPanel.add(txtMatricula, getGBC(1, 6));

        inputPanel.add(new JLabel("Estado de Venta:"), getGBC(0, 7));
        txtEstadoVenta = new JTextField(15);
        inputPanel.add(txtEstadoVenta, getGBC(1, 7));

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Ajustar el layout a 2x2 para los botones
        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        // Ajustar tamaño y diseño de los botones
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
        add(new JScrollPane(txtApartamentos), BorderLayout.SOUTH);

        // Acción para crear apartamento
        btnCrear.addActionListener(e -> {
            try {
                int idApartamento = Integer.parseInt(txtID.getText());
                int idTorre = (Integer) cbIDTorre.getSelectedItem();
                int numero = Integer.parseInt(txtNumeroApartamento.getText());
                double valor = Double.parseDouble(txtValorApartamento.getText());
                String tipoUnidad = txtTipoUnidad.getText();
                String area = txtArea.getText();
                String matricula = txtMatricula.getText();
                String estadoVenta = txtEstadoVenta.getText();

                apartamentoController.crearApartamento(idApartamento, idTorre, numero, valor, tipoUnidad, area, matricula, estadoVenta);
                JOptionPane.showMessageDialog(null, "Apartamento creado exitosamente");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para leer apartamentos
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

        // Acción para eliminar apartamento
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

        // Acción para editar apartamento
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
        boton.setPreferredSize(new Dimension(100, 30)); // Ajustar tamaño para que sean más pequeños
        boton.setHorizontalAlignment(SwingConstants.CENTER);  // Centrar texto horizontalmente
        boton.setVerticalAlignment(SwingConstants.CENTER);    // Centrar texto verticalmente
    }

    private GridBagConstraints getGBC(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }
}
