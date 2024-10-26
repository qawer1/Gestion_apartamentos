package controlador;

import modelo.Venta;
import modelo.dao.VentaDAO;


public class VentaController {
    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public void agregarVenta(Venta venta) {
        ventaDAO.insertarVenta(venta);
        System.out.println("Venta añadida exitosamente.");
    }

    public void eliminarVenta(int id) {
        ventaDAO.eliminarVenta(id);
        System.out.println("Venta eliminada exitosamente.");
    }

    public void actualizarVenta(Venta venta) {
        ventaDAO.actualizarVenta(venta);
        System.out.println("Venta actualizada exitosamente.");
    }
}
