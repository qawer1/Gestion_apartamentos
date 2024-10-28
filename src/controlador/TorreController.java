// TorreController.java
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
        torre.setNumero_torre(numeroTorre);
        torre.setNumeroApartamentos(numeroApartamentos);
        torreDAO.crearTorre(torre);
        System.out.println("Torre creada exitosamente.");
    }

    public void listarTorres() {
        List<Torre> torres = torreDAO.obtenerTorres();
        for (Torre torre : torres) {
            System.out.println(torre);
        }
    }

    public void actualizarTorre(int idTorre, int idProyecto, int Numero_torre, int numeroApartamentos) {
        Torre torre = new Torre(idTorre, idProyecto, Numero_torre, numeroApartamentos);
        torreDAO.actualizarTorre(torre);
        System.out.println("Torre actualizada exitosamente.");
    }

    public void eliminarTorre(int idTorre) {
        torreDAO.eliminarTorre(idTorre);
        System.out.println("Torre eliminada exitosamente.");
    }
}
