package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Pago;

public class PagoDAO {

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

    public void insertarPago(Pago pago) {
        String sql = "INSERT INTO Pago(monto, fechaPago, ventaId) VALUES(?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pago.getMonto());
            pstmt.setString(2, pago.getFechaPago());
            pstmt.setInt(3, pago.getVentaId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pago> obtenerPagosPorVenta(int ventaId) {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM Pago WHERE ventaId = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ventaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pago pago = new Pago(
                    rs.getInt("id"),
                    rs.getDouble("monto"),
                    rs.getString("fechaPago"),
                    rs.getInt("ventaId")
                );
                pagos.add(pago);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pagos;
    }

    public void actualizarPago(Pago pago) {
        String sql = "UPDATE Pago SET monto = ?, fechaPago = ? WHERE id = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pago.getMonto());
            pstmt.setString(2, pago.getFechaPago());
            pstmt.setInt(3, pago.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPago(int id) {
        String sql = "DELETE FROM Pago WHERE id = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
