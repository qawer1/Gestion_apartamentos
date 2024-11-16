package dao;

import conexion.conexion;
import modelo.Torre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TorreDAO {

    // Método para crear una torre, incluyendo el ID_TORRE
    public void crearTorre(Torre torre) {
        String sql = "INSERT INTO Torre (ID_TORRE, ID_PROYECTO, NUMERO_TORRE, NUMEROAPARTAMENTOS) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, torre.getIdTorre()); // Ahora se incluye el ID_TORRE
            pstmt.setInt(2, torre.getIdProyecto());
            pstmt.setInt(3, torre.getNumero_torre());
            pstmt.setInt(4, torre.getNumeroApartamentos());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al crear torre: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }

    // Método para obtener todas las torres
    public List<Torre> obtenerTorres() {
        List<Torre> torres = new ArrayList<>();
        String sql = "SELECT ID_TORRE, ID_PROYECTO, NUMERO_TORRE, NUMEROAPARTAMENTOS FROM Torre";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Torre torre = new Torre();
                torre.setIdTorre(rs.getInt("ID_TORRE"));
                torre.setIdProyecto(rs.getInt("ID_PROYECTO"));
                torre.setNumero_torre(rs.getInt("NUMERO_TORRE"));
                torre.setNumeroApartamentos(rs.getInt("NUMEROAPARTAMENTOS"));
                torres.add(torre);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener torres");
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return torres;
    }

    // Método para actualizar una torre
    public void actualizarTorre(Torre torre) {
        String sql = "UPDATE Torre SET ID_PROYECTO = ?, NUMERO_TORRE = ?, NUMEROAPARTAMENTOS = ? WHERE ID_TORRE = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, torre.getIdProyecto());
            pstmt.setInt(2, torre.getNumero_torre());
            pstmt.setInt(3, torre.getNumeroApartamentos());
            pstmt.setInt(4, torre.getIdTorre());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar torre con ID: " + torre.getIdTorre());
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }

    // Método para eliminar una torre
    public void eliminarTorre(int idTorre) {
        String sql = "DELETE FROM Torre WHERE ID_TORRE = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTorre);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar torre con ID: " + idTorre);
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }
}
