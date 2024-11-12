package modelo;

public class Reporte {
    private int totalVentas;
    private int totalPagos;
    private double totalIngresos;
    private double totalGastos;

    public Reporte(int totalVentas, int totalPagos, double totalIngresos, double totalGastos) {
        this.totalVentas = totalVentas;
        this.totalPagos = totalPagos;
        this.totalIngresos = totalIngresos;
        this.totalGastos = totalGastos;
    }

    public int getTotalVentas() { return totalVentas; }
    public int getTotalPagos() { return totalPagos; }
    public double getTotalIngresos() { return totalIngresos; }
    public double getTotalGastos() { return totalGastos; }

    @Override
    public String toString() {
        return "Reporte{" +
                "totalVentas=" + totalVentas +
                ", totalPagos=" + totalPagos +
                ", totalIngresos=" + totalIngresos +
                ", totalGastos=" + totalGastos +
                '}';
    }
}
