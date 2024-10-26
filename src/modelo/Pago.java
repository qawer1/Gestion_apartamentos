package modelo;

public class Pago {
    private int id;
    private double monto;
    private String fechaPago;
    private int ventaId;

    // Constructor vacío
    public Pago() {}

    // Constructor con parámetros
    public Pago(int id, double monto, String fechaPago, int ventaId) {
        this.id = id;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.ventaId = ventaId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }
}
