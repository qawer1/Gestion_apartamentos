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
        setBackground(Color.GRAY);

        // Panel para la entrada de datos
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear etiquetas y campos de entrada
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("ID del Apartamento:"), gbc);
        gbc.gridx = 1;
        txtID = new JTextField(10);
        inputPanel.add(txtID, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("ID Torre:"), gbc);
        gbc.gridx = 1;
        cbIDTorre = new JComboBox<>();
        cargarTorres();
        inputPanel.add(cbIDTorre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Número del Apartamento:"), gbc);
        gbc.gridx = 1;
        txtNumeroApartamento = new JTextField(10);
        inputPanel.add(txtNumeroApartamento, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Valor del Apartamento:"), gbc);
        gbc.gridx = 1;
        txtValorApartamento = new JTextField(10);
        inputPanel.add(txtValorApartamento, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("Tipo de Unidad:"), gbc);
        gbc.gridx = 1;
        txtTipoUnidad = new JTextField(10);
        inputPanel.add(txtTipoUnidad, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(new JLabel("Área:"), gbc);
        gbc.gridx = 1;
        txtArea = new JTextField(10);
        inputPanel.add(txtArea, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        inputPanel.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        txtMatricula = new JTextField(10);
        inputPanel.add(txtMatricula, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        inputPanel.add(new JLabel("Estado de Venta:"), gbc);
        gbc.gridx = 1;
        txtEstadoVenta = new JTextField(10);
        inputPanel.add(txtEstadoVenta, gbc);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonPanel.setBackground(Color.GRAY);

        
        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        Dimension buttonSize = new Dimension(2, 5); // Tamaño más pequeño para los botones
        btnCrear.setPreferredSize(buttonSize);
        btnLeer.setPreferredSize(buttonSize);
        btnEliminar.setPreferredSize(buttonSize);
        btnEditar.setPreferredSize(buttonSize);

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Agregar paneles a la interfaz principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Área de texto para mostrar apartamentos
        txtApartamentos = new JTextArea(10, 30);
        txtApartamentos.setEditable(false);
        add(new JScrollPane(txtApartamentos), BorderLayout.SOUTH);

        // Acción para crear apartamento
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Acción para leer apartamentos
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Acción para eliminar apartamento
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idApartamento = Integer.parseInt(txtID.getText());
                    apartamentoController.eliminarApartamento(idApartamento);
                    JOptionPane.showMessageDialog(null, "Apartamento eliminado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa un ID de apartamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para editar apartamento
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
}
