package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Proyecto;

public class ProyectoDAO {

    // Método para conectar con la base de datos
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

    // Método para insertar un proyecto
    public void insertarProyecto(Proyecto proyecto) {
        String sql = "INSERT INTO Proyecto(nombre, numeroTorres) VALUES(?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proyecto.getNombre());
            pstmt.setInt(2, proyecto.getNumeroTorres());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM Proyecto";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Proyecto proyecto = new Proyecto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("numeroTorres")
                );
                proyectos.add(proyecto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return proyectos;
    }

    // Método para actualizar un proyecto
    public void actualizarProyecto(Proyecto proyecto) {
        String sql = "UPDATE Proyecto SET nombre = ?, numeroTorres = ? WHERE id = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proyecto.getNombre());
            pstmt.setInt(2, proyecto.getNumeroTorres());
            pstmt.setInt(3, proyecto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un proyecto
    public void eliminarProyecto(int id) {
        String sql = "DELETE FROM Proyecto WHERE id = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
