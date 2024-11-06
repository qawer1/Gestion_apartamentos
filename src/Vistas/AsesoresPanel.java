package Vistas;

import controlador.AsesorController;
import modelo.Asesor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AsesoresPanel extends JPanel {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextArea txtAsesores;
    private AsesorController asesorController;

    public AsesoresPanel() {
        asesorController = new AsesorController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        inputPanel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        inputPanel.add(txtCedula);

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        inputPanel.add(txtDireccion);

        inputPanel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        inputPanel.add(txtTelefono);

        inputPanel.add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField();
        inputPanel.add(txtCorreo);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Asesor");
        JButton btnLeer = new JButton("Leer Asesores");
        JButton btnEliminar = new JButton("Eliminar Asesor");
        JButton btnEditar = new JButton("Editar Asesor");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        txtAsesores = new JTextArea(10, 30);
        txtAsesores.setEditable(false);
        add(new JScrollPane(txtAsesores), BorderLayout.SOUTH);

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cedula = Integer.parseInt(txtCedula.getText());
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();

                asesorController.crearAsesor(cedula, nombre, direccion, telefono, correo);
                JOptionPane.showMessageDialog(null, "Asesor creado exitosamente");
                limpiarCampos();
            }
        });

        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Asesor> asesores = asesorController.obtenerAsesores();
                txtAsesores.setText("");
                for (Asesor asesor : asesores) {
                    txtAsesores.append("Cédula: " + asesor.getCedula() +
                                       ", Nombre: " + asesor.getNombre() +
                                       ", Dirección: " + asesor.getDireccion() +
                                       ", Teléfono: " + asesor.getTelefono() +
                                       ", Correo: " + asesor.getCorreo_electronico() + "\n");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cedula = Integer.parseInt(txtCedula.getText());
                asesorController.eliminarAsesor(cedula);
                JOptionPane.showMessageDialog(null, "Asesor eliminado exitosamente");
                limpiarCampos();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cedula = Integer.parseInt(txtCedula.getText());
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();

                asesorController.actualizarAsesor(cedula, nombre, direccion, telefono, correo);
                JOptionPane.showMessageDialog(null, "Asesor actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}
