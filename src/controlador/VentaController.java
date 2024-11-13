package controlador;

import dao.VentaDAO;
import modelo.Venta;

import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;

    // Constructor que inicializa el DAO de Venta
    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    // Método para crear una venta
    public void crearVenta(double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento, String estadoVenta) {
        try {
            Venta venta = new Venta();
            venta.setPrecioTotal(precioTotal);
            venta.setNumeroCuotas(numeroCuotas);
            venta.setIntereses(intereses);
            venta.setIdCliente(idCliente);
            venta.setIdApartamento(idApartamento);
            venta.setEstadoVenta(estadoVenta);  // Establecer el estado de la venta

            // El ID se asignará automáticamente en el DAO gracias a la secuencia o disparador
            ventaDAO.crearVenta(venta); 
            System.out.println("Venta creada con éxito. ID generado: " + venta.getIdVenta());
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
    public void actualizarVenta(int idVenta, double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento, String estadoVenta) {
        try {
            Venta venta = new Venta();
            venta.setIdVenta(idVenta);
            venta.setPrecioTotal(precioTotal);
            venta.setNumeroCuotas(numeroCuotas);
            venta.setIntereses(intereses);
            venta.setIdCliente(idCliente);
            venta.setIdApartamento(idApartamento);
            venta.setEstadoVenta(estadoVenta);  // Actualizar el estado de la venta

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
}
