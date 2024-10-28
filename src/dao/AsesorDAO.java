package dao;

import modelo.Asesor;
import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AsesorDAO {
    // Elimina la variable connection, ya que obtendrás la conexión directamente en cada método
    public void crearAsesor(Asesor asesor) {
        String sql = "INSERT INTO Asesor (cedula, nombre, direccion, telefono, Correo_electronico) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = conexion.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, asesor.getCedula());
            statement.setString(2, asesor.getNombre());
            statement.setString(3, asesor.getDireccion());
            statement.setString(4, asesor.getTelefono());
            statement.setString(5, asesor.getCorreo_electronico());
            statement.executeUpdate();
            System.out.println("Asesor creado con exito.");
        } catch (SQLException e) {
            System.out.println("Error al crear el asesor: " + e.getMessage());
        }
    }

    public List<Asesor> listarAsesores() {
        List<Asesor> asesores = new ArrayList<>();
        String sql = "SELECT * FROM Asesor";
        try (Connection connection = conexion.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Asesor asesor = new Asesor();
                asesor.setCedula(resultSet.getInt("cedula"));
                asesor.setNombre(resultSet.getString("nombre"));
                asesor.setDireccion(resultSet.getString("direccion"));
                asesor.setTelefono(resultSet.getString("telefono"));
                asesor.setCorreo_electronico(resultSet.getString("Correo_electronico"));
                asesores.add(asesor);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar asesores: " + e.getMessage());
        }
        return asesores;
    }

    public void actualizarAsesor(Asesor asesor) {
        String sql = "UPDATE Asesor SET nombre = ?, direccion = ?, telefono = ?, Correo_electronico = ? WHERE cedula = ?";
        try (Connection connection = conexion.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, asesor.getNombre());
            statement.setString(2, asesor.getDireccion());
            statement.setString(3, asesor.getTelefono());
            statement.setString(4, asesor.getCorreo_electronico());
            statement.setInt(5, asesor.getCedula());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Asesor actualizado con éxito.");
            } else {
                System.out.println("No se encontro un asesor con la cedula proporcionada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el asesor: " + e.getMessage());
        }
    }

    public void eliminarAsesor(int cedula) {
        String sql = "DELETE FROM Asesor WHERE cedula = ?";
        try (Connection connection = conexion.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cedula);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Asesor eliminado con éxito.");
            } else {
                System.out.println("No se encontro un asesor con la cedula proporcionada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el asesor: " + e.getMessage());
        }
    }
}
