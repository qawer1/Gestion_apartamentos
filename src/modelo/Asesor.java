package modelo;

public class Asesor {
    private int cedula;
    private String nombre;
    private String direccion;
    private String telefono;
    private String Correo_electronico;

    // Constructor vacío
    public Asesor() {}

    // Constructor con parámetros
    public Asesor(int cedula, String nombre, String direccion, String telefono, String Correo_electronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.Correo_electronico = Correo_electronico;
    }

    // Getters y Setters
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return Correo_electronico;
    }

    public void setCorreo_electronico(String Correo_electronico) {
        this.Correo_electronico = Correo_electronico;
    }

    @Override
    public String toString() {
        return "Asesor{cedula=" + cedula + ", nombre='" + nombre + "', direccion='" + direccion + "', telefono='" + telefono + "', Correo_electronico='" + Correo_electronico+ "'}";
    }
}
