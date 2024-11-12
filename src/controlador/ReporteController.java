package controlador;

import dao.ReporteDAO;
import modelo.Reporte;
import java.util.ArrayList;
import java.util.List;

public class ReporteController {
    private ReporteDAO reporteDAO;
    private List<Reporte> historialReportes;

    public ReporteController() {
        this.reporteDAO = new ReporteDAO();
        this.historialReportes = new ArrayList<>();
    }

    public Reporte generarReporte(Integer clienteId, Integer asesorId) {
        // Validación de parámetros
        if (clienteId != null && clienteId < 0) {
            throw new IllegalArgumentException("El ID del cliente no puede ser negativo.");
        }
        if (asesorId != null && asesorId < 0) {
            throw new IllegalArgumentException("El ID del asesor no puede ser negativo.");
        }
        try {
            Reporte reporte = reporteDAO.generarReporte(clienteId, asesorId);
            if (reporte != null) {
                historialReportes.add(reporte);
            }
            return reporte;
        } catch (Exception e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
            return null;
        }
    }

    public String obtenerReporteFormateado(Integer clienteId, Integer asesorId) {
        Reporte reporte = generarReporte(clienteId, asesorId);
        if (reporte == null) {
            return "No se pudo generar el reporte.";
        }
        return String.format("Total Ventas: %.2f\nTotal Pagos: %.2f\n", 
                             reporte.getTotalVentas(), 
                             reporte.getTotalPagos());
    }

    public List<Reporte> obtenerHistorialReportes() {
        return historialReportes;
    }
}
