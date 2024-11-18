// AsesorController.java
package controlador;

import dao.AsesorDAO;
import modelo.Asesor;

import java.util.List;

public class AsesorController {
    private AsesorDAO asesorDAO;

    public AsesorController() {
        asesorDAO = new AsesorDAO();
    }

    public void crearAsesor(int cedula, String nombre, String direccion, String telefono, String correoElectronico) {
        Asesor asesor = new Asesor();
        asesor.setCedula(cedula);
        asesor.setNombre(nombre);
        asesor.setDireccion(direccion);
        asesor.setTelefono(telefono);
        asesor.setCorreo_electronico(correoElectronico);
        asesorDAO.crearAsesor(asesor);
    }

    public List<Asesor> obtenerAsesores() {
        return asesorDAO.obtenerAsesores();
    }

    public void actualizarAsesor(int cedula, String nombre, String direccion, String telefono, String correoElectronico) {
        Asesor asesor = new Asesor();
        asesor.setCedula(cedula);
        asesor.setNombre(nombre);
        asesor.setDireccion(direccion);
        asesor.setTelefono(telefono);
        asesor.setCorreo_electronico(correoElectronico);
        asesorDAO.actualizarAsesor(asesor);
    }

    public String listarAsesores() {
        List<Asesor> asesores = obtenerAsesores(); 
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Asesores:\n");
        for (Asesor asesor : asesores) {
            sb.append("Cédula: ").append(asesor.getCedula())
              .append(", Nombre: ").append(asesor.getNombre())
              .append(", Dirección: ").append(asesor.getDireccion())
              .append(", Teléfono: ").append(asesor.getTelefono())
              .append(", Correo Electrónico: ").append(asesor.getCorreo_electronico())
              .append("\n");
        }
        return sb.toString();
    }

    public void eliminarAsesor(int cedula) {
        asesorDAO.eliminarAsesor(cedula);
    }
}
