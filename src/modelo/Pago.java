// Pago.java
package modelo;

public class Pago {
    private int ID_Pago;
    private double valorPago;
    private String fecha; 
    private int Cedula_cliente; 
    private int Cedula_asesor;   

    // Constructor vacío
    public Pago() {}

    // Constructor con parámetros
    public Pago(int ID_Pago, double valorPago, String fecha, int Cedula_cliente, int idVenta) {
        this.ID_Pago = ID_Pago;
        this.valorPago = valorPago;
        this.fecha = fecha;
        this.Cedula_cliente = Cedula_cliente;
        this.Cedula_asesor = Cedula_asesor;
    }

    // Getters y Setters
    public int getID_Pago() {
        return ID_Pago;
    }

    public void setID_Pago(int ID_Pago) {
        this.ID_Pago = ID_Pago;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCedula_cliente() {
        return Cedula_cliente;
    }

    public void setCedula_cliente(int Cedula_cliente) {
        this.Cedula_cliente = Cedula_cliente;
    }

    public int getCedula_asesor() {
        return Cedula_asesor;
    }

    public void setCedula_asesor(int Cedula_asesor) {
        this.Cedula_asesor = Cedula_asesor;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "ID_Pago=" + ID_Pago +
                ", valorPago=" + valorPago +
                ", fecha='" + fecha + '\'' +
                ", Cedula_cliente=" + Cedula_cliente +
                ", Cedula_asesor=" + Cedula_asesor +
                '}';
    }
}
