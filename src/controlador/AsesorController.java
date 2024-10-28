package controlador;

import dao.AsesorDAO;
import modelo.Asesor;
import java.util.List;

public class AsesorController {
    private AsesorDAO asesorDAO; // Cambié el nombre de la variable a asesorDAO

    public AsesorController() {
        this.asesorDAO = new AsesorDAO(); // Instanciar AsesorDAO
    }

    public void crearAsesor(int cedula, String nombre, String direccion, String telefono, String Correo_electronico) {
        Asesor asesor = new Asesor(); // Crear un objeto Asesor
        asesor.setCedula(cedula);
        asesor.setNombre(nombre);
        asesor.setDireccion(direccion);
        asesor.setTelefono(telefono);
        asesor.setCorreo_electronico(Correo_electronico);
        asesorDAO.crearAsesor(asesor); // Llamar al método para crear asesor
        System.out.println("Asesor creado exitosamente.");
    }

    public void listarAsesores() {
        List<Asesor> asesores = asesorDAO.listarAsesores(); // Obtener la lista de asesores
        for (Asesor asesor : asesores) {
            System.out.println(asesor); // Imprimir cada asesor
        }
    }

    public void actualizarAsesor(int cedula, String nombre, String direccion, String telefono, String Correo_electronico) {
        Asesor asesor = new Asesor(cedula, nombre, direccion, telefono, Correo_electronico); // Crear un objeto Asesor
        asesorDAO.actualizarAsesor(asesor); // Llamar al método para actualizar asesor
        System.out.println("Asesor actualizado exitosamente.");
    }

    public void eliminarAsesor(int cedula) {
        asesorDAO.eliminarAsesor(cedula); // Llamar al método para eliminar asesor
        System.out.println("Asesor eliminado exitosamente.");
    }
}
