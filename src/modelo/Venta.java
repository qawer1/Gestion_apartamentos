// Venta.java
package modelo;

public class Venta {
    private int idVenta;
    private double precioTotal;
    private int numeroCuotas;
    private double intereses;
    private int idCliente;  
    private int idApartamento;

    // Constructor vacío
    public Venta() {}

    // Constructor con todos los atributos
    public Venta(int idVenta, double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento) {
        this.idVenta = idVenta;
        this.precioTotal = precioTotal;
        this.numeroCuotas = numeroCuotas;
        this.intereses = intereses;
        this.idCliente = idCliente;
        this.idApartamento = idApartamento;
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(int idApartamento) {
        this.idApartamento = idApartamento;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", precioTotal=" + precioTotal +
                ", numeroCuotas=" + numeroCuotas +
                ", intereses=" + intereses +
                ", idCliente=" + idCliente +
                ", idApartamento=" + idApartamento +
                '}';
    }
}
