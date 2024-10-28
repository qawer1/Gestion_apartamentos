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

    // Método para listar todos los clientes
    public void listarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
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
