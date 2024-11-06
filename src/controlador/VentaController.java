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
            venta.setIdVenta(idVenta); // Este ID debe ser válido y existir en la base de datos
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
}