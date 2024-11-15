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

        // Crear el panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY); // Mantener el color de fondo igual
        inputPanel.add(new JLabel("ID del Apartamento:"));
        txtID = new JTextField();
        inputPanel.add(txtID);

        inputPanel.add(new JLabel("ID Torre:"));
        cbIDTorre = new JComboBox<>();
        cargarTorres();
        inputPanel.add(cbIDTorre);

        inputPanel.add(new JLabel("Número del Apartamento:"));
        txtNumeroApartamento = new JTextField();
        inputPanel.add(txtNumeroApartamento);

        inputPanel.add(new JLabel("Valor del Apartamento:"));
        txtValorApartamento = new JTextField();
        inputPanel.add(txtValorApartamento);

        inputPanel.add(new JLabel("Tipo de Unidad:"));
        txtTipoUnidad = new JTextField();
        inputPanel.add(txtTipoUnidad);

        inputPanel.add(new JLabel("Área:"));
        txtArea = new JTextField();
        inputPanel.add(txtArea);

        inputPanel.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        inputPanel.add(txtMatricula);

        inputPanel.add(new JLabel("Estado de Venta:"));
        txtEstadoVenta = new JTextField();
        inputPanel.add(txtEstadoVenta);

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5)); 
        buttonPanel.setBackground(Color.GRAY); 
        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        // Ajustar tamaño de los botones
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
    txtApartamentos.setText("");  // Limpiar el área de texto antes de listar
    if (apartamentos.isEmpty()) {
        txtApartamentos.append("No hay apartamentos registrados.\n");
    } else {
        for (Apartamento apartamento : apartamentos) {
            txtApartamentos.append(String.format("ID Apartamento: %d\n", apartamento.getID_apartamento()));
            txtApartamentos.append(String.format("ID Torre: %d\n", apartamento.getID_torre()));
            txtApartamentos.append(String.format("Número Apartamento: %d\n", apartamento.getNumero_apartamento()));
            txtApartamentos.append(String.format("Valor: %.2f\n", apartamento.getValorApartamento()));
            txtApartamentos.append(String.format("Tipo Unidad: %s\n", apartamento.getTipoUnidad()));
            txtApartamentos.append(String.format("Área: %s\n", apartamento.getArea()));
            txtApartamentos.append(String.format("Matrícula: %s\n", apartamento.getMatricula()));
            txtApartamentos.append(String.format("Estado de Venta: %s\n", apartamento.getEstadoVenta()));
            txtApartamentos.append("----------------------------\n");
        }
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
        boton.setPreferredSize(new Dimension(100, 30)); // Ajuste de tamaño más pequeño
    }
}