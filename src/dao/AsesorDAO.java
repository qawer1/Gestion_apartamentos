// AsesorDAO.java
package dao;

import modelo.Asesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsesorDAO {

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

    // Método para crear un asesor
    public void crearAsesor(Asesor asesor) {
        String sql = "INSERT INTO Asesor (cedula, nombre, direccion, telefono, Correo_electronico) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, asesor.getCedula());
            pstmt.setString(2, asesor.getNombre());
            pstmt.setString(3, asesor.getDireccion());
            pstmt.setString(4, asesor.getTelefono());
            pstmt.setString(5, asesor.getCorreo_electronico());
            pstmt.executeUpdate();
            System.out.println("Asesor creado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al crear el asesor: " + e.getMessage());
        }
    }

    // Método para obtener todos los asesores
    public List<Asesor> obtenerAsesores() {
        List<Asesor> asesores = new ArrayList<>();
        String sql = "SELECT cedula, nombre, direccion, telefono, Correo_electronico FROM Asesor";
        
        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asesor asesor = new Asesor();
                asesor.setCedula(rs.getInt("cedula"));
                asesor.setNombre(rs.getString("nombre"));
                asesor.setDireccion(rs.getString("direccion"));
                asesor.setTelefono(rs.getString("telefono"));
                asesor.setCorreo_electronico(rs.getString("Correo_electronico"));
                asesores.add(asesor);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener asesores: " + e.getMessage());
        }
        return asesores;
    }

    // Método para actualizar un asesor
    public void actualizarAsesor(Asesor asesor) {
        String sql = "UPDATE Asesor SET nombre = ?, direccion = ?, telefono = ?, Correo_electronico = ? WHERE cedula = ?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, asesor.getNombre());
            pstmt.setString(2, asesor.getDireccion());
            pstmt.setString(3, asesor.getTelefono());
            pstmt.setString(4, asesor.getCorreo_electronico());
            pstmt.setInt(5, asesor.getCedula());
            pstmt.executeUpdate();
            System.out.println("Asesor actualizado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el asesor con cédula: " + asesor.getCedula());
            e.printStackTrace();
        }
    }

    // Método para eliminar un asesor
    public void eliminarAsesor(int cedula) {
        String sql = "DELETE FROM Asesor WHERE cedula = ?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cedula);
            pstmt.executeUpdate();
            System.out.println("Asesor eliminado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el asesor con cédula: " + cedula);
            e.printStackTrace();
        }
    }
}
