package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import modelo.Pago;

public class ReporteDAO {
    private Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "SYSTEM";
            String password = "Case18283022";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }

    // Método para generar reporte de ventas
    public List<Venta> obtenerReporteVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "{ call sp_generarReporteVentas(?) }";

        try (Connection conn = conectar();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
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
            System.out.println("Error al obtener reporte de ventas: " + e.getMessage());
        }
        return ventas;
    }

    // Método para generar reporte de pagos
    public List<Pago> obtenerReportePagos() {
        List<Pago> pagos = new ArrayList<>();
        String sql = "{ call sp_generarReportePagos(?) }";

        try (Connection conn = conectar();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setID_Pago(rs.getInt("ID_PAGO"));
                pago.setValorPago(rs.getDouble("VALORPAGO"));
                pago.setFecha(rs.getString("FECHA"));
                pago.setCedula_cliente(rs.getInt("CEDULA_CLIENTE"));
                pago.setCedula_asesor(rs.getInt("CEDULA_ASESOR"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte de pagos: " + e.getMessage());
        }
        return pagos;
    }
}