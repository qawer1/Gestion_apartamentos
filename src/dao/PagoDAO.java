package dao;

import modelo.Pago;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    // Método de conexión
    private Connection conectar() {
        Connection conn = null;
        try {
            // Configuración de conexión para Oracle
            String url = "jdbc:oracle:thin:@localhost:1521:xe";  // Ajusta el URL si es necesario
            String username = "SYSTEM";  // Cambia el usuario según tu configuración
            String password = "Case18283022";  // Cambia la contraseña según tu configuración
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }

    // Método para crear un pago
    public void crearPago(Pago pago) {
        String sql = "INSERT INTO Pago (ID_PAGO, VALORPAGO, FECHA, CEDULA_CLIENTE, CEDULA_ASESOR) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pago.getID_Pago());
            pstmt.setDouble(2, pago.getValorPago());
            pstmt.setString(3, pago.getFecha());
            pstmt.setInt(4, pago.getCedula_cliente());
            pstmt.setInt(5, pago.getCedula_asesor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear pago: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener todos los pagos
    public List<Pago> obtenerPagos() {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT ID_PAGO, VALORPAGO, FECHA, CEDULA_CLIENTE, CEDULA_ASESOR FROM Pago";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setID_Pago(rs.getInt("ID_PAGO"));
                pago.setValorPago(rs.getDouble("VALORPAGO"));
                pago.setFecha(rs.getString("FECHA"));
                pago.setCedula_cliente(rs.getInt("CEDULA_CLIENTE"));
                pago.setCedula_asesor(rs.getInt("CEDULA_ASESOR"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pagos: " + e.getMessage());
            e.printStackTrace();
        }
        return pagos;
    }

    // Método para actualizar un pago
    public void actualizarPago(Pago pago) {
        String sql = "UPDATE Pago SET VALORPAGO = ?, FECHA = ?, CEDULA_CLIENTE = ?, CEDULA_ASESOR = ? WHERE ID_PAGO = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pago.getValorPago());
            pstmt.setString(2, pago.getFecha());
            pstmt.setInt(3, pago.getCedula_cliente());
            pstmt.setInt(4, pago.getCedula_asesor());
            pstmt.setInt(5, pago.getID_Pago());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar pago con ID: " + pago.getID_Pago());
            e.printStackTrace();
        }
    }

    // Método para eliminar un pago
    public void eliminarPago(int idPago) {
        String sql = "DELETE FROM Pago WHERE ID_PAGO = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPago);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar pago con ID: " + idPago);
            e.printStackTrace();
        }
    }
}