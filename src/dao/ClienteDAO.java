package dao;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexion.conexion;  // Importamos la clase de conexión

public class ClienteDAO {

    // Método para conectar a la base de datos
    private Connection conectar() {
        return conexion.conectar();  // Usamos la clase conexion para la conexión
    }

    // Método para crear un nuevo cliente
    public void crearCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correoElectronico) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cliente.getCedula());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getSisben());
            pstmt.setString(4, cliente.getSubsidioMinisterio());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setString(6, cliente.getTelefono());
            pstmt.setString(7, cliente.getCorreoElectronico());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCedula(rs.getInt("cedula"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setSisben(rs.getString("sisben"));
                cliente.setSubsidioMinisterio(rs.getString("subsidioMinisterio"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreoElectronico(rs.getString("correoElectronico"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes");
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para actualizar un cliente
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, sisben = ?, subsidioMinisterio = ?, direccion = ?, telefono = ?, correoElectronico = ? WHERE cedula = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getSisben());
            pstmt.setString(3, cliente.getSubsidioMinisterio());
            pstmt.setString(4, cliente.getDireccion());
            pstmt.setString(5, cliente.getTelefono());
            pstmt.setString(6, cliente.getCorreoElectronico());
            pstmt.setInt(7, cliente.getCedula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente con cédula: " + cliente.getCedula());
            e.printStackTrace();
        }
    }

    // Método para eliminar un cliente
    public void eliminarCliente(int cedula) {
        String sql = "DELETE FROM Cliente WHERE cedula = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente con cédula: " + cedula);
            e.printStackTrace();
        }
    }
}
