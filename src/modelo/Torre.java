// Torre.java
package modelo;

public class Torre {
    private int idTorre;
    private int idProyecto;
    private int Numero_torre;
    private int numeroApartamentos;

    // Constructor vacío
    public Torre() {}

    // Constructor con parámetros
    public Torre(int idTorre, int idProyecto, int Numero_torre, int numeroApartamentos) {
        this.idTorre = idTorre;
        this.idProyecto = idProyecto;
        this.Numero_torre = Numero_torre;
        this.numeroApartamentos = numeroApartamentos;
    }

    // Getters y Setters
    public int getIdTorre() {
        return idTorre;
    }

    public void setIdTorre(int idTorre) {
        this.idTorre = idTorre;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getNumero_torre() {
        return Numero_torre;
    }

    public void setNumero_torre(int Numero_torre) {
        this.Numero_torre = Numero_torre;
    }

    public int getNumeroApartamentos() {
        return numeroApartamentos;
    }

    public void setNumeroApartamentos(int numeroApartamentos) {
        this.numeroApartamentos = numeroApartamentos;
    }

    @Override
    public String toString() {
        return "Torre{idTorre=" + idTorre + ", idProyecto=" + idProyecto + ", Numero_torre=" + Numero_torre + ", numeroApartamentos=" + numeroApartamentos + "}";
    }
}
