package controlador;

import dao.PagoDAO;
import modelo.Pago;

import java.util.List;

public class PagoController {
    private PagoDAO pagoDAO;

    public PagoController() {
        this.pagoDAO = new PagoDAO();
    }

    // Método para crear un pago
    public void crearPago(int ID_Pago, double valorPago, String fecha, int Cedula_cliente, int Cedula_asesor) {
        Pago pago = new Pago();
        pago.setID_Pago(ID_Pago); 
        pago.setValorPago(valorPago);
        pago.setFecha(fecha);
        pago.setCedula_cliente(Cedula_cliente);
        pago.setCedula_asesor(Cedula_asesor);
        pagoDAO.crearPago(pago);
    }

    // Método para obtener la lista de pagos
    public List<Pago> obtenerPagos() {
        return pagoDAO.obtenerPagos();
    }

    // Método para listar los pagos
    public void listarPagos() {
        List<Pago> pagos = pagoDAO.obtenerPagos();
        if (pagos.isEmpty()) {
            System.out.println("No hay pagos registrados.");
        } else {
            for (Pago pago : pagos) {
                System.out.println(pago);
            }
        }
    }

    // Método para actualizar un pago
    public void actualizarPago(int ID_Pago, double valorPago, String fecha, int Cedula_cliente, int Cedula_asesor) {
        Pago pago = new Pago();
        pago.setID_Pago(ID_Pago);  
        pago.setValorPago(valorPago);
        pago.setFecha(fecha);
        pago.setCedula_cliente(Cedula_cliente);
        pago.setCedula_asesor(Cedula_asesor);
        pagoDAO.actualizarPago(pago);
    }

    // Método para eliminar un pago
    public void eliminarPago(int ID_Pago) {
        pagoDAO.eliminarPago(ID_Pago);
    }
}