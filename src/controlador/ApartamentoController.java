package controlador;

import dao.ApartamentoDAO;
import modelo.Apartamento;

import java.util.List;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController() {
        apartamentoDAO = new ApartamentoDAO();
    }

    // Método para crear un apartamento, ahora incluye estadoVenta como parámetro
    public void crearApartamento(int id_apartamento, int id_torre, int numero_apartamento, double valorApartamento, String tipoUnidad, double area, String matricula, String estadoVenta) {
        Apartamento apartamento = new Apartamento();
        apartamento.setID_apartamento(id_apartamento); // Asigna el ID manualmente
        apartamento.setID_torre(id_torre);
        apartamento.setNumero_apartamento(numero_apartamento);
        apartamento.setValorApartamento(valorApartamento);
        apartamento.setTipoUnidad(tipoUnidad);
        apartamento.setArea(area);
        apartamento.setMatricula(matricula);
        apartamento.setEstadoVenta(estadoVenta);  // Asigna el estadoVenta
        apartamentoDAO.crearApartamento(apartamento);
    }

    // Método para obtener todos los apartamentos
    public List<Apartamento> obtenerApartamentos() {
        return apartamentoDAO.obtenerApartamentos();
    }

    // Método para actualizar un apartamento, ahora incluye estadoVenta como parámetro
    public void actualizarApartamento(int id_apartamento, int id_torre, int numero_apartamento, double valorApartamento, String tipoUnidad, double area, String matricula, String estadoVenta) {
        Apartamento apartamento = new Apartamento();
        apartamento.setID_apartamento(id_apartamento); // Asigna el ID para actualizar el registro correcto
        apartamento.setID_torre(id_torre);
        apartamento.setNumero_apartamento(numero_apartamento);
        apartamento.setValorApartamento(valorApartamento);
        apartamento.setTipoUnidad(tipoUnidad);
        apartamento.setArea(area);
        apartamento.setMatricula(matricula);
        apartamento.setEstadoVenta(estadoVenta);  // Actualiza el estadoVenta
        apartamentoDAO.actualizarApartamento(apartamento);
    }

    // Método para listar los apartamentos con su estadoVenta
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
              .append(", Estado de Venta: ").append(apartamento.getEstadoVenta())  // Muestra el estadoVenta
              .append("\n");
        }
        return sb.toString();
    }

    // Método para eliminar un apartamento
    public void eliminarApartamento(int id_apartamento) {
        apartamentoDAO.eliminarApartamento(id_apartamento);
    }
}
