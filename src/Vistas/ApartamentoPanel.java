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
    private JTextField txtIDTorre;
    private JTextField txtNumeroApartamento;
    private JTextField txtValorApartamento;
    private JTextField txtTipoUnidad;
    private JTextField txtArea;
    private JTextField txtMatricula;
    private JTextArea txtApartamentos;
    private ApartamentoController apartamentoController;

    public ApartamentoPanel() {
        apartamentoController = new ApartamentoController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));

        inputPanel.add(new JLabel("ID del Apartamento:"));
        txtID = new JTextField();
        inputPanel.add(txtID);

        inputPanel.add(new JLabel("ID Torre:"));
        txtIDTorre = new JTextField();
        inputPanel.add(txtIDTorre);

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

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Apartamento");
        JButton btnLeer = new JButton("Leer Apartamentos");
        JButton btnEliminar = new JButton("Eliminar Apartamento");
        JButton btnEditar = new JButton("Editar Apartamento");

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
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idTorre = Integer.parseInt(txtIDTorre.getText());
                    int numero = Integer.parseInt(txtNumeroApartamento.getText());
                    double valor = Double.parseDouble(txtValorApartamento.getText());
                    String tipoUnidad = txtTipoUnidad.getText();
                    double area = Double.parseDouble(txtArea.getText());
                    String matricula = txtMatricula.getText();

                    apartamentoController.crearApartamento(idTorre, numero, valor, tipoUnidad, area, matricula);
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
                txtApartamentos.setText(""); // Limpiar antes de listar
                for (Apartamento apartamento : apartamentos) {
                    txtApartamentos.append("ID: " + apartamento.getID_apartamento() + 
                                            ", Torre ID: " + apartamento.getID_torre() + 
                                            ", Número: " + apartamento.getNumero_apartamento() +
                                            ", Valor: " + apartamento.getValorApartamento() +
                                            ", Tipo: " + apartamento.getTipoUnidad() +
                                            ", Área: " + apartamento.getArea() +
                                            ", Matrícula: " + apartamento.getMatricula() + "\n");
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
                    int idTorre = Integer.parseInt(txtIDTorre.getText());
                    int numero = Integer.parseInt(txtNumeroApartamento.getText());
                    double valor = Double.parseDouble(txtValorApartamento.getText());
                    String tipoUnidad = txtTipoUnidad.getText();
                    double area = Double.parseDouble(txtArea.getText());
                    String matricula = txtMatricula.getText();

                    apartamentoController.actualizarApartamento(idApartamento, idTorre, numero, valor, tipoUnidad, area, matricula);
                    JOptionPane.showMessageDialog(null, "Apartamento actualizado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void limpiarCampos() {
        txtID.setText("");
        txtIDTorre.setText("");
        txtNumeroApartamento.setText("");
        txtValorApartamento.setText("");
        txtTipoUnidad.setText("");
        txtArea.setText("");
        txtMatricula.setText("");
    }
}
