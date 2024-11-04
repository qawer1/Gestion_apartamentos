package Vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApartamentoPanel extends JPanel {
    private JTextField txtNumero, txtValor, txtTipo, txtArea, txtMatricula;
    private JButton btnCrear, btnLeer, btnActualizar, btnEliminar;

    public ApartamentoPanel() {
        setLayout(new GridLayout(7, 2, 5, 5));  // Layout en una cuadrícula

        // Crear los campos y etiquetas
        add(new JLabel("Número de Apartamento:"));
        txtNumero = new JTextField();
        add(txtNumero);

        add(new JLabel("Valor del Apartamento:"));
        txtValor = new JTextField();
        add(txtValor);

        add(new JLabel("Tipo de Unidad:"));
        txtTipo = new JTextField();
        add(txtTipo);

        add(new JLabel("Área:"));
        txtArea = new JTextField();
        add(txtArea);

        add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        // Crear los botones para el CRUD
        btnCrear = new JButton("Crear");
        btnLeer = new JButton("Leer");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        add(btnCrear);
        add(btnLeer);
        add(btnActualizar);
        add(btnEliminar);

        // Agregar los listeners a los botones
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearApartamento();
            }
        });

        btnLeer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leerApartamento();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarApartamento();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarApartamento();
            }
        });
    }

    // Métodos CRUD simulados (con lógica real en la base de datos)
    private void crearApartamento() {
        String numero = txtNumero.getText();
        String valor = txtValor.getText();
        String tipo = txtTipo.getText();
        String area = txtArea.getText();
        String matricula = txtMatricula.getText();

        // Aquí colocarías la lógica para insertar en la base de datos
        JOptionPane.showMessageDialog(this, "Apartamento creado: " + numero);
    }

    private void leerApartamento() {
        // Aquí colocarías la lógica para leer un apartamento específico de la base de datos
        JOptionPane.showMessageDialog(this, "Leer apartamento (por número)");
    }

    private void actualizarApartamento() {
        // Aquí colocarías la lógica para actualizar el apartamento en la base de datos
        JOptionPane.showMessageDialog(this, "Apartamento actualizado.");
    }

    private void eliminarApartamento() {
        // Aquí colocarías la lógica para eliminar el apartamento de la base de datos
        JOptionPane.showMessageDialog(this, "Apartamento eliminado.");
    }
}
