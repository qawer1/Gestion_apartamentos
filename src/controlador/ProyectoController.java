// ProyectoController.java
package controlador;

import dao.ProyectoDAO;
import modelo.Proyecto;
import java.util.List;

public class ProyectoController {
    private ProyectoDAO proyectoDAO;

    public ProyectoController() {
        this.proyectoDAO = new ProyectoDAO();
    }

    public void crearProyecto(String nombre, int numeroTorres) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombre);
        proyecto.setNumeroTorres(numeroTorres);
        proyectoDAO.crearProyecto(proyecto);
        System.out.println("Proyecto creado exitosamente.");
    }

    public void listarProyectos() {
        List<Proyecto> proyectos = proyectoDAO.obtenerProyectos();
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

    public void actualizarProyecto(int idProyecto, String nombre, int numeroTorres) {
        Proyecto proyecto = new Proyecto(idProyecto, nombre, numeroTorres);
        proyectoDAO.actualizarProyecto(proyecto);
        System.out.println("Proyecto actualizado exitosamente.");
    }

    public void eliminarProyecto(int idProyecto) {
        proyectoDAO.eliminarProyecto(idProyecto);
        System.out.println("Proyecto eliminado exitosamente.");
    }
}
