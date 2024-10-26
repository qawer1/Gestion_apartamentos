package modelo;

public class Apartamento {
    private int id;
    private int numeroApartamento;
    private double valor;
    private String tipoUnidad;  // Puede ser "local", "apartamento", "garaje"
    private double area;
    private String matricula;
    private String vendedor;    // Se ingresa al momento de la venta
    private String fechaEscritura;  // Se ingresa al momento de la venta
    private int ID_torre;

    // Constructor vacío
    public Apartamento() {}

    // Constructor con parámetros
    public Apartamento(int id, int numeroApartamento, double valor, String tipoUnidad, double area, String matricula, int ID_torre) {
        this.id = id;
        this.numeroApartamento = numeroApartamento;
        this.valor = valor;
        this.tipoUnidad = tipoUnidad;
        this.area = area;
        this.matricula = matricula;
        this.vendedor = null;    // Inicialmente nulo, se establece en la venta
        this.fechaEscritura = null;  // Inicialmente nulo, se establece en la venta
        this.ID_torre= ID_torre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(int numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getFechaEscritura() {
        return fechaEscritura;
    }

    public void setFechaEscritura(String fechaEscritura) {
        this.fechaEscritura = fechaEscritura;
    }
}
