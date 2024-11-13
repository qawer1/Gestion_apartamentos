package controlador;

import dao.ReporteDAO;
import modelo.Venta;
import modelo.Pago;
import java.util.List;

public class ReporteController {
    private ReporteDAO reporteDAO;

    public ReporteController() {
        this.reporteDAO = new ReporteDAO();
    }

    // Obtener todas las ventas
    public List<Venta> obtenerReporteVentas() {
        return reporteDAO.obtenerReporteVentas();
    }

    // Obtener todos los pagos
    public List<Pago> obtenerReportePagos() {
        return reporteDAO.obtenerReportePagos();
    }

    // Obtener las ventas de un cliente específico por su cédula
    public List<Venta> obtenerVentasPorCliente(int cedulaCliente) {
        return reporteDAO.obtenerVentasPorCliente(cedulaCliente);
    }

    // Obtener los pagos de un cliente específico por su cédula
    public List<Pago> obtenerPagosPorCliente(int cedulaCliente) {
        return reporteDAO.obtenerPagosPorCliente(cedulaCliente);
    }

    // Obtener el total pagado por un cliente para un apartamento
    public double obtenerTotalPagadoPorCliente(int cedulaCliente, int idApartamento) {
        return reporteDAO.obtenerTotalPagadoPorCliente(cedulaCliente, idApartamento);
    }

    // Obtener la cantidad restante por pagar por un cliente para un apartamento
    public double obtenerCantidadRestantePorPagar(int cedulaCliente, int idApartamento) {
        double totalPagado = obtenerTotalPagadoPorCliente(cedulaCliente, idApartamento);
        double precioTotal = reporteDAO.obtenerPrecioTotalApartamento(idApartamento);
        return precioTotal - totalPagado;
    }
}
