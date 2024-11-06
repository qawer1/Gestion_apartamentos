package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ProyectoController;
import modelo.Proyecto;
import java.util.List;

public class ProyectoPanel extends JPanel {
    private JTextField txtNombre;
    private JTextField txtTorres;
    private JTextField txtId; // Campo para ingresar el ID del proyecto
    private JTextArea txtProyectos;
    private ProyectoController proyectoController;

    public ProyectoPanel() {
        proyectoController = new ProyectoController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("ID del Proyecto:")); // Etiqueta para ID
        txtId = new JTextField(); // Campo para ID
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Nombre del Proyecto:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Número de Torres:"));
        txtTorres = new JTextField();
        inputPanel.add(txtTorres);

        // Cambiar la disposición de los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Disposición vertical
        JButton btnCrear = new JButton("Crear Proyecto");
        JButton btnLeer = new JButton("Leer Proyectos");
        JButton btnEliminar = new JButton("Eliminar Proyecto");
        JButton btnEditar = new JButton("Editar Proyecto");

        // Añadir botones al panel
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        // Añadir paneles al layout principal
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER); // Añadir el panel de botones al centro

        txtProyectos = new JTextArea(10, 30);
        txtProyectos.setEditable(false);
        add(new JScrollPane(txtProyectos), BorderLayout.SOUTH); // Cambiar a la parte inferior

        // ActionListener para el botón "Crear Proyecto"
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                int numeroTorres = Integer.parseInt(txtTorres.getText());
                proyectoController.crearProyecto(nombre, numeroTorres);
                JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente");
                limpiarCampos();
            }
        });

        // ActionListener para el botón "Leer Proyectos"
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Proyecto> proyectos = proyectoController.obtenerProyectos();
                txtProyectos.setText("");  // Limpiar el área de texto antes de listar
                for (Proyecto proyecto : proyectos) {
                    txtProyectos.append(proyecto + "\n");
                }
            }
        });

        // ActionListener para el botón "Eliminar Proyecto"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProyecto = Integer.parseInt(txtId.getText());
                proyectoController.eliminarProyecto(idProyecto);
                JOptionPane.showMessageDialog(null, "Proyecto eliminado exitosamente");
                limpiarCampos();
            }
        });

        // ActionListener para el botón "Editar Proyecto"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProyecto = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                int numeroTorres = Integer.parseInt(txtTorres.getText());
                proyectoController.actualizarProyecto(idProyecto, nombre, numeroTorres);
                JOptionPane.showMessageDialog(null, "Proyecto actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtId.setText(""); // Limpiar el campo de ID
        txtNombre.setText("");
        txtTorres.setText("");
    }
}
