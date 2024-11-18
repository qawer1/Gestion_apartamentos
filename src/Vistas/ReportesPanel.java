package vistas;

import controlador.ReporteController;
import controlador.ClienteController;
import modelo.Venta;
import modelo.Pago;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportesPanel extends JPanel {
    private JTextArea txtReporte;
    private JComboBox<String> comboClientes;
    private ReporteController reporteController;
    private ClienteController clienteController;

    public ReportesPanel() {
        reporteController = new ReporteController();
        clienteController = new ClienteController();
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);  

        // Botones para generar reportes
        JButton btnGenerarReporte = new JButton("Generar Reporte");
        JButton btnExportarPDF = new JButton("Exportar a PDF");

        // Área de texto para mostrar los reportes
        txtReporte = new JTextArea(20, 50);
        txtReporte.setEditable(false);
        txtReporte.setBackground(Color.LIGHT_GRAY);  // Fondo gris claro para el área de texto
        txtReporte.setForeground(Color.BLACK);  // Texto en color negro para mejor contraste

        // ComboBox para seleccionar cliente
        comboClientes = new JComboBox<>();
        cargarClientes();

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY); // Fondo gris para el panel de botones
        buttonPanel.add(new JLabel("Seleccionar Cliente:"));
        buttonPanel.add(comboClientes);
        buttonPanel.add(btnGenerarReporte);
        buttonPanel.add(btnExportarPDF);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(txtReporte), BorderLayout.CENTER);

        // Acciones de los botones
        btnGenerarReporte.addActionListener(e -> mostrarReporte());
        btnExportarPDF.addActionListener(e -> exportarReportePDF());
    }

    // Cargar la lista de clientes en el ComboBox
    private void cargarClientes() {
        List<modelo.Cliente> clientes = clienteController.obtenerClientes();
        comboClientes.removeAllItems();  // Limpiar el combo antes de agregar nuevos elementos
        for (modelo.Cliente cliente : clientes) {
            // Añadimos el nombre del cliente junto con su cédula en el ComboBox
            comboClientes.addItem(cliente.getNombre() + " - Cédula: " + cliente.getCedula());
        }
    }

    // Mostrar reporte con la información de ventas y pagos de un cliente seleccionado
    private void mostrarReporte() {
        // Obtener la cédula del cliente seleccionado
        String clienteSeleccionado = (String) comboClientes.getSelectedItem();
        int cedulaCliente = Integer.parseInt(clienteSeleccionado.split(" - ")[1].replace("Cédula: ", ""));

        // Obtener las ventas y pagos del cliente
        List<Venta> ventas = reporteController.obtenerVentasPorCliente(cedulaCliente);
        List<Pago> pagos = reporteController.obtenerPagosPorCliente(cedulaCliente);

        // Mostrar las ventas
        double totalPagado = 0.0;
        double precioTotal = 0.0;
        txtReporte.setText("Reporte de Ventas y Pagos para el Cliente: " + clienteSeleccionado + "\n\n");

        // Mostrar información de ventas
        for (Venta venta : ventas) {
            precioTotal = venta.getPrecioTotal();
            txtReporte.append("Venta ID: " + venta.getIdVenta() + 
                              ", Precio Total: " + venta.getPrecioTotal() + 
                              ", Cuotas: " + venta.getNumeroCuotas() + 
                              ", Intereses: " + venta.getIntereses() + 
                              ", Apartamento ID: " + venta.getIdApartamento() +
                              ", Estado de la Venta: " + venta.getEstadoVenta() + "\n");  // Aquí se agrega el estado
        }

        // Mostrar información de pagos
        txtReporte.append("\nPagos realizados:\n");
        for (Pago pago : pagos) {
            totalPagado += pago.getValorPago();
            txtReporte.append("Pago ID: " + pago.getID_Pago() + 
                              ", Valor: " + pago.getValorPago() + 
                              ", Fecha: " + pago.getFecha() + "\n");
        }

        // Mostrar cuánto le falta por pagar
        double saldoRestante = precioTotal - totalPagado;
        txtReporte.append("\nTotal Pagado: " + totalPagado + "\n");
        txtReporte.append("Saldo Restante por Pagar: " + saldoRestante + "\n");
    }

    // Exportar reporte a PDF
    private void exportarReportePDF() {
        // Obtener el texto del reporte generado
        String reporteTexto = txtReporte.getText();
        if (reporteTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reporte para exportar.");
            return;
        }

        // Definir la ruta donde se guardará el PDF
        String filePath = "C:\\Users\\Sebastian\\OneDrive\\Documentos\\Reportes\\reporte_cliente.pdf";

        // Crear el documento PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Añadir el contenido del reporte al PDF
            document.add(new Paragraph("Reporte de Ventas y Pagos\n\n"));
            document.add(new Paragraph(reporteTexto));

            // Cerrar el documento
            document.close();

            JOptionPane.showMessageDialog(this, "Reporte exportado a PDF exitosamente en: " + filePath);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error al exportar el reporte a PDF: " + e.getMessage());
        }
    }
}
