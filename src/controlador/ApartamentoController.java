// ApartamentoController.java
package controlador;

import dao.ApartamentoDAO;
import modelo.Apartamento;

import java.util.List;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController() {
        this.apartamentoDAO = new ApartamentoDAO();
    }

    public void crearApartamento(int ID_torre, int Numero_apartamento, double valorApartamento, String tipoUnidad,
                                 double area, String matricula, String Id_vendedor, String fechaEscritura) {
        Apartamento apartamento = new Apartamento();
        apartamento.setID_torre(ID_torre);
        apartamento.setNumero_apartamento(Numero_apartamento);
        apartamento.setValorApartamento(valorApartamento);
        apartamento.setTipoUnidad(tipoUnidad);
        apartamento.setArea(area);
        apartamento.setMatricula(matricula);
        apartamento.setId_vendedor(Id_vendedor);
        apartamento.setFechaEscritura(fechaEscritura);
        apartamentoDAO.crearApartamento(apartamento);
    }

    public List<Apartamento> obtenerApartamentos() {
        return apartamentoDAO.obtenerApartamentos();
    }

    public void listarApartamentos() {
        List<Apartamento> apartamentos = obtenerApartamentos();
        if (apartamentos.isEmpty()) {
            System.out.println("No hay apartamentos registrados.");
        } else {
            System.out.println("Lista de Apartamentos:");
            for (Apartamento apartamento : apartamentos) {
                System.out.println("ID Apartamento: " + apartamento.getID_apartamento());
                System.out.println("ID Torre: " + apartamento.getID_torre());
                System.out.println("Número Apartamento: " + apartamento.getNumero_apartamento());
                System.out.println("Valor Apartamento: " + apartamento.getValorApartamento());
                System.out.println("Tipo de Unidad: " + apartamento.getTipoUnidad());
                System.out.println("Área: " + apartamento.getArea());
                System.out.println("Matrícula: " + apartamento.getMatricula());
                System.out.println("Id_vendedor: " + apartamento.getId_vendedor());
                System.out.println("Fecha de Escritura: " + apartamento.getFechaEscritura());
                System.out.println("------------------------");
            }
        }
    }

    public void actualizarApartamento(int ID_apartamento, int ID_torre, int Numero_apartamento, double valorApartamento,
                                      String tipoUnidad, double area, String matricula, String Id_vendedor, String fechaEscritura) {
        Apartamento apartamento = new Apartamento();
        apartamento.setID_apartamento(ID_apartamento);
        apartamento.setID_torre(ID_torre);
        apartamento.setNumero_apartamento(Numero_apartamento);
        apartamento.setValorApartamento(valorApartamento);
        apartamento.setTipoUnidad(tipoUnidad);
        apartamento.setArea(area);
        apartamento.setMatricula(matricula);
        apartamento.setId_vendedor(Id_vendedor);
        apartamento.setFechaEscritura(fechaEscritura);
        apartamentoDAO.actualizarApartamento(apartamento);
    }

    public void eliminarApartamento(int ID_apartamento) {
        apartamentoDAO.eliminarApartamento(ID_apartamento);
    }
}
