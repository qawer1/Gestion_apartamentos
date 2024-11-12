package Vistas;

import controlador.VentaController;
import modelo.Venta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ReportesPanel extends JPanel {
    private VentaController ventaController;
    private JTextArea txtReporte;
    private JButton btnGenerarReporte;

    public ReportesPanel() {
        ventaController = new VentaController(); // Inicializar controlador de ventas

        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Generar Reporte de Ventas", SwingConstants.CENTER);
        

        // Botón para generar el reporte
        btnGenerarReporte = new JButton("Generar Reporte PDF");

        // Área de texto para mostrar el reporte generado o mensajes de éxito
        txtReporte = new JTextArea(10, 30);
        txtReporte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtReporte);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnGenerarReporte);

        // Añadir componentes al panel principal
        add(lblTitulo, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Acción para generar el reporte en PDF
        btnGenerarReporte.addActionListener(e -> {
            generarReportePDF();
        });
    }

    // Método para generar el reporte en PDF
    private void generarReportePDF() {
        try {
            // Crear documento PDF (iText 5)
            Document document = new Document();
            String path = "Reporte_Ventas.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Título del reporte sin especificar fuente (iText usa la predeterminada)
            document.add(new Paragraph("Reporte de Ventas"));
            document.add(new Paragraph("Fecha de generación: " + java.time.LocalDate.now()));
            document.add(new Paragraph("\n"));

            // Obtener las ventas
            List<Venta> ventas = ventaController.obtenerVentas();

            if (ventas.isEmpty()) {
                txtReporte.setText("No hay ventas registradas para generar el reporte.");
                return;
            }

            // Agregar la información de las ventas al documento
            for (Venta venta : ventas) {
                document.add(new Paragraph("ID Venta: " + venta.getIdVenta()));
                document.add(new Paragraph("Precio Total: " + venta.getPrecioTotal()));
                document.add(new Paragraph("Número de Cuotas: " + venta.getNumeroCuotas()));
                document.add(new Paragraph("Intereses: " + venta.getIntereses()));
                document.add(new Paragraph("ID Cliente: " + venta.getIdCliente()));
                document.add(new Paragraph("ID Apartamento: " + venta.getIdApartamento()));
                document.add(new Paragraph("\n"));
            }

            // Cerrar el documento PDF
            document.close();
            txtReporte.setText("Reporte PDF generado exitosamente en: " + path);
            JOptionPane.showMessageDialog(this, "Reporte PDF generado exitosamente.");

        } catch (FileNotFoundException | DocumentException ex) {
            txtReporte.setText("Error al generar el reporte: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error al generar el reporte PDF.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
