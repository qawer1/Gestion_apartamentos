package controlador;

import modelo.Apartamento;
import modelo.dao.ApartamentoDAO;
import java.util.List;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController() {
        this.apartamentoDAO = new ApartamentoDAO();
    }

    public void agregarApartamento(Apartamento apartamento) {
        apartamentoDAO.insertarApartamento(apartamento);
        System.out.println("Apartamento añadido exitosamente.");
    }

    public void eliminarApartamento(int id) {
        apartamentoDAO.eliminarApartamento(id);
        System.out.println("Apartamento eliminado exitosamente.");
    }

    public List<Apartamento> obtenerApartamentos(int torreId) {
        return apartamentoDAO.obtenerApartamentosPorTorre(torreId);
    }

    public void actualizarApartamento(Apartamento apartamento) {
        apartamentoDAO.actualizarApartamento(apartamento);
        System.out.println("Apartamento actualizado exitosamente.");
    }
}
