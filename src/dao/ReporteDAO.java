package dao;

import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import modelo.Pago;

public class ReporteDAO {

    // Método para generar reporte de ventas (con estado de venta)
    public List<Venta> obtenerReporteVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "{ call sp_generarReporteVentas(?) }";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            CallableStatement cstmt = conn.prepareCall(sql);
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
                venta.setEstadoVenta(rs.getString("ESTADO_VENTA"));  // Estado de la venta
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte de ventas: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return ventas;
    }

    // Método para generar reporte de pagos
    public List<Pago> obtenerReportePagos() {
        List<Pago> pagos = new ArrayList<>();
        String sql = "{ call sp_generarReportePagos(?) }";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            CallableStatement cstmt = conn.prepareCall(sql);
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
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return pagos;
    }

    // Método para obtener las ventas de un cliente específico (con estado de la venta)
    public List<Venta> obtenerVentasPorCliente(int cedulaCliente) {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM VENTA WHERE CEDULA_CLIENTES = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cedulaCliente);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("ID_VENTA"));
                venta.setPrecioTotal(rs.getDouble("PRECIO_TOTAL"));
                venta.setNumeroCuotas(rs.getInt("NUMERO_CUOTAS"));
                venta.setIntereses(rs.getDouble("INTERESES"));
                venta.setIdCliente(rs.getInt("CEDULA_CLIENTES"));
                venta.setIdApartamento(rs.getInt("ID_APARTAMENTO"));
                venta.setEstadoVenta(rs.getString("ESTADO_VENTA"));  // Estado de la venta
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas del cliente: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return ventas;
    }

    // Método para obtener los pagos de un cliente específico
    public List<Pago> obtenerPagosPorCliente(int cedulaCliente) {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM PAGO WHERE CEDULA_CLIENTE = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cedulaCliente);
            ResultSet rs = pstmt.executeQuery();

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
            System.out.println("Error al obtener pagos del cliente: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return pagos;
    }

    // Método para obtener el total pagado por un cliente para un apartamento específico
    public double obtenerTotalPagadoPorCliente(int cedulaCliente, int idApartamento) {
        double totalPagado = 0.0;
        String sql = "SELECT SUM(VALORPAGO) AS TOTAL_PAGADO FROM PAGO WHERE CEDULA_CLIENTE = ? AND ID_APARTAMENTO = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cedulaCliente);
            pstmt.setInt(2, idApartamento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalPagado = rs.getDouble("TOTAL_PAGADO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener total pagado por cliente: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return totalPagado;
    }

    // Método para obtener el precio total de un apartamento
    public double obtenerPrecioTotalApartamento(int idApartamento) {
        double precioTotal = 0.0;
        String sql = "SELECT VALORAPARTAMENTO FROM APARTAMENTO WHERE ID_APARTAMENTO = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();  // Usar la conexión de la clase 'conexion'
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idApartamento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                precioTotal = rs.getDouble("VALORAPARTAMENTO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener precio total del apartamento: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);  // Asegurar la desconexión
        }
        return precioTotal;
    }
}
