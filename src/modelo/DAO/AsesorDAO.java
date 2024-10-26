package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Asesor;

public class AsesorDAO {

    // Método para conectar con la base de datos
    private Connection conectar() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\Sebastian\\Downloads\\bd\\base de datos apartamentos.db"; // Cambia esta ruta si es necesario
        return DriverManager.getConnection(url);
    }

    // Método para insertar un asesor en la base de datos
    public void insertarAsesor(Asesor asesor) {
        String sql = "INSERT INTO Asesor(cedula, nombre, direccion, telefono, correo) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, asesor.getCedula());
            pstmt.setString(2, asesor.getNombre());
            pstmt.setString(3, asesor.getDireccion());
            pstmt.setString(4, asesor.getTelefono());
            pstmt.setString(5, asesor.getCorreo());
            pstmt.executeUpdate();
            System.out.println("Asesor insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar asesor: " + e.getMessage());
        }
    }

   

    // Método para obtener una lista de todos los asesores
    public List<Asesor> obtenerAsesores() {
        List<Asesor> asesores = new ArrayList<>();
        String sql = "SELECT * FROM Asesor";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asesor asesor = new Asesor(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                );
                asesores.add(asesor);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener asesores: " + e.getMessage());
        }

        return asesores;
    }

    // Método para actualizar la información de un asesor
    public void actualizarAsesor(Asesor asesor) {
        String sql = "UPDATE Asesor SET nombre = ?, direccion = ?, telefono = ?, correo = ? WHERE cedula = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, asesor.getNombre());
            pstmt.setString(2, asesor.getDireccion());
            pstmt.setString(3, asesor.getTelefono());
            pstmt.setString(4, asesor.getCorreo());
            pstmt.setString(5, asesor.getCedula());
            pstmt.executeUpdate();
            System.out.println("Asesor actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar asesor: " + e.getMessage());
        }
    }

    // Método para eliminar un asesor por su cédula
    public void eliminarAsesor(String cedula) {
        String sql = "DELETE FROM Asesor WHERE cedula = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            pstmt.executeUpdate();
            System.out.println("Asesor eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar asesor: " + e.getMessage());
        }
    }
}
