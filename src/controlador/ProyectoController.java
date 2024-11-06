package controlador;

import dao.ProyectoDAO;
import modelo.Proyecto;
import java.util.List;

public class ProyectoController {
    private ProyectoDAO proyectoDAO;

    // Constructor que inicializa el DAO de Proyecto
    public ProyectoController() {
        this.proyectoDAO = new ProyectoDAO();
    }

    // Método para crear un proyecto
    public void crearProyecto(String nombre, int numeroTorres) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombre);
        proyecto.setNumeroTorres(numeroTorres);
        proyectoDAO.crearProyecto(proyecto);
        System.out.println("Proyecto creado exitosamente.");
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() {
        return proyectoDAO.obtenerProyectos();
    }

    // Método para listar todos los proyectos de manera estructurada
    public String listarProyectos() {
        List<Proyecto> proyectos = proyectoDAO.obtenerProyectos();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Proyectos:\n");

        if (proyectos.isEmpty()) {
            sb.append("No hay proyectos registrados.\n");
        } else {
            for (Proyecto proyecto : proyectos) {
                sb.append("ID Proyecto: ").append(proyecto.getIdProyecto())
                  .append(", Nombre: ").append(proyecto.getNombre())
                  .append(", Número de Torres: ").append(proyecto.getNumeroTorres())
                  .append("\n");
            }
        }

        System.out.println(sb.toString());  // Depuración para verificar los proyectos en la consola
        
        return sb.toString();
    }

    // Método para actualizar un proyecto
    public void actualizarProyecto(int idProyecto, String nombre, int numeroTorres) {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(idProyecto);
        proyecto.setNombre(nombre);
        proyecto.setNumeroTorres(numeroTorres);
        proyectoDAO.actualizarProyecto(proyecto);
        System.out.println("Proyecto actualizado exitosamente.");
    }

    // Método para eliminar un proyecto
    public void eliminarProyecto(int idProyecto) {
        proyectoDAO.eliminarProyecto(idProyecto);
        System.out.println("Proyecto eliminado exitosamente.");
    }
}
