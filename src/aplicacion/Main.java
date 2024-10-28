// Main.java
package aplicacion;

import controlador.ProyectoController;
import controlador.TorreController;
import controlador.ApartamentoController;
import controlador.AsesorController;
import controlador.ClienteController;
import controlador.PagoController; // Asegúrate de tener el controlador de pagos
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProyectoController proyectoController = new ProyectoController();
        TorreController torreController = new TorreController();
        ApartamentoController apartamentoController = new ApartamentoController();
        AsesorController asesorController = new AsesorController();
        ClienteController clienteController = new ClienteController();
        PagoController pagoController = new PagoController(); // Instancia del controlador de pagos
        
        try (Scanner scanner = new Scanner(System.in)) {
            int opcionPrincipal;
            
            do {
                System.out.println("\n--- Menu Principal ---");
                System.out.println("1. Gestionar Proyectos");
                System.out.println("2. Gestionar Torres");
                System.out.println("3. Gestionar Apartamentos");
                System.out.println("4. Gestionar Asesores");
                System.out.println("5. Gestionar Clientes");
                System.out.println("6. Gestionar Pagos"); // Opción para gestionar pagos
                System.out.println("7. Salir");
                System.out.print("Seleccione una opcion: ");
                opcionPrincipal = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                
                switch (opcionPrincipal) {
                    case 1 -> gestionarProyectos(scanner, proyectoController);
                    case 2 -> gestionarTorres(scanner, torreController);
                    case 3 -> gestionarApartamentos(scanner, apartamentoController);
                    case 4 -> gestionarAsesores(scanner, asesorController);
                    case 5 -> gestionarClientes(scanner, clienteController);
                    case 6 -> gestionarPagos(scanner, pagoController); // Llamada al método de gestión de pagos
                    case 7 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opcion no valida.");
                }
            } while (opcionPrincipal != 7);
        }
    }

    // Método para gestionar proyectos
    private static void gestionarProyectos(Scanner scanner, ProyectoController proyectoController) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Proyectos ---");
            System.out.println("1. Crear Proyecto");
            System.out.println("2. Listar Proyectos");
            System.out.println("3. Actualizar Proyecto");
            System.out.println("4. Eliminar Proyecto");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nombre del proyecto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el número de torres: ");
                    int numeroTorres = scanner.nextInt();
                    proyectoController.crearProyecto(nombre, numeroTorres);
                }
                case 2 -> proyectoController.listarProyectos();
                case 3 -> {
                    System.out.print("Ingrese el ID del proyecto a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo nombre del proyecto: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese el nuevo numero de torres: ");
                    int nuevoNumeroTorres = scanner.nextInt();
                    proyectoController.actualizarProyecto(idActualizar, nuevoNombre, nuevoNumeroTorres);
                }
                case 4 -> {
                    System.out.print("Ingrese el ID del proyecto a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    proyectoController.eliminarProyecto(idEliminar);
                }
                case 5 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // Método para gestionar torres
    private static void gestionarTorres(Scanner scanner, TorreController torreController) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Torres ---");
            System.out.println("1. Crear Torre");
            System.out.println("2. Listar Torres");
            System.out.println("3. Actualizar Torre");
            System.out.println("4. Eliminar Torre");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el ID del proyecto: ");
                    int idProyecto = scanner.nextInt();
                    System.out.print("Ingrese el numero de la torre: ");
                    int numeroTorre = scanner.nextInt();
                    torreController.crearTorre(idProyecto, numeroTorre, 0); // 0 ya que se omitió el número de apartamentos
                }
                case 2 -> torreController.listarTorres();
                case 3 -> {
                    System.out.print("Ingrese el ID de la torre a actualizar: ");
                    int idTorreActualizar = scanner.nextInt();
                    System.out.print("Ingrese el nuevo ID del proyecto: ");
                    int nuevoIdProyecto = scanner.nextInt();
                    System.out.print("Ingrese el nuevo numero de la torre: ");
                    int nuevoNumeroTorre = scanner.nextInt();
                    torreController.actualizarTorre(idTorreActualizar, nuevoIdProyecto, nuevoNumeroTorre, 0);
                }
                case 4 -> {
                    System.out.print("Ingrese el ID de la torre a eliminar: ");
                    int idTorreEliminar = scanner.nextInt();
                    torreController.eliminarTorre(idTorreEliminar);
                }
                case 5 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    // Método para gestionar apartamentos
    private static void gestionarApartamentos(Scanner scanner, ApartamentoController apartamentoController) {
        int opcion;

        do {
            System.out.println("\n--- Gestión de Apartamentos ---");
            System.out.println("1. Crear Apartamento");
            System.out.println("2. Listar Apartamentos");
            System.out.println("3. Actualizar Apartamento");
            System.out.println("4. Eliminar Apartamento");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el ID de la torre: ");
                    int idTorre = scanner.nextInt();
                    System.out.print("Ingrese el numero del apartamento: ");
                    int numeroApartamento = scanner.nextInt();
                    System.out.print("Ingrese el valor del apartamento: ");
                    double valorApartamento = scanner.nextDouble();
                    scanner.nextLine(); // Consumir salto de línea
                    System.out.print("Ingrese el tipo de unidad (local, apartamento, garaje): ");
                    String tipoUnidad = scanner.nextLine();
                    System.out.print("Ingrese el area del apartamento: ");
                    double area = scanner.nextDouble();
                    scanner.nextLine(); // Consumir salto de línea
                    System.out.print("Ingrese la matrícula del apartamento: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Ingrese el nombre del vendedor: ");
                    String idVendedor = scanner.nextLine();
                    System.out.print("Ingrese la fecha de escritura (AAAA-MM-DD): ");
                    String fechaEscritura = scanner.nextLine();
                    apartamentoController.crearApartamento(idTorre, numeroApartamento, valorApartamento, tipoUnidad, area, matricula, idVendedor, fechaEscritura);
                }
                case 2 -> apartamentoController.listarApartamentos();
                case 3 -> {
                    System.out.print("Ingrese el ID del apartamento a actualizar: ");
                    int idApartamentoActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo ID de la torre: ");
                    int nuevoIdTorre = scanner.nextInt();
                    System.out.print("Ingrese el nuevo numero del apartamento: ");
                    int nuevoNumeroApartamento = scanner.nextInt();
                    System.out.print("Ingrese el nuevo valor del apartamento: ");
                    double nuevoValorApartamento = scanner.nextDouble();
                    scanner.nextLine(); // Consumir salto de línea
                    System.out.print("Ingrese el nuevo tipo de unidad: ");
                    String nuevoTipoUnidad = scanner.nextLine();
                    System.out.print("Ingrese el nuevo area del apartamento: ");
                    double nuevoArea = scanner.nextDouble();
                    scanner.nextLine(); // Consumir salto de línea
                    System.out.print("Ingrese la nueva matricula del apartamento: ");
                    String nuevaMatricula = scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del vendedor: ");
                    String nuevoIdVendedor = scanner.nextLine();
                    System.out.print("Ingrese la nueva fecha de escritura (AAAA-MM-DD): ");
                    String nuevaFechaEscritura = scanner.nextLine();
                    apartamentoController.actualizarApartamento(idApartamentoActualizar, nuevoIdTorre, nuevoNumeroApartamento, nuevoValorApartamento, nuevoTipoUnidad, nuevoArea, nuevaMatricula, nuevoIdVendedor, nuevaFechaEscritura);
                }
                case 4 -> {
                    System.out.print("Ingrese el ID del apartamento a eliminar: ");
                    int idApartamentoEliminar = scanner.nextInt();
                    apartamentoController.eliminarApartamento(idApartamentoEliminar);
                }
                case 5 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    // Método para gestionar asesores
    private static void gestionarAsesores(Scanner scanner, AsesorController asesorController) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Asesores ---");
            System.out.println("1. Crear Asesor");
            System.out.println("2. Listar Asesores");
            System.out.println("3. Actualizar Asesor");
            System.out.println("4. Eliminar Asesor");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la cédula del asesor: ");
                    int cedula = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nombre del asesor: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la dirección del asesor: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Ingrese el teléfono del asesor: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Ingrese el correo del asesor: ");
                    String correo = scanner.nextLine();
                    asesorController.crearAsesor(cedula, nombre, direccion, telefono, correo);
                }
                case 2 -> asesorController.listarAsesores();
                case 3 -> {
                    System.out.print("Ingrese la cédula del asesor a actualizar: ");
                    int cedulaActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo nombre del asesor: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese la nueva dirección del asesor: ");
                    String nuevaDireccion = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono del asesor: ");
                    String nuevoTelefono = scanner.nextLine();
                    System.out.print("Ingrese el nuevo correo del asesor: ");
                    String nuevoCorreo = scanner.nextLine();
                    asesorController.actualizarAsesor(cedulaActualizar, nuevoNombre, nuevaDireccion, nuevoTelefono, nuevoCorreo);
                }
                case 4 -> {
                    System.out.print("Ingrese la cédula del asesor a eliminar: ");
                    int cedulaEliminar = scanner.nextInt();
                    asesorController.eliminarAsesor(cedulaEliminar);
                }
                case 5 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // Método para gestionar clientes
    private static void gestionarClientes(Scanner scanner, ClienteController clienteController) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Clientes ---");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la cédula del cliente: ");
                    int cedula = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el SISBEN del cliente: ");
                    String sisben = scanner.nextLine();
                    System.out.print("Ingrese el subsidio del ministerio: ");
                    String subsidioMinisterio = scanner.nextLine();
                    System.out.print("Ingrese la dirección del cliente: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Ingrese el teléfono del cliente: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Ingrese el correo electrónico del cliente: ");
                    String correo = scanner.nextLine();
                    clienteController.crearCliente(cedula, nombre, sisben, subsidioMinisterio, direccion, telefono, correo);
                }
                case 2 -> clienteController.listarClientes();
                case 3 -> {
                    System.out.print("Ingrese la cédula del cliente a actualizar: ");
                    int cedulaActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo nombre del cliente: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese el nuevo SISBEN del cliente: ");
                    String nuevoSisben = scanner.nextLine();
                    System.out.print("Ingrese el nuevo subsidio del ministerio: ");
                    String nuevoSubsidioMinisterio = scanner.nextLine();
                    System.out.print("Ingrese la nueva dirección del cliente: ");
                    String nuevaDireccion = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono del cliente: ");
                    String nuevoTelefono = scanner.nextLine();
                    System.out.print("Ingrese el nuevo correo electrónico del cliente: ");
                    String nuevoCorreo = scanner.nextLine();
                    clienteController.actualizarCliente(cedulaActualizar, nuevoNombre, nuevoSisben, nuevoSubsidioMinisterio, nuevaDireccion, nuevoTelefono, nuevoCorreo);
                }
                case 4 -> {
                    System.out.print("Ingrese la cédula del cliente a eliminar: ");
                    int cedulaEliminar = scanner.nextInt();
                    clienteController.eliminarCliente(cedulaEliminar);
                }
                case 5 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // Método para gestionar pagos
    private static void gestionarPagos(Scanner scanner, PagoController pagoController) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Pagos ---");
            System.out.println("1. Crear Pago");
            System.out.println("2. Listar Pagos");
            System.out.println("3. Actualizar Pago");
            System.out.println("4. Eliminar Pago");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el valor del pago: ");
                    double valorPago = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese la fecha del pago (AAAA-MM-DD): ");
                    String fechaPago = scanner.nextLine();
                    System.out.print("Ingrese la cédula del cliente: ");
                    int cedulaCliente = scanner.nextInt();
                    System.out.print("Ingrese la cédula del asesor: ");
                    int cedulaAsesor = scanner.nextInt();
                    pagoController.crearPago(valorPago, fechaPago, cedulaCliente, cedulaAsesor);
                }
                case 2 -> pagoController.listarPagos();
                case 3 -> {
                    System.out.print("Ingrese el ID del pago a actualizar: ");
                    int idPagoActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo valor del pago: ");
                    double nuevoValorPago = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese la nueva fecha del pago (AAAA-MM-DD): ");
                    String nuevaFechaPago = scanner.nextLine();
                    System.out.print("Ingrese la nueva cédula del cliente: ");
                    int nuevaCedulaCliente = scanner.nextInt();
                    System.out.print("Ingrese la nueva cédula del asesor: ");
                    int nuevaCedulaAsesor = scanner.nextInt();
                    pagoController.actualizarPago(idPagoActualizar, nuevoValorPago, nuevaFechaPago, nuevaCedulaCliente, nuevaCedulaAsesor);
                }
                case 4 -> {
                    System.out.print("Ingrese el ID del pago a eliminar: ");
                    int idPagoEliminar = scanner.nextInt();
                    pagoController.eliminarPago(idPagoEliminar);
                }
                case 5 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
