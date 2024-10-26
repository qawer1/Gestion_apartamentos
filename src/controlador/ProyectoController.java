package controlador;

import modelo.Proyecto;
import modelo.dao.ProyectoDAO;
import java.util.List;

public class ProyectoController {
    private ProyectoDAO proyectoDAO;

    public ProyectoController() {
        this.proyectoDAO = new ProyectoDAO();
    }

    public void agregarProyecto(Proyecto proyecto) {
        proyectoDAO.insertarProyecto(proyecto);
        System.out.println("Proyecto añadido exitosamente.");
    }

    public void eliminarProyecto(int id) {
        proyectoDAO.eliminarProyecto(id);
        System.out.println("Proyecto eliminado exitosamente.");
    }

    public List<Proyecto> obtenerProyectos() {
        return proyectoDAO.obtenerProyectos();
    }

    public void actualizarProyecto(Proyecto proyecto) {
        proyectoDAO.actualizarProyecto(proyecto);
        System.out.println("Proyecto actualizado exitosamente.");
    }
}
