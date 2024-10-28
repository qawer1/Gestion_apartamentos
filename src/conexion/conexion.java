package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String URL = "jdbc:sqlite:C:\\Users\\Sebastian\\Downloads\\bd\\base de datos apartamentos.db";

    // Método para conectar a la base de datos
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexion a la base de datos establecida.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conn;
    }

    // Método principal para pruebas de conexión
    public static void main(String[] args) {
        Connection connection = connect();
        if (connection != null) {
            try {
                // Si la conexión se establece, cierra la conexión aquí
                connection.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
