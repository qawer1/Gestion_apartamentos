// Proyecto.java
package modelo;

public class Proyecto {
    private int idProyecto;       // ID del proyecto
    private String nombre;         // Nombre del proyecto
    private int idTorre;           // ID de la torre
    private int numeroTorre;       // Número de la torre específica
    private int numeroApartamentos; // Número de apartamentos en el proyecto

    // Constructor vacío
    public Proyecto() {}

    // Constructor con parámetros
    public Proyecto(int idProyecto, String nombre, int idTorre, int numeroTorre, int numeroApartamentos) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.idTorre = idTorre;
        this.numeroTorre = numeroTorre;
        this.numeroApartamentos = numeroApartamentos;
    }

    // Getters y Setters
    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTorre() {
        return idTorre;
    }

    public void setIdTorre(int idTorre) {
        this.idTorre = idTorre;
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

    @Override
    public String toString() {
        return "Proyecto{idProyecto=" + idProyecto + ", nombre='" + nombre + "', idTorre=" + idTorre +
                ", numeroTorre=" + numeroTorre + ", numeroApartamentos=" + numeroApartamentos + "}";
    }
}
