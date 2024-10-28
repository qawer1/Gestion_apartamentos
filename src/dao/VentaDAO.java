// VentaDAO.java
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

    // Crear una nueva venta
    public boolean crearVenta(Venta venta) {
        String sql = "INSERT INTO Venta (precioTotal, numeroCuotas, intereses, idCliente, idApartamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, venta.getPrecioTotal());
            pstmt.setInt(2, venta.getNumeroCuotas());
            pstmt.setDouble(3, venta.getIntereses());
            pstmt.setInt(4, venta.getIdCliente());
            pstmt.setInt(5, venta.getIdApartamento());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear venta: " + e.getMessage());
            return false;
        }
    }

    // Obtener una lista de todas las ventas
    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("idVenta"),
                        rs.getDouble("precioTotal"),
                        rs.getInt("numeroCuotas"),
                        rs.getDouble("intereses"),
                        rs.getInt("idCliente"),
                        rs.getInt("idApartamento")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas: " + e.getMessage());
        }
        return ventas;
    }

    // Obtener una venta por su ID
    public Venta obtenerVentaPorId(int idVenta) {
        String sql = "SELECT * FROM Venta WHERE idVenta = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Venta(
                        rs.getInt("idVenta"),
                        rs.getDouble("precioTotal"),
                        rs.getInt("numeroCuotas"),
                        rs.getDouble("intereses"),
                        rs.getInt("idCliente"),
                        rs.getInt("idApartamento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener venta: " + e.getMessage());
        }
        return null;
    }

    // Actualizar una venta existente
    public boolean actualizarVenta(Venta venta) {
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
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una venta por su ID
    public boolean eliminarVenta(int idVenta) {
        String sql = "DELETE FROM Venta WHERE idVenta = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
            return false;
        }
    }
}
