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

    public List<Venta> obtenerReporteVentas() {
        return reporteDAO.obtenerReporteVentas();
    }

    public List<Pago> obtenerReportePagos() {
        return reporteDAO.obtenerReportePagos();
    }
}