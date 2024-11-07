package controlador;

import dao.ClienteDAO;
import modelo.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    // Constructor que inicializa el ClienteDAO
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método para crear un nuevo cliente
    public void crearCliente(int cedula, String nombre, String sisben, String subsidioMinisterio,
                             String direccion, String telefono, String correoElectronico) {
        Cliente cliente = new Cliente(cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correoElectronico);
        clienteDAO.crearCliente(cliente);
        System.out.println("Cliente creado exitosamente.");
    }

    // Método para listar todos los clientes en formato legible
    public String listarClientes() {
        List<Cliente> clientes = obtenerClientes(); // Llama a obtenerClientes() para obtener la lista
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Clientes:\n");
        for (Cliente cliente : clientes) {
            sb.append("Cédula: ").append(cliente.getCedula())
              .append(", Nombre: ").append(cliente.getNombre())
              .append(", SISBEN: ").append(cliente.getSisben())
              .append(", Subsidio Ministerio: ").append(cliente.getSubsidioMinisterio())
              .append(", Dirección: ").append(cliente.getDireccion())
              .append(", Teléfono: ").append(cliente.getTelefono())
              .append(", Correo Electrónico: ").append(cliente.getCorreoElectronico())
              .append("\n");
        }
        return sb.toString();
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        return clienteDAO.obtenerClientes();
    }

    // Método para actualizar un cliente existente
    public void actualizarCliente(int cedula, String nombre, String sisben, String subsidioMinisterio,
                                  String direccion, String telefono, String correoElectronico) {
        Cliente cliente = new Cliente(cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correoElectronico);
        clienteDAO.actualizarCliente(cliente);
        System.out.println("Cliente actualizado exitosamente.");
    }

    // Método para eliminar un cliente
    public void eliminarCliente(int cedula) {
        clienteDAO.eliminarCliente(cedula);
        System.out.println("Cliente eliminado exitosamente.");
    }
}
