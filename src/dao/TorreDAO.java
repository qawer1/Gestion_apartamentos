package dao;

import modelo.Torre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TorreDAO {

    // Método de conexión
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

    // Método para crear una torre, incluyendo el ID_TORRE
    public void crearTorre(Torre torre) {
        String sql = "INSERT INTO Torre (ID_TORRE, ID_PROYECTO, NUMERO_TORRE, NUMEROAPARTAMENTOS) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torre.getIdTorre()); // Ahora se incluye el ID_TORRE
            pstmt.setInt(2, torre.getIdProyecto());
            pstmt.setInt(3, torre.getNumero_torre());
            pstmt.setInt(4, torre.getNumeroApartamentos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear torre: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener todas las torres
    public List<Torre> obtenerTorres() {
        List<Torre> torres = new ArrayList<>();
        String sql = "SELECT ID_TORRE, ID_PROYECTO, NUMERO_TORRE, NUMEROAPARTAMENTOS FROM Torre";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Torre torre = new Torre();
                torre.setIdTorre(rs.getInt("ID_TORRE"));
                torre.setIdProyecto(rs.getInt("ID_PROYECTO"));
                torre.setNumero_torre(rs.getInt("NUMERO_TORRE"));
                torre.setNumeroApartamentos(rs.getInt("NUMEROAPARTAMENTOS"));
                torres.add(torre);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener torres");
            e.printStackTrace();
        }
        return torres;
    }

    // Método para actualizar una torre
    public void actualizarTorre(Torre torre) {
        String sql = "UPDATE Torre SET ID_PROYECTO = ?, NUMERO_TORRE = ?, NUMEROAPARTAMENTOS = ? WHERE ID_TORRE = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torre.getIdProyecto());
            pstmt.setInt(2, torre.getNumero_torre());
            pstmt.setInt(3, torre.getNumeroApartamentos());
            pstmt.setInt(4, torre.getIdTorre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar torre con ID: " + torre.getIdTorre());
            e.printStackTrace();
        }
    }

    // Método para eliminar una torre
    public void eliminarTorre(int idTorre) {
        String sql = "DELETE FROM Torre WHERE ID_TORRE = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTorre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar torre con ID: " + idTorre);
            e.printStackTrace();
        }
    }
}
