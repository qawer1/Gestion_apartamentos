package dao;

import modelo.Proyecto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {

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

    // Método para crear un proyecto
    public void crearProyecto(Proyecto proyecto) {
        String sql = "INSERT INTO Proyecto (ID_PROYECTO, nombre, numeroTorres) VALUES (?, ?, ?)"; // Agregado el campo ID_PROYECTO
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, proyecto.getIdProyecto()); // Ahora también insertamos el ID del proyecto
            pstmt.setString(2, proyecto.getNombre());
            pstmt.setInt(3, proyecto.getNumeroTorres());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear proyecto: " + e.getMessage());
        }
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT ID_PROYECTO, nombre, numeroTorres FROM Proyecto"; // Consultar solo los campos requeridos
        
        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setIdProyecto(rs.getInt("ID_PROYECTO"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setNumeroTorres(rs.getInt("numeroTorres"));
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener proyectos: " + e.getMessage());
        }
        return proyectos;
    }

    // Método para actualizar un proyecto
    public void actualizarProyecto(Proyecto proyecto) {
        String sql = "UPDATE Proyecto SET nombre = ?, numeroTorres = ? WHERE ID_PROYECTO = ?"; // Actualización solo de los campos relevantes
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proyecto.getNombre());
            pstmt.setInt(2, proyecto.getNumeroTorres());
            pstmt.setInt(3, proyecto.getIdProyecto());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar proyecto con ID: " + proyecto.getIdProyecto());
            e.printStackTrace();
        }
    }

    // Método para eliminar un proyecto
    public void eliminarProyecto(int idProyecto) {
        String sql = "DELETE FROM Proyecto WHERE ID_PROYECTO = ?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProyecto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar proyecto con ID: " + idProyecto);
            e.printStackTrace();
        }
    }
}
