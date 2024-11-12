package controlador;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.VentaDAO;
import modelo.Venta;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;

    // Constructor que inicializa el DAO de Venta
    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    // Método para crear una venta
    public void crearVenta(double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento) {
        try {
            Venta venta = new Venta();
            venta.setPrecioTotal(precioTotal);
            venta.setNumeroCuotas(numeroCuotas);
            venta.setIntereses(intereses);
            venta.setIdCliente(idCliente);
            venta.setIdApartamento(idApartamento);
            
            ventaDAO.crearVenta(venta); // El ID lo asigna el DAO si es autogenerado
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear la venta: " + e.getMessage());
        }
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerVentas() {
        try {
            return ventaDAO.obtenerVentas();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener ventas: " + e.getMessage());
            return null;
        }
    }

    // Método para actualizar una venta
    public void actualizarVenta(int idVenta, double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento) {
        try {
            Venta venta = new Venta();
            venta.setIdVenta(idVenta);
            venta.setPrecioTotal(precioTotal);
            venta.setNumeroCuotas(numeroCuotas);
            venta.setIntereses(intereses);
            venta.setIdCliente(idCliente);
            venta.setIdApartamento(idApartamento);
            
            ventaDAO.actualizarVenta(venta);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la venta: " + e.getMessage());
        }
    }

    // Método para eliminar una venta
    public void eliminarVenta(int idVenta) {
        try {
            ventaDAO.eliminarVenta(idVenta);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar la venta: " + e.getMessage());
        }
    }

    // Método para listar todas las ventas
    public void listarVentas() {
        try {
            List<Venta> ventas = ventaDAO.obtenerVentas();
            if (ventas.isEmpty()) {
                System.out.println("No hay ventas registradas.");
            } else {
                for (Venta venta : ventas) {
                    System.out.println(venta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al listar ventas: " + e.getMessage());
        }
    }

    // Método para generar un reporte en PDF con todas las ventas
    public void generarReporteVentasPDF(String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Título del documento
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Ventas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Espacio en blanco

            // Tabla para mostrar datos de ventas
            PdfPTable table = new PdfPTable(6); // 6 columnas
            table.setWidthPercentage(100);

            // Encabezados de la tabla
            String[] headers = { "ID Venta", "Precio Total", "Número de Cuotas", "Intereses", "ID Cliente", "ID Apartamento" };
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Llenar la tabla con los datos de las ventas
            List<Venta> ventas = obtenerVentas();
            for (Venta venta : ventas) {
                table.addCell(String.valueOf(venta.getIdVenta()));
                table.addCell(String.valueOf(venta.getPrecioTotal()));
                table.addCell(String.valueOf(venta.getNumeroCuotas()));
                table.addCell(String.valueOf(venta.getIntereses()));
                table.addCell(String.valueOf(venta.getIdCliente()));
                table.addCell(String.valueOf(venta.getIdApartamento()));
            }

            document.add(table);
            System.out.println("Reporte de ventas generado correctamente en " + filePath);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            System.out.println("Error al generar el reporte PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
