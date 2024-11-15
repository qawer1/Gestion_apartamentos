package dao;

import modelo.Apartamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {

    // Método de conexión
    private Connection conectar() {
        Connection conn = null;
        try {
            // Configuración de conexión para Oracle
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "SYSTEM"; // Cambia esto si es necesario
            String password = "Case18283022"; // Cambia esto si es necesario
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    // Método para obtener todos los apartamentos (incluye estadoVenta)
    public List<Apartamento> obtenerApartamentos() {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT ID_apartamento, ID_torre, Numero_apartamento, valorApartamento, tipoUnidad, area, matricula, estadoVenta FROM Apartamento";
        
        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setID_apartamento(rs.getInt("ID_apartamento"));
                apartamento.setID_torre(rs.getInt("ID_torre"));
                apartamento.setNumero_apartamento(rs.getInt("Numero_apartamento"));
                apartamento.setValorApartamento(rs.getDouble("valorApartamento"));
                apartamento.setTipoUnidad(rs.getString("tipoUnidad"));
                apartamento.setArea(rs.getString("area"));
                apartamento.setMatricula(rs.getString("matricula"));
                apartamento.setEstadoVenta(rs.getString("estadoVenta"));  // Obtener estadoVenta
                apartamentos.add(apartamento);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener apartamentos: " + e.getMessage());
        }
        return apartamentos;
    }

    // Método para obtener todos los IDs de torres
    public List<Integer> obtenerIdsTorres() {
        List<Integer> idsTorres = new ArrayList<>();
        String sql = "SELECT ID_torre FROM Torre";  // Suponiendo que tienes una tabla llamada "Torre" con los IDs de torres
        
        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                idsTorres.add(rs.getInt("ID_torre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener IDs de torres: " + e.getMessage());
        }
        return idsTorres;
    }

    // Método para crear un apartamento (ahora incluye estadoVenta)
    public void crearApartamento(Apartamento apartamento) {
        String sql = "INSERT INTO Apartamento (ID_apartamento, ID_torre, Numero_apartamento, valorApartamento, tipoUnidad, area, matricula, estadoVenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apartamento.getID_apartamento());
            pstmt.setInt(2, apartamento.getID_torre());
            pstmt.setInt(3, apartamento.getNumero_apartamento());
            pstmt.setDouble(4, apartamento.getValorApartamento());
            pstmt.setString(5, apartamento.getTipoUnidad());
            pstmt.setString(6, apartamento.getArea());
            pstmt.setString(7, apartamento.getMatricula());
            pstmt.setString(8, apartamento.getEstadoVenta());  // Se incluye estadoVenta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear apartamento: " + e.getMessage());
        }
    }

    // Método para actualizar un apartamento (incluye estadoVenta)
    public void actualizarApartamento(Apartamento apartamento) {
        String sql = "UPDATE Apartamento SET ID_torre = ?, Numero_apartamento = ?, valorApartamento = ?, tipoUnidad = ?, area = ?, matricula = ?, estadoVenta = ? WHERE ID_apartamento = ?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apartamento.getID_torre());
            pstmt.setInt(2, apartamento.getNumero_apartamento());
            pstmt.setDouble(3, apartamento.getValorApartamento());
            pstmt.setString(4, apartamento.getTipoUnidad());
            pstmt.setString(5, apartamento.getArea());
            pstmt.setString(6, apartamento.getMatricula());
            pstmt.setString(7, apartamento.getEstadoVenta());  // Actualizar estadoVenta
            pstmt.setInt(8, apartamento.getID_apartamento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar apartamento con ID: " + apartamento.getID_apartamento());
            e.printStackTrace();
        }
    }

    // Método para eliminar un apartamento
    public void eliminarApartamento(int ID_apartamento) {
        String sql = "DELETE FROM Apartamento WHERE ID_apartamento = ?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_apartamento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar apartamento con ID: " + ID_apartamento);
            e.printStackTrace();
        }
    }
}
