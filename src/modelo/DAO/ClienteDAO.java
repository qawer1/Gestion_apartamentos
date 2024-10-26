package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAO {

    // Método para conectar a la base de datos
    private Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:\\Users\\Sebastian\\Downloads\\bd\\base de datos apartamentos.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método para insertar un cliente
    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente(cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correo) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getCedula());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getSisben());
            pstmt.setString(4, cliente.getSubsidioMinisterio());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setString(6, cliente.getTelefono());
            pstmt.setString(7, cliente.getCorreo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obtener un cliente por cédula
    public Cliente obtenerClientePorCedula(String cedula) {
        String sql = "SELECT * FROM Cliente WHERE cedula = ?";
        Cliente cliente = null;

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("sisben"),
                    rs.getString("subsidioMinisterio"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cliente;
    }

    // Método para actualizar un cliente
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, sisben = ?, subsidioMinisterio = ?, direccion = ?, telefono = ?, correo = ? WHERE cedula = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getSisben());
            pstmt.setString(3, cliente.getSubsidioMinisterio());
            pstmt.setString(4, cliente.getDireccion());
            pstmt.setString(5, cliente.getTelefono());
            pstmt.setString(6, cliente.getCorreo());
            pstmt.setString(7, cliente.getCedula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un cliente
    public void eliminarCliente(String cedula) {
        String sql = "DELETE FROM Cliente WHERE cedula = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
