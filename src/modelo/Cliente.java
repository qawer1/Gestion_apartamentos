package modelo;

public class Cliente {
    private int cedula;
    private String nombre;
    private String sisben;
    private String subsidioMinisterio;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    // Constructor vacío
    public Cliente() {}

    // Constructor con parámetros
    public Cliente(int cedula, String nombre, String sisben, String subsidioMinisterio,
                   String direccion, String telefono, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sisben = sisben;
        this.subsidioMinisterio = subsidioMinisterio;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
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

    public String getSisben() {
        return sisben;
    }

    public void setSisben(String sisben) {
        this.sisben = sisben;
    }

    public String getSubsidioMinisterio() {
        return subsidioMinisterio;
    }

    public void setSubsidioMinisterio(String subsidioMinisterio) {
        this.subsidioMinisterio = subsidioMinisterio;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula=" + cedula + // Sin comillas porque es un número
                ", nombre='" + nombre + '\'' +
                ", sisben='" + sisben + '\'' +
                ", subsidioMinisterio='" + subsidioMinisterio + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}