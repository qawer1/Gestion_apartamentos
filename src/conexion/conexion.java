package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class conexion {

    private static Connection conn = null;
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // Ajusta si es necesario
    private static final String USER = "SYSTEM"; // Cambia "tu_usuario" por el usuario de tu BD
    private static final String PASSWORD = "Case18283022"; // Cambia "tu_contraseña" por la contraseña de tu BD

    // Método para conectar
    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a Oracle 10g XE");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return conn;
    }

    // Método para desconectar
    public static void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al desconectar: " + e.getMessage());
        }
    }

    // Método de prueba para realizar una consulta
    public static void pruebaConsulta() {
        Connection con = conectar();
        if (con != null) {
            String consulta = "SELECT NOMBRE, CONTRASENA, ROL, ID FROM Usuario"; // Asegúrate de que "Usuario" esté en mayúsculas, igual que en tu BD
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(consulta)) {
                while (rs.next()) {
                    String nombre = rs.getString("NOMBRE");
                    String contrasena = rs.getString("CONTRASENA");
                    String rol = rs.getString("ROL");
                    int id = rs.getInt("ID");

                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Contraseña: " + contrasena + ", Rol: " + rol);
                }
            } catch (SQLException e) {
                System.err.println("Error al realizar la consulta: " + e.getMessage());
            } finally {
                desconectar();
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }

    public static void main(String[] args) {
        // Llamada de prueba para verificar la conexión y la consulta
        pruebaConsulta();
    }
}
