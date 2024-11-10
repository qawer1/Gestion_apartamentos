package dao;

import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para conectar a la base de datos
    private Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia la URL según tu configuración
            String username = "SYSTEM"; // Cambia a tu usuario de Oracle
            String password = "Case18283022"; // Cambia a tu contraseña de Oracle
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método para registrar un usuario
    public void registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (id, nombre, contrasena, rol) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuario.getId());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getRol());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + usuario.getNombre());
            e.printStackTrace();
        }
    }

    // Método para validar un usuario por nombre y contraseña
    public boolean validarUsuario(String nombre, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE nombre = ? AND contrasena = ?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // Si hay resultados, las credenciales son válidas
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + nombre);
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios");
            e.printStackTrace();
        }
        return usuarios;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombre = ?, contrasena = ?, rol = ? WHERE id = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getContrasena());
            pstmt.setString(3, usuario.getRol());
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario con ID: " + usuario.getId());
            e.printStackTrace();
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario con ID: " + id);
            e.printStackTrace();
        }
    }
}
