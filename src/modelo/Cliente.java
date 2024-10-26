package modelo;

public class Cliente {
    private String cedula;
    private String nombre;
    private String sisben;
    private String subsidioMinisterio;
    private String direccion;
    private String telefono;
    private String correo;

    // Constructor vacío
    public Cliente() {}

    // Constructor con parámetros
    public Cliente(String cedula, String nombre, String sisben, String subsidioMinisterio, String direccion, String telefono, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sisben = sisben;
        this.subsidioMinisterio = subsidioMinisterio;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
