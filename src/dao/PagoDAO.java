// PagoDAO.java
package dao;

import conexion.conexion;
import modelo.Pago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public void crearPago(Pago pago) {
        String sql = "INSERT INTO Pago (valorPago, fecha, Cedula_cliente, Cedula_asesor) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pago.getValorPago());
            pstmt.setString(2, pago.getFecha());
            pstmt.setInt(3, pago.getCedula_cliente());
            pstmt.setInt(4, pago.getCedula_asesor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear pago: " + e.getMessage());
        }
    }

    public List<Pago> obtenerPagos() {
        String sql = "SELECT * FROM Pago";
        List<Pago> pagos = new ArrayList<>();
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setID_Pago(rs.getInt("ID_Pago"));
                pago.setValorPago(rs.getDouble("valorPago"));
                pago.setFecha(rs.getString("fecha")); // Asumiendo que `fecha` es de tipo String
                pago.setCedula_cliente(rs.getInt("Cedula_cliente"));
                pago.setCedula_asesor(rs.getInt("Cedula_asesor"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pagos: " + e.getMessage());
        }
        return pagos;
    }

    public void actualizarPago(Pago pago) {
        String sql = "UPDATE Pago SET valorPago = ?, fecha = ?, Cedula_cliente = ?, Cedula_asesor = ? WHERE ID_Pago = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pago.getValorPago());
            pstmt.setString(2, pago.getFecha()); // Suponiendo que `fecha` es de tipo Date
            pstmt.setInt(3, pago.getCedula_cliente());
            pstmt.setInt(4, pago.getCedula_asesor());
            pstmt.setInt(5, pago.getID_Pago());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
        }
    }

    public void eliminarPago(int ID_Pago) {
        String sql = "DELETE FROM Pago WHERE ID_Pago = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_Pago);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar pago: " + e.getMessage());
        }
    }
}
