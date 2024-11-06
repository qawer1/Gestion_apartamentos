// ProyectoPanel.java
package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ProyectoController;
import modelo.Proyecto;
import java.util.List;

public class ProyectoPanel extends JPanel {
    private JTextField txtIdProyecto;      // Campo para ingresar el ID del proyecto
    private JTextField txtNombre;          // Campo para ingresar el nombre del proyecto
    private JTextField txtNumeroTorre;     // Campo para ingresar el número de la torre
    private JTextArea txtProyectos;        // Área para mostrar proyectos
    private ProyectoController proyectoController;

    public ProyectoPanel() {
        proyectoController = new ProyectoController();
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        inputPanel.add(new JLabel("ID del Proyecto:"));
        txtIdProyecto = new JTextField();
        inputPanel.add(txtIdProyecto);

        inputPanel.add(new JLabel("Nombre del Proyecto:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Número de Torre:"));
        txtNumeroTorre = new JTextField();
        inputPanel.add(txtNumeroTorre);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnCrear = new JButton("Crear Proyecto");
        JButton btnLeer = new JButton("Leer Proyectos");
        JButton btnEliminar = new JButton("Eliminar Proyecto");
        JButton btnEditar = new JButton("Editar Proyecto");

        buttonPanel.add(btnCrear);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnEditar);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        txtProyectos = new JTextArea(10, 30);
        txtProyectos.setEditable(false);
        add(new JScrollPane(txtProyectos), BorderLayout.SOUTH);

        // Acción para crear proyecto
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                proyectoController.crearProyecto(nombre, numeroTorre);
                JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente");
                limpiarCampos();
            }
        });

        // Acción para leer proyectos
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Proyecto> proyectos = proyectoController.obtenerProyectos();
                txtProyectos.setText(""); // Limpiar antes de listar
                for (Proyecto proyecto : proyectos) {
                    txtProyectos.append("ID Proyecto: " + proyecto.getIdProyecto() + 
                                         ", Nombre: " + proyecto.getNombre() + 
                                         ", Torres: " + proyecto.getNumeroTorres() + "\n");
                }
            }
        });

        // Acción para eliminar proyecto
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                proyectoController.eliminarProyecto(idProyecto);
                JOptionPane.showMessageDialog(null, "Proyecto eliminado exitosamente");
                limpiarCampos();
            }
        });

        // Acción para editar proyecto
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                String nombre = txtNombre.getText();
                int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());

                proyectoController.actualizarProyecto(idProyecto, nombre, numeroTorre);
                JOptionPane.showMessageDialog(null, "Proyecto actualizado exitosamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtIdProyecto.setText("");
        txtNombre.setText("");
        txtNumeroTorre.setText("");
    }
}
