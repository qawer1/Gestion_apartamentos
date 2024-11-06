package dao;

import conexion.conexion;
import modelo.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // Método para crear una venta
    public void crearVenta(Venta venta) {
        String sql = "INSERT INTO Venta (precioTotal, numeroCuotas, intereses, idCliente, idApartamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, venta.getPrecioTotal());
            pstmt.setInt(2, venta.getNumeroCuotas());
            pstmt.setDouble(3, venta.getIntereses());
            pstmt.setInt(4, venta.getIdCliente());
            pstmt.setInt(5, venta.getIdApartamento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear venta: " + e.getMessage());
        }
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerVentas() {
        String sql = "SELECT * FROM Venta";
        List<Venta> ventas = new ArrayList<>();
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setPrecioTotal(rs.getDouble("precioTotal"));
                venta.setNumeroCuotas(rs.getInt("numeroCuotas"));
                venta.setIntereses(rs.getDouble("intereses"));
                venta.setIdCliente(rs.getInt("idCliente"));
                venta.setIdApartamento(rs.getInt("idApartamento"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas: " + e.getMessage());
        }
        return ventas;
    }

    // Método para actualizar una venta
    public void actualizarVenta(Venta venta) {
        String sql = "UPDATE Venta SET precioTotal = ?, numeroCuotas = ?, intereses = ?, idCliente = ?, idApartamento = ? WHERE idVenta = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, venta.getPrecioTotal());
            pstmt.setInt(2, venta.getNumeroCuotas());
            pstmt.setDouble(3, venta.getIntereses());
            pstmt.setInt(4, venta.getIdCliente());
            pstmt.setInt(5, venta.getIdApartamento());
            pstmt.setInt(6, venta.getIdVenta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
        }
    }

    // Método para eliminar una venta
    public void eliminarVenta(int idVenta) {
        String sql = "DELETE FROM Venta WHERE idVenta = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
        }
    }
}
