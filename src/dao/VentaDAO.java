package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;

public class VentaDAO {

    private Connection conectar() {
        Connection conn = null;
        try {
            // Configuración de conexión para Oracle
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "SYSTEM";
            String password = "Case18283022";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método para crear una venta
    public void crearVenta(Venta venta) {
        String sql = "INSERT INTO Venta (PRECIO_TOTAL, NUMERO_CUOTAS, INTERESES, CEDULA_CLIENTES, ID_APARTAMENTO, ESTADO_VENTA) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, venta.getPrecioTotal());
            pstmt.setInt(2, venta.getNumeroCuotas());
            pstmt.setDouble(3, venta.getIntereses());
            pstmt.setInt(4, venta.getIdCliente());
            pstmt.setInt(5, venta.getIdApartamento());
            pstmt.setString(6, venta.getEstadoVenta());  // Establecer el estado de la venta

            pstmt.executeUpdate();

            // Obtener el ID generado por la base de datos
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    venta.setIdVenta(rs.getInt(1));  // Asignar el ID generado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al crear venta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT ID_VENTA, PRECIO_TOTAL, NUMERO_CUOTAS, INTERESES, CEDULA_CLIENTES, ID_APARTAMENTO, ESTADO_VENTA FROM Venta";  // Agregar estado de la venta

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("ID_VENTA"));
                venta.setPrecioTotal(rs.getDouble("PRECIO_TOTAL"));
                venta.setNumeroCuotas(rs.getInt("NUMERO_CUOTAS"));
                venta.setIntereses(rs.getDouble("INTERESES"));
                venta.setIdCliente(rs.getInt("CEDULA_CLIENTES"));
                venta.setIdApartamento(rs.getInt("ID_APARTAMENTO"));
                venta.setEstadoVenta(rs.getString("ESTADO_VENTA"));  // Obtener el estado de la venta
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas");
            e.printStackTrace();
        }
        return ventas;
    }

    // Método para actualizar una venta
    public void actualizarVenta(Venta venta) {
        String sql = "UPDATE Venta SET PRECIO_TOTAL = ?, NUMERO_CUOTAS = ?, INTERESES = ?, CEDULA_CLIENTES = ?, ID_APARTAMENTO = ?, ESTADO_VENTA = ? WHERE ID_VENTA = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, venta.getPrecioTotal());
            pstmt.setInt(2, venta.getNumeroCuotas());
            pstmt.setDouble(3, venta.getIntereses());
            pstmt.setInt(4, venta.getIdCliente());
            pstmt.setInt(5, venta.getIdApartamento());
            pstmt.setString(6, venta.getEstadoVenta());  // Actualizar el estado de la venta
            pstmt.setInt(7, venta.getIdVenta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta con ID: " + venta.getIdVenta());
            e.printStackTrace();
        }
    }

    // Método para eliminar una venta
    public void eliminarVenta(int idVenta) {
        String sql = "DELETE FROM Venta WHERE ID_VENTA = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar venta con ID: " + idVenta);
            e.printStackTrace();
        }
    }
}
