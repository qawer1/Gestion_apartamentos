package modelo;

public class Torre {
    private int id;
    private int numeroTorre;
    private int numeroApartamentos;
    private int proyectoId;  // Relación con el proyecto
    private int ID_torre;

    // Constructor vacío
    public Torre() {}

    // Constructor con parámetros
    public Torre(int ID_torre, int numeroTorre, int numeroApartamentos, int proyectoId) {
        this.ID_torre = ID_torre;
        this.numeroTorre = numeroTorre;
        this.numeroApartamentos = numeroApartamentos;
        this.proyectoId = proyectoId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroTorre() {
        return numeroTorre;
    }

    public void setNumeroTorre(int numeroTorre) {
        this.numeroTorre = numeroTorre;
    }

    public int getNumeroApartamentos() {
        return numeroApartamentos;
    }

    public void setNumeroApartamentos(int numeroApartamentos) {
        this.numeroApartamentos = numeroApartamentos;
    }

    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }
}
