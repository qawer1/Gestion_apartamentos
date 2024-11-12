package dao;

import modelo.Reporte;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class ReporteDAO {

    private Connection conectar() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "SYSTEM";
        String password = "Case18283022";
        return DriverManager.getConnection(url, username, password);
    }

    public Reporte generarReporte(Integer clienteId, Integer asesorId) {
        int totalVentas = 0;
        int totalPagos = 0;
        double totalIngresos = 0.0;
        double totalGastos = 0.0;

        String sqlVentas = "SELECT COUNT(*) AS total_ventas, SUM(PRECIO_TOTAL) AS ingresos FROM Venta WHERE CEDULA_CLIENTES = ? OR ID_ASESOR = ?";
        String sqlPagos = "SELECT COUNT(*) AS total_pagos, SUM(VALORPAGO) AS gastos FROM Pago WHERE CEDULA_CLIENTE = ? OR CEDULA_ASESOR = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmtVentas = conn.prepareStatement(sqlVentas);
             PreparedStatement pstmtPagos = conn.prepareStatement(sqlPagos)) {

            // Consulta para ventas
            pstmtVentas.setInt(1, clienteId);
            pstmtVentas.setInt(2, asesorId);
            ResultSet rsVentas = pstmtVentas.executeQuery();
            if (rsVentas.next()) {
                totalVentas = rsVentas.getInt("total_ventas");
                totalIngresos = rsVentas.getDouble("ingresos");
            }

            // Consulta para pagos
            pstmtPagos.setInt(1, clienteId);
            pstmtPagos.setInt(2, asesorId);
            ResultSet rsPagos = pstmtPagos.executeQuery();
            if (rsPagos.next()) {
                totalPagos = rsPagos.getInt("total_pagos");
                totalGastos = rsPagos.getDouble("gastos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Reporte(totalVentas, totalPagos, totalIngresos, totalGastos);
    }

    public void generarReportePDF(Reporte reporte, String rutaArchivo) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte Consolidado de Ventas y Pagos", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));  // Espacio en blanco

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            table.addCell("Total Ventas");
            table.addCell(String.valueOf(reporte.getTotalVentas()));
            table.addCell("Total Ingresos");
            table.addCell(String.format("%.2f", reporte.getTotalIngresos()));
            table.addCell("Total Pagos");
            table.addCell(String.valueOf(reporte.getTotalPagos()));
            table.addCell("Total Gastos");
            table.addCell(String.format("%.2f", reporte.getTotalGastos()));

            document.add(table);

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar reporte en PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
