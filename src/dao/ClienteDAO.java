package dao;

import conexion.conexion;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para crear un nuevo cliente en la base de datos
    public void crearCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correoElectronico) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.connect();
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
        }
    }

    // Método para obtener todos los clientes de la base de datos
    public List<Cliente> obtenerClientes() {
        String sql = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
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
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Método para actualizar un cliente existente en la base de datos
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, sisben = ?, subsidioMinisterio = ?, direccion = ?, telefono = ?, correoElectronico = ? WHERE cedula = ?";
        try (Connection conn = conexion.connect();
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
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    // Método para eliminar un cliente de la base de datos
public void eliminarCliente(int cedula) { // Cambia String a int
    String sql = "DELETE FROM Cliente WHERE cedula = ?";
    try (Connection conn = conexion.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, cedula); // Cambia setString a setInt
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al eliminar cliente: " + e.getMessage());
    }
}
}