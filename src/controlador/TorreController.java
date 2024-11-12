package controlador;

import dao.TorreDAO;
import modelo.Torre;
import java.util.List;

public class TorreController {
    private TorreDAO torreDAO;

    // Constructor que inicializa el DAO de Torre
    public TorreController() {
        this.torreDAO = new TorreDAO();
    }

    // Método para crear una torre, incluyendo ID_TORRE
    public void crearTorre(int idTorre, int idProyecto, int numeroTorre, int numeroApartamentos) {
        Torre torre = new Torre();
        torre.setIdTorre(idTorre); // Ahora se incluye el ID_TORRE
        torre.setIdProyecto(idProyecto);
        torre.setNumero_torre(numeroTorre);
        torre.setNumeroApartamentos(numeroApartamentos);
        torreDAO.crearTorre(torre);
        System.out.println("Torre creada exitosamente.");
    }

    // Método para obtener todas las torres
    public List<Torre> obtenerTorres() {
        return torreDAO.obtenerTorres();
    }

    // Método para listar todas las torres de manera estructurada
    public String listarTorres() {
        List<Torre> torres = torreDAO.obtenerTorres();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Torres:\n");

        if (torres.isEmpty()) {
            sb.append("No hay torres registradas.\n");
        } else {
            for (Torre torre : torres) {
                sb.append("ID Torre: ").append(torre.getIdTorre())
                  .append(", ID Proyecto: ").append(torre.getIdProyecto())
                  .append(", Número de Torre: ").append(torre.getNumero_torre())
                  .append(", Número de Apartamentos: ").append(torre.getNumeroApartamentos())
                  .append("\n");
            }
        }
        return sb.toString();
    }

    // Método para actualizar una torre
    public void actualizarTorre(int idTorre, int idProyecto, int numeroTorre, int numeroApartamentos) {
        Torre torre = new Torre();
        torre.setIdTorre(idTorre);
        torre.setIdProyecto(idProyecto);
        torre.setNumero_torre(numeroTorre);
        torre.setNumeroApartamentos(numeroApartamentos);
        torreDAO.actualizarTorre(torre);
        System.out.println("Torre actualizada exitosamente.");
    }

    // Método para eliminar una torre
    public void eliminarTorre(int idTorre) {
        torreDAO.eliminarTorre(idTorre);
        System.out.println("Torre eliminada exitosamente.");
    }
}
