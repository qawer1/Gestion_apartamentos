// Apartamento.java
package modelo;

public class Apartamento {
    private int ID_apartamento;
    private int ID_torre;
    private int Numero_apartamento;
    private double valorApartamento;
    private String tipoUnidad;
    private double area;
    private String matricula;
    private String Id_vendedor;
    private String fechaEscritura;

    // Constructor vacío
    public Apartamento() {}

    // Constructor con parámetros
    public Apartamento(int ID_apartamento, int ID_torre, int Numero_apartamento, double valorApartamento, String tipoUnidad,
                       double area, String matricula, String Id_vendedor, String fechaEscritura) {
        this.ID_apartamento = ID_apartamento;
        this.ID_torre = ID_torre;
        this.Numero_apartamento = Numero_apartamento;
        this.valorApartamento = valorApartamento;
        this.tipoUnidad = tipoUnidad;
        this.area = area;
        this.matricula = matricula;
        this.Id_vendedor = Id_vendedor;
        this.fechaEscritura = fechaEscritura;
    }

    // Getters y Setters
    public int getID_apartamento() {
        return ID_apartamento;
    }

    public void setID_apartamento(int ID_apartamento) {
        this.ID_apartamento = ID_apartamento;
    }

    public int getID_torre() {
        return ID_torre;
    }

    public void setID_torre(int ID_torre) {
        this.ID_torre = ID_torre;
    }

    public int getNumero_apartamento() {
        return Numero_apartamento;
    }

    public void setNumero_apartamento(int Numero_apartamento) {
        this.Numero_apartamento = Numero_apartamento;
    }

    public double getValorApartamento() {
        return valorApartamento;
    }

    public void setValorApartamento(double valorApartamento) {
        this.valorApartamento = valorApartamento;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getId_vendedor() {
        return Id_vendedor;
    }

    public void setId_vendedor(String Id_vendedor) {
        this.Id_vendedor = Id_vendedor;
    }

    public String getFechaEscritura() {
        return fechaEscritura;
    }

    public void setFechaEscritura(String fechaEscritura) {
        this.fechaEscritura = fechaEscritura;
    }

    @Override
    public String toString() {
        return "Apartamento{ID_apartamento=" + ID_apartamento + ", ID_torre=" + ID_torre + ", Numero_apartamento=" + Numero_apartamento +
                ", valorApartamento=" + valorApartamento + ", tipoUnidad='" + tipoUnidad + "', area=" + area + ", matricula='" +
                matricula + "', Id_vendedor='" + Id_vendedor + "', fechaEscritura='" + fechaEscritura + "'}";
    }
}
