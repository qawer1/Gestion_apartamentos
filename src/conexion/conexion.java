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

}
