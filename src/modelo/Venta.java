package modelo;

public class Venta {
    private int idVenta;
    private double precioTotal;
    private int numeroCuotas;
    private double intereses;
    private int idCliente;  
    private int idApartamento;
    private String estadoVenta;  // Nuevo atributo

    // Constructor vacío
    public Venta() {}

    // Constructor con todos los atributos
    public Venta(int idVenta, double precioTotal, int numeroCuotas, double intereses, int idCliente, int idApartamento, String estadoVenta) {
        this.idVenta = idVenta;
        this.precioTotal = precioTotal;
        this.numeroCuotas = numeroCuotas;
        this.intereses = intereses;
        this.idCliente = idCliente;
        this.idApartamento = idApartamento;
        this.estadoVenta = estadoVenta;  // Inicializar el estado de la venta
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

    public String getEstadoVenta() {
        return estadoVenta;  // Getter para el estado de la venta
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;  // Setter para el estado de la venta
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
                ", estadoVenta='" + estadoVenta + '\'' +  // Incluir el estado en la representación de la venta
                '}';
    }
}
