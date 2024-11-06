package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ProyectoController;
import modelo.Proyecto;

public class ProyectoPanel extends JPanel {
    private JTextField txtIdProyecto;      // Campo para ingresar el ID del proyecto
    private JTextField txtNombre;          // Campo para ingresar el nombre del proyecto
    private JTextField txtNumeroTorre;     // Campo para ingresar el número de la torre
   private JTextArea txtProyectos;        // Área para mostrar proyectos
    private ProyectoController proyectoController;

    public ProyectoPanel() {
        proyectoController = new ProyectoController();
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // Ahora con 6 campos: ID, Nombre, IdTorre, NumeroTorre, NumeroApartamentos
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

        // Área de texto para mostrar los proyectos
        txtProyectos = new JTextArea(10, 30);
        txtProyectos.setEditable(false);
        add(new JScrollPane(txtProyectos), BorderLayout.SOUTH); // Cambiar a la parte inferior

        // ActionListener para el botón "Crear Proyecto"
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombre.getText();
                    int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());
                    

                    // Crear un Proyecto con todos los datos
                    proyectoController.crearProyecto(nombre, numeroTorre);
                    JOptionPane.showMessageDialog(null, "Proyecto creado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos en todos los campos.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el proyecto: " + ex.getMessage());
                }
            }
        });

        // ActionListener para el botón "Leer Proyectos"
        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String proyectos = proyectoController.listarProyectos();
                txtProyectos.setText(proyectos);  // Mostrar la lista de proyectos en el área de texto
            }
        });

        // ActionListener para el botón "Eliminar Proyecto"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                    proyectoController.eliminarProyecto(idProyecto);
                    JOptionPane.showMessageDialog(null, "Proyecto eliminado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de proyecto válido.");
                }
            }
        });

        // ActionListener para el botón "Editar Proyecto"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProyecto = Integer.parseInt(txtIdProyecto.getText());
                    String nombre = txtNombre.getText();
                    int numeroTorre = Integer.parseInt(txtNumeroTorre.getText());

                    // Actualizar el proyecto con todos los datos
                    proyectoController.actualizarProyecto(idProyecto, nombre,  numeroTorre);
                    JOptionPane.showMessageDialog(null, "Proyecto actualizado exitosamente");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos en todos los campos.");
                }
            }
        });
    }

    // Método para limpiar los campos después de realizar una acción
    private void limpiarCampos() {
        txtIdProyecto.setText("");          // Limpiar el campo de ID de proyecto
        txtNombre.setText("");              // Limpiar el campo de nombre
        txtNumeroTorre.setText("");         // Limpiar el campo de número de torre
        
    }
}
