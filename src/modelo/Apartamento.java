// Apartamento.java
package modelo;

public class Apartamento {
    private int ID_apartamento;
    private int ID_torre;
    private int numero_apartamento;
    private double valorApartamento;
    private String tipoUnidad;
    private double area;
    private String matricula;
   

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
        return numero_apartamento;
    }

    public void setNumero_apartamento(int numero_apartamento) {
        this.numero_apartamento = numero_apartamento;
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

}
