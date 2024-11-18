package modelo;

public class Proyecto {
    private int idProyecto;       
    private String nombre;         
    private int numeroTorres;      
    // Constructor vacío
    public Proyecto() {}

    // Constructor con parámetros
    public Proyecto(int idProyecto, String nombre, int numeroTorres) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.numeroTorres = numeroTorres;
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

    public int getNumeroTorres() {
        return numeroTorres;
    }

    public void setNumeroTorres(int numeroTorres) {
        this.numeroTorres = numeroTorres;
    }

    @Override
    public String toString() {
        return "Proyecto{idProyecto=" + idProyecto + ", nombre='" + nombre + "', numeroTorres=" + numeroTorres + "}";
    }
}
