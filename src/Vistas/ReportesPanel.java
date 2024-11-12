package vistas;

import controlador.ReporteController;
import modelo.Venta;
import modelo.Pago;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportesPanel extends JPanel {
    private JTextArea txtReporte;
    private ReporteController reporteController;

    public ReportesPanel() {
        reporteController = new ReporteController();
        setLayout(new BorderLayout());

        JButton btnVentas = new JButton("Generar Reporte de Ventas");
        JButton btnPagos = new JButton("Generar Reporte de Pagos");
        JButton btnExportarPDF = new JButton("Exportar a PDF");
        txtReporte = new JTextArea(20, 50);
        txtReporte.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnVentas);
        buttonPanel.add(btnPagos);
        buttonPanel.add(btnExportarPDF);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(txtReporte), BorderLayout.CENTER);

        btnVentas.addActionListener(e -> mostrarReporteVentas());
        btnPagos.addActionListener(e -> mostrarReportePagos());
        btnExportarPDF.addActionListener(e -> exportarReportePDF());
    }

    private void mostrarReporteVentas() {
        List<Venta> ventas = reporteController.obtenerReporteVentas();
        txtReporte.setText("");
        for (Venta venta : ventas) {
            txtReporte.append("ID Venta: " + venta.getIdVenta() + 
                              ", Precio Total: " + venta.getPrecioTotal() + 
                              ", Cuotas: " + venta.getNumeroCuotas() + 
                              ", Intereses: " + venta.getIntereses() + 
                              ", Cliente: " + venta.getIdCliente() + 
                              ", Apartamento: " + venta.getIdApartamento() + "\n");
        }
    }

    private void mostrarReportePagos() {
        List<Pago> pagos = reporteController.obtenerReportePagos();
        txtReporte.setText("");
        for (Pago pago : pagos) {
            txtReporte.append("ID Pago: " + pago.getID_Pago() + 
                              ", Valor: " + pago.getValorPago() + 
                              ", Fecha: " + pago.getFecha() + 
                              ", Cliente: " + pago.getCedula_cliente() + 
                              ", Asesor: " + pago.getCedula_asesor() + "\n");
        }
    }

    private void exportarReportePDF() {
        Document documento = new Document();
        try {
            // Crear el archivo PDF y asociarlo con un FileOutputStream
            PdfWriter.getInstance(documento, new FileOutputStream("reporte.pdf"));
            documento.open();

            // Agregar el contenido del JTextArea al PDF como párrafo
            documento.add(new Paragraph(txtReporte.getText()));

            JOptionPane.showMessageDialog(this, "Reporte exportado a PDF exitosamente.");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar el reporte a PDF.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            documento.close();
        }
    }
}
