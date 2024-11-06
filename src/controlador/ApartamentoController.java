// ApartamentoController.java
package controlador;

import dao.ApartamentoDAO;
import modelo.Apartamento;

import java.util.List;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController() {
        apartamentoDAO = new ApartamentoDAO();
    }

    public void crearApartamento(int id_torre, int numero_apartamento, double valorApartamento, String tipoUnidad, double area, String matricula, String id_vendedor, String fechaEscritura) {
        Apartamento apartamento = new Apartamento();
        apartamento.setID_torre(id_torre);
        apartamento.setNumero_apartamento(numero_apartamento);
        apartamento.setValorApartamento(valorApartamento);
        apartamento.setTipoUnidad(tipoUnidad);
        apartamento.setArea(area);
        apartamento.setMatricula(matricula);
        apartamento.setId_vendedor(id_vendedor);
        apartamento.setFechaEscritura(fechaEscritura);
        apartamentoDAO.crearApartamento(apartamento);
    }

    public List<Apartamento> obtenerApartamentos() {
        return apartamentoDAO.obtenerApartamentos();
    }

    public void actualizarApartamento(int id_apartamento, int id_torre, int numero_apartamento, double valorApartamento, String tipoUnidad, double area, String matricula, String id_vendedor, String fechaEscritura) {
        Apartamento apartamento = new Apartamento();
        apartamento.setID_apartamento(id_apartamento);
        apartamento.setID_torre(id_torre);
        apartamento.setNumero_apartamento(numero_apartamento);
        apartamento.setValorApartamento(valorApartamento);
        apartamento.setTipoUnidad(tipoUnidad);
        apartamento.setArea(area);
        apartamento.setMatricula(matricula);
        apartamento.setId_vendedor(id_vendedor);
        apartamento.setFechaEscritura(fechaEscritura);
        apartamentoDAO.actualizarApartamento(apartamento);
    }
    
    public String listarApartamentos() {
        List<Apartamento> apartamentos = apartamentoDAO.obtenerApartamentos();
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Apartamentos:\n");
        for (Apartamento apartamento : apartamentos) {
            sb.append("ID Apartamento: ").append(apartamento.getID_apartamento())
              .append(", Torre: ").append(apartamento.getID_torre())
              .append(", Número: ").append(apartamento.getNumero_apartamento())
              .append(", Valor: ").append(apartamento.getValorApartamento())
              .append(", Tipo: ").append(apartamento.getTipoUnidad())
              .append(", Área: ").append(apartamento.getArea())
              .append(", Matrícula: ").append(apartamento.getMatricula())
              .append(", Vendedor: ").append(apartamento.getId_vendedor())
              .append(", Fecha de Escritura: ").append(apartamento.getFechaEscritura())
              .append("\n");
        }
        return sb.toString();
    }

    public void eliminarApartamento(int id_apartamento) {
        apartamentoDAO.eliminarApartamento(id_apartamento);
    }
}
