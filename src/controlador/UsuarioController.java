package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    // Constructor que inicializa el DAO de Usuario
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método para crear un usuario
    public void crearUsuario(int id, String nombre, String contrasena, String rol) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuarioDAO.registrarUsuario(usuario);
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    // Método para listar todos los usuarios
    public void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Rol: " + usuario.getRol());
            }
        }
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(int id, String nombre, String contrasena, String rol) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuarioDAO.actualizarUsuario(usuario);
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    // Método para validar un usuario
    public boolean validarUsuario(String nombre, String contrasena) {
        return usuarioDAO.validarUsuario(nombre, contrasena);
    }

    // Método para obtener el rol de un usuario
    public String obtenerRolUsuario(String nombre) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombre);  // Suponemos que este método existe en el DAO.
        return usuario != null ? usuario.getRol() : null;
    }
}
