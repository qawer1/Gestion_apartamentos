// ProyectoDAO.java
package dao;

import conexion.conexion;
import modelo.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {

    public void crearProyecto(Proyecto proyecto) {
        String sql = "INSERT INTO Proyecto (nombre, numeroTorres) VALUES (?, ?)";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proyecto.getNombre());
            pstmt.setInt(2, proyecto.getNumeroTorres());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear proyecto: " + e.getMessage());
        }
    }

    public List<Proyecto> obtenerProyectos() {
        String sql = "SELECT * FROM Proyecto";
        List<Proyecto> proyectos = new ArrayList<>();
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setIdProyecto(rs.getInt("ID_proyecto")); // Cambiado a 'ID_proyecto'
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setNumeroTorres(rs.getInt("numeroTorres"));
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener proyectos: " + e.getMessage());
        }
        return proyectos;
    }

    public void actualizarProyecto(Proyecto proyecto) {
        String sql = "UPDATE Proyecto SET nombre = ?, numeroTorres = ? WHERE ID_proyecto = ?"; // Cambiado a 'ID_proyecto'
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proyecto.getNombre());
            pstmt.setInt(2, proyecto.getNumeroTorres());
            pstmt.setInt(3, proyecto.getIdProyecto());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar proyecto: " + e.getMessage());
        }
    }

    public void eliminarProyecto(int idProyecto) {
        String sql = "DELETE FROM Proyecto WHERE ID_proyecto = ?"; // Cambiado a 'ID_proyecto'
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProyecto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar proyecto: " + e.getMessage());
        }
    }
}
