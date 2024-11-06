package controlador;

import dao.TorreDAO;
import modelo.Torre;

import java.util.List;

public class TorreController {
    private TorreDAO torreDAO;

    public TorreController() {
        this.torreDAO = new TorreDAO();
    }

    public void crearTorre(int idProyecto, int numeroTorre, int numeroApartamentos) {
        Torre torre = new Torre();
        torre.setIdProyecto(idProyecto);
        torre.setNumero_torre(numeroTorre); // Cambiado a camelCase
        torre.setNumeroApartamentos(numeroApartamentos);
        torreDAO.crearTorre(torre);
        System.out.println("Torre creada exitosamente.");
    }

    public List<Torre> listarTorres() {
        return torreDAO.obtenerTorres();
    }

    public void actualizarTorre(int idTorre, int idProyecto, int numeroTorre, int numeroApartamentos) {
        Torre torre = new Torre(idTorre, idProyecto, numeroTorre, numeroApartamentos); // Cambiado a camelCase
        torreDAO.actualizarTorre(torre);
        System.out.println("Torre actualizada exitosamente.");
    }

    public void eliminarTorre(int idTorre) {
        torreDAO.eliminarTorre(idTorre);
        System.out.println("Torre eliminada exitosamente.");
    }
}
