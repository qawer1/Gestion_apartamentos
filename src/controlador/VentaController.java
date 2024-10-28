// VentaController.java
package controlador;

import dao.VentaDAO;
import modelo.Venta;

import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public void crearVenta(double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento) {
        Venta venta = new Venta();
        venta.setPrecioTotal(precioTotal);
        venta.setNumeroCuotas(numeroCuotas);
        venta.setIntereses(intereses);
        venta.setIdCliente(idCliente);
        venta.setIdApartamento(idApartamento);
        ventaDAO.crearVenta(venta);
        System.out.println("Venta creada exitosamente.");
    }

    public void listarVentas() {
        List<Venta> ventas = ventaDAO.obtenerVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("Listado de Ventas:");
            for (Venta venta : ventas) {
                System.out.println("ID Venta: " + venta.getIdVenta());
                System.out.println("Precio Total: " + venta.getPrecioTotal());
                System.out.println("Número de Cuotas: " + venta.getNumeroCuotas());
                System.out.println("Intereses: " + venta.getIntereses());
                System.out.println("ID Cliente: " + venta.getIdCliente());
                System.out.println("ID Apartamento: " + venta.getIdApartamento());
                System.out.println("---------------------------");
            }
        }
    }

    public void actualizarVenta(int idVenta, double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento) {
        Venta venta = new Venta();
        venta.setIdVenta(idVenta);
        venta.setPrecioTotal(precioTotal);
        venta.setNumeroCuotas(numeroCuotas);
        venta.setIntereses(intereses);
        venta.setIdCliente(idCliente);
        venta.setIdApartamento(idApartamento);
        if (ventaDAO.actualizarVenta(venta)) {
            System.out.println("Venta actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la venta.");
        }
    }

    public void eliminarVenta(int idVenta) {
        if (ventaDAO.eliminarVenta(idVenta)) {
            System.out.println("Venta eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la venta.");
        }
    }
}
