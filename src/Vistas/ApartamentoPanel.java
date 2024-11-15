package Vistas;

import javax.swing.*;
import java.awt.*;
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
        setBackground(Color.GRAY); // Fondo gris para el panel principal

        // Crear el panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        inputPanel.setBackground(Color.GRAY);  // Fondo gris
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
        buttonPanel.setBackground(Color.GRAY); // Fondo gris
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
        txtApartamentos.setBackground(Color.LIGHT_GRAY); // Fondo gris claro para la sección de texto
        add(new JScrollPane(txtApartamentos), BorderLayout.SOUTH);

        // Acciones a botones
        btnCrear.addActionListener(e -> crearApartamento());
        btnLeer.addActionListener(e -> leerApartamentos());
        btnEliminar.addActionListener(e -> eliminarApartamento());
        btnEditar.addActionListener(e -> editarApartamento());
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto, SwingConstants.RIGHT);
        etiqueta.setForeground(Color.BLACK); // Letras negras
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

    private void crearApartamento() { /* código para crear */ }
    private void leerApartamentos() { /* código para leer */ }
    private void eliminarApartamento() { /* código para eliminar */ }
    private void editarApartamento() { /* código para editar */ }
}
