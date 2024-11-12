package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
public class VentaDAO {

    private Connection conectar() {
        Connection conn = null;
        try {
            // Configuración de conexión para Oracle
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "SYSTEM";
            String password = "Case18283022";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método para crear una venta
    public void crearVenta(Venta venta) {
        String sql = "INSERT INTO Venta (ID_VENTA, PRECIO_TOTAL, NUMERO_CUOTAS, INTERESES, CEDULA_CLIENTES, ID_APARTAMENTO) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, venta.getIdVenta());
            pstmt.setDouble(2, venta.getPrecioTotal());
            pstmt.setInt(3, venta.getNumeroCuotas());
            pstmt.setDouble(4, venta.getIntereses());
            pstmt.setInt(5, venta.getIdCliente());
            pstmt.setInt(6, venta.getIdApartamento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear venta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT ID_VENTA, PRECIO_TOTAL, NUMERO_CUOTAS, INTERESES, CEDULA_CLIENTES, ID_APARTAMENTO FROM Venta";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("ID_VENTA"));
                venta.setPrecioTotal(rs.getDouble("PRECIO_TOTAL"));
                venta.setNumeroCuotas(rs.getInt("NUMERO_CUOTAS"));
                venta.setIntereses(rs.getDouble("INTERESES"));
                venta.setIdCliente(rs.getInt("CEDULA_CLIENTES"));
                venta.setIdApartamento(rs.getInt("ID_APARTAMENTO"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas");
            e.printStackTrace();
        }
        return ventas;
    }

    // Método para actualizar una venta
    public void actualizarVenta(Venta venta) {
        String sql = "UPDATE Venta SET PRECIO_TOTAL = ?, NUMERO_CUOTAS = ?, INTERESES = ?, CEDULA_CLIENTES = ?, ID_APARTAMENTO = ? WHERE ID_VENTA = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, venta.getPrecioTotal());
            pstmt.setInt(2, venta.getNumeroCuotas());
            pstmt.setDouble(3, venta.getIntereses());
            pstmt.setInt(4, venta.getIdCliente());
            pstmt.setInt(5, venta.getIdApartamento());
            pstmt.setInt(6, venta.getIdVenta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta con ID: " + venta.getIdVenta());
            e.printStackTrace();
        }
    }

    // Método para eliminar una venta
    public void eliminarVenta(int idVenta) {
        String sql = "DELETE FROM Venta WHERE ID_VENTA = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVenta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar venta con ID: " + idVenta);
            e.printStackTrace();
        }
    }
    
    public void generarReporteVentasPDF(String rutaArchivo) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            // Agregar título
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte de Ventas", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));  // Espacio en blanco

            // Crear tabla
            PdfPTable table = new PdfPTable(6);  // 6 columnas
            table.setWidthPercentage(100);
            
            // Encabezados
            String[] headers = {"ID Venta", "Precio Total", "Número de Cuotas", "Intereses", "Cédula Cliente", "ID Apartamento"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Obtener ventas y llenar la tabla
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

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar reporte en PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
