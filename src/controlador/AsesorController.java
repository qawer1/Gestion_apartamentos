package controlador;

import modelo.Asesor;
import modelo.dao.AsesorDAO;
import java.util.List;

public class AsesorController {
    private AsesorDAO asesorDAO;

    public AsesorController() {
        this.asesorDAO = new AsesorDAO();
    }

    public void agregarAsesor(Asesor asesor) {
        asesorDAO.insertarAsesor(asesor);
        System.out.println("Asesor añadido exitosamente.");
    }

    public void eliminarAsesor(String cedula) {
        asesorDAO.eliminarAsesor(cedula);
        System.out.println("Asesor eliminado exitosamente.");
    }
    
    public List<Asesor> obtenerAsesores() {
    return asesorDAO.obtenerAsesores();
}

  
    public void actualizarAsesor(Asesor asesor) {
        asesorDAO.actualizarAsesor(asesor);
        System.out.println("Asesor actualizado exitosamente.");
    }
}
