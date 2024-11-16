package dao;

import conexion.conexion;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para registrar un usuario
    public void registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (id, nombre, contrasena, rol) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuario.getId());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getRol());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + usuario.getNombre());
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }

    // Método para validar un usuario por nombre y contraseña
    public boolean validarUsuario(String nombre, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE nombre = ? AND contrasena = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // Si hay resultados, las credenciales son válidas
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + nombre);
            e.printStackTrace();
            return false;
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios");
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return usuarios;
    }

    // Método para obtener un usuario por nombre
    public Usuario obtenerUsuarioPorNombre(String nombre) {
        String sql = "SELECT * FROM Usuario WHERE nombre = ?";
        Usuario usuario = null;
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario por nombre: " + nombre);
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return usuario;
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombre = ?, contrasena = ?, rol = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getContrasena());
            pstmt.setString(3, usuario.getRol());
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario con ID: " + usuario.getId());
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario con ID: " + id);
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
    }
}
