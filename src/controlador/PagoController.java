package controlador;

import modelo.Pago;
import modelo.dao.PagoDAO;
import java.util.List;

public class PagoController {
    private PagoDAO pagoDAO;

    public PagoController() {
        this.pagoDAO = new PagoDAO();
    }

    public void agregarPago(Pago pago) {
        pagoDAO.insertarPago(pago);
        System.out.println("Pago añadido exitosamente.");
    }

    public void eliminarPago(int id) {
        pagoDAO.eliminarPago(id);
        System.out.println("Pago eliminado exitosamente.");
    }

    public List<Pago> obtenerPagosPorVenta(int ventaId) {
        return pagoDAO.obtenerPagosPorVenta(ventaId);
    }

    public void actualizarPago(Pago pago) {
        pagoDAO.actualizarPago(pago);
        System.out.println("Pago actualizado exitosamente.");
    }
}
