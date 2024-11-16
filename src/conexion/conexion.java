package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    // Datos de conexión
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // Ajusta si es necesario
    private static final String USER = "controlador"; // Usuario de tu base de datos
    private static final String PASSWORD = "Case18283022"; // Contraseña de tu base de datos

    // Método para conectar
    public static Connection conectar() {
        Connection conn = null;
        try {
            // Cargar el driver
            Class.forName(DRIVER);
            // Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a Oracle 10g XE");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de Oracle JDBC. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    // Método para desconectar
    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
