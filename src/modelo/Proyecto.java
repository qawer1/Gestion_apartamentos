package modelo;

public class Proyecto {
    private int id;
    private String nombre;
    private int numeroTorres;

    // Constructor vacío
    public Proyecto() {}

    // Constructor con todos los parámetros
    public Proyecto(int id, String nombre, int numeroTorres) {
        this.id = id;
        this.nombre = nombre;
        this.numeroTorres = numeroTorres;
    }

    // Constructor que solo recibe el nombre del proyecto
    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.id = 0;  // Asignar un valor por defecto al id
        this.numeroTorres = 0;  // Asignar un valor por defecto al número de torres
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
