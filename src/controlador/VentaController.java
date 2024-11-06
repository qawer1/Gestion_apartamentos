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
        Venta venta = new Venta();
        venta.setPrecioTotal(precioTotal);
        venta.setNumeroCuotas(numeroCuotas);
        venta.setIntereses(intereses);
        venta.setIdCliente(idCliente);
        venta.setIdApartamento(idApartamento);
        ventaDAO.crearVenta(venta);
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerVentas() {
        return ventaDAO.obtenerVentas();
    }

    // Método para listar todas las ventas
    public void listarVentas() {
        List<Venta> ventas = ventaDAO.obtenerVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta venta : ventas) {
                System.out.println(venta);
            }
        }
    }

    // Método para actualizar una venta
    public void actualizarVenta(int idVenta, double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento) {
        Venta venta = new Venta();
        venta.setIdVenta(idVenta);
        venta.setPrecioTotal(precioTotal);
        venta.setNumeroCuotas(numeroCuotas);
        venta.setIntereses(intereses);
        venta.setIdCliente(idCliente);
        venta.setIdApartamento(idApartamento);
        ventaDAO.actualizarVenta(venta);
    }

    // Método para eliminar una venta
    public void eliminarVenta(int idVenta) {
        ventaDAO.eliminarVenta(idVenta);
    }
}
