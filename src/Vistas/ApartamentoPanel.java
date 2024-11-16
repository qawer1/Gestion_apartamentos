package Vistas;

import controlador.ApartamentoController;
import modelo.Apartamento;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ApartamentoPanel extends JPanel {
    private JTextField txtID;
    private JTextField txtNumeroApartamento;
    private JTextField txtValorApartamento;
    private JTextField txtTipoUnidad;
    private JTextField txtArea;
    private JTextField txtMatricula;
    private JTextField txtEstadoVenta;
    private JComboBox<Integer> cbIDTorre;
    private JTextArea txtApartamentos;
    private ApartamentoController apartamentoController;
    private JButton btnRefrescar;  // Botón para refrescar

    public ApartamentoPanel() {
        // Inicializar el controlador
        apartamentoController = new ApartamentoController();

        // Configuración del panel principal
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(400, 300)); // Limitar tamaño del panel

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));  // Aumentado a 9 para incluir el botón "Refrescar"
        inputPanel.setBackground(Color.GRAY);

        // Configurar campos de entrada y etiquetas
        JLabel labelID = new JLabel("ID Apartamento:");
        labelID.setForeground(Color.WHITE);
        inputPanel.add(labelID);
        txtID = new JTextField();
        inputPanel.add(txtID);

        JLabel labelIDTorre = new JLabel("ID Torre:");
        labelIDTorre.setForeground(Color.WHITE);
        inputPanel.add(labelIDTorre);
        cbIDTorre = new JComboBox<>();
        cargarTorres();
        inputPanel.add(cbIDTorre);

        JLabel labelNumero = new JLabel("Número Apartamento:");
        labelNumero.setForeground(Color.WHITE);
        inputPanel.add(labelNumero);
        txtNumeroApartamento = new JTextField();
        inputPanel.add(txtNumeroApartamento);

        JLabel labelValor = new JLabel("Valor Apartamento:");
        labelValor.setForeground(Color.WHITE);
        inputPanel.add(labelValor);
        txtValorApartamento = new JTextField();
        inputPanel.add(txtValorApartamento);

        JLabel labelTipoUnidad = new JLabel("Tipo Unidad:");
        labelTipoUnidad.setForeground(Color.WHITE);
        inputPanel.add(labelTipoUnidad);
        txtTipoUnidad = new JTextField();
        inputPanel.add(txtTipoUnidad);

        JLabel labelArea = new JLabel("Área:");
        labelArea.setForeground(Color.WHITE);
        inputPanel.add(labelArea);
        txtArea = new JTextField();
        inputPanel.add(txtArea);

        JLabel labelMatricula = new JLabel("Matrícula:");
        labelMatricula.setForeground(Color.WHITE);
        inputPanel.add(labelMatricula);
        txtMatricula = new JTextField();
        inputPanel.add(txtMatricula);

        JLabel labelEstadoVenta = new JLabel("Estado Venta:");
        labelEstadoVenta.setForeground(Color.WHITE);
        inputPanel.add(labelEstadoVenta);
        txtEstadoVenta = new JTextField();
        inputPanel.add(txtEstadoVenta);

        // Panel de botones con FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.GRAY);

        // Crear botones y agregarles configuración de tamaño
        JButton btnCrear = new JButton("Crear");
        JButton btnLeer = new JButton("Leer");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");
        btnRefrescar = new JButton("Refrescar");  // Crear el botón Refrescar aquí

        ajustarBoton(btnCrear);
        ajustarBoton(btnLeer);
        ajustarBoton(btnEliminar);
        ajustarBoton(btnEditar);
        ajustarBoton(btnRefrescar);

        // Agregar botones al panel de botones
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnRefrescar);

        // Agregar paneles al panel principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Configuración del área de texto para mostrar resultados
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
                    txtApartamentos.append(String.format("Estado Venta: %s\n", apartamento.getEstadoVenta()));
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

        // Acción para refrescar las torres
        btnRefrescar.addActionListener(e -> refrescarTorres());  // Acción para refrescar el JComboBox
    }

    // Método para cargar las torres en el combo box
    private void cargarTorres() {
        List<Integer> idsTorres = apartamentoController.obtenerIdsTorres();
        for (Integer id : idsTorres) {
            cbIDTorre.addItem(id);
        }
    }

    // Método para refrescar las torres en el combo box
    private void refrescarTorres() {
        cbIDTorre.removeAllItems();  // Limpiar elementos actuales
        cargarTorres();  // Recargar las torres desde el controlador
    }

    // Método para limpiar los campos de entrada
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

    // Método para ajustar el tamaño de los botones
    private void ajustarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setPreferredSize(new Dimension(100, 25)); // Tamaño compacto de los botones
    }
}
