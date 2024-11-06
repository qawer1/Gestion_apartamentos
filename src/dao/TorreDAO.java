package dao;

import conexion.conexion; // Asegúrate de que esta conexión esté bien configurada
import modelo.Torre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TorreDAO {

    public void crearTorre(Torre torre) {
        String sql = "INSERT INTO Torre (ID_proyecto, Numero_torre, numeroApartamentos) VALUES (?, ?, ?)";
        try (Connection conn = conexion.connect(); // Asegúrate de que el método connect() sea correcto
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torre.getIdProyecto());
            pstmt.setInt(2, torre.getNumero_torre()); // Cambiado a camelCase
            pstmt.setInt(3, torre.getNumeroApartamentos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear torre: " + e.getMessage());
        }
    }

    public List<Torre> obtenerTorres() {
        String sql = "SELECT * FROM Torre"; // Asegúrate de que la tabla y los nombres de las columnas sean correctos
        List<Torre> torres = new ArrayList<>();
        try (Connection conn = conexion.connect(); // Asegúrate de que el método connect() sea correcto
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Torre torre = new Torre();
                torre.setIdTorre(rs.getInt("ID_torre"));
                torre.setIdProyecto(rs.getInt("ID_proyecto"));
                torre.setNumero_torre(rs.getInt("Numero_torre")); // Cambiado a camelCase
                torre.setNumeroApartamentos(rs.getInt("numeroApartamentos"));
                torres.add(torre);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener torres: " + e.getMessage());
        }
        return torres;
    }

    public void actualizarTorre(Torre torre) {
        String sql = "UPDATE Torre SET ID_proyecto = ?, Numero_torre = ?, numeroApartamentos = ? WHERE ID_torre = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torre.getIdProyecto());
            pstmt.setInt(2, torre.getNumero_torre()); // Cambiado a camelCase
            pstmt.setInt(3, torre.getNumeroApartamentos());
            pstmt.setInt(4, torre.getIdTorre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar torre: " + e.getMessage());
        }
    }

    public void eliminarTorre(int idTorre) {
        String sql = "DELETE FROM Torre WHERE ID_torre = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTorre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar torre: " + e.getMessage());
        }
    }
}
