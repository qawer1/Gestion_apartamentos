package controlador;

import modelo.Torre;
import modelo.dao.TorreDAO;
import java.util.List;

public class TorreController {
    private TorreDAO torreDAO;

    public TorreController() {
        this.torreDAO = new TorreDAO();
    }

    public void agregarTorre(Torre torre) {
        torreDAO.insertarTorre(torre);
        System.out.println("Torre añadida exitosamente.");
    }

    public void eliminarTorre(int id) {
        torreDAO.eliminarTorre(id);
        System.out.println("Torre eliminada exitosamente.");
    }

    public List<Torre> obtenerTorres(int proyectoId) {
        return torreDAO.obtenerTorresPorProyecto(proyectoId);
    }

    public void actualizarTorre(Torre torre) {
        torreDAO.actualizarTorre(torre);
        System.out.println("Torre actualizada exitosamente.");
    }
}
