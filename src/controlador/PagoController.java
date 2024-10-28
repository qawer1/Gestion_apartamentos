
package controlador;

import dao.PagoDAO;
import modelo.Pago;

import java.util.List;

public class PagoController {
    private PagoDAO pagoDAO;

    public PagoController() {
        this.pagoDAO = new PagoDAO();
    }

    public void crearPago(double valorPago, String fecha, int Cedula_cliente, int Cedula_asesor) {
        Pago pago = new Pago();
        pago.setValorPago(valorPago);
        pago.setFecha(fecha);
        pago.setCedula_cliente(Cedula_cliente);
        pago.setCedula_asesor(Cedula_asesor);
        pagoDAO.crearPago(pago);
    }

    public List<Pago> obtenerPagos() {
        return pagoDAO.obtenerPagos();
    }
    public void listarPagos() {
    List<Pago> pagos = pagoDAO.obtenerPagos(); // Asegúrate de usar el nombre correcto
    if (pagos.isEmpty()) {
        System.out.println("No hay pagos registrados.");
    } else {
        for (Pago pago : pagos) {
            System.out.println(pago);
        }
    }
}


    public void actualizarPago(int ID_Pago, double valorPago, String fecha, int Cedula_cliente, int Cedula_asesor) {
        Pago pago = new Pago();
        pago.setID_Pago(ID_Pago);
        pago.setValorPago(valorPago);
        pago.setFecha(fecha);
        pago.setCedula_cliente(Cedula_cliente);
        pago.setCedula_asesor(Cedula_asesor);
        pagoDAO.actualizarPago(pago);
    }

    public void eliminarPago(int idPago) {
        pagoDAO.eliminarPago(idPago);
    }
}
