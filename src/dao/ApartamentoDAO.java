package dao;

import conexion.conexion;
import modelo.Apartamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {

    // Método para obtener todos los apartamentos 
    public List<Apartamento> obtenerApartamentos() {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT ID_apartamento, ID_torre, Numero_apartamento, valorApartamento, tipoUnidad, area, matricula, estadoVenta FROM Apartamento";
        Connection conn = null;

        try {
            conn = conexion.conectar(); 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setID_apartamento(rs.getInt("ID_apartamento"));
                apartamento.setID_torre(rs.getInt("ID_torre"));
                apartamento.setNumero_apartamento(rs.getInt("Numero_apartamento"));
                apartamento.setValorApartamento(rs.getDouble("valorApartamento"));
                apartamento.setTipoUnidad(rs.getString("tipoUnidad"));
                apartamento.setArea(rs.getString("area"));
                apartamento.setMatricula(rs.getString("matricula"));
                apartamento.setEstadoVenta(rs.getString("estadoVenta"));  
                apartamentos.add(apartamento);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener apartamentos: " + e.getMessage());
        } finally {
            conexion.desconectar(conn); 
        }
        return apartamentos;
    }

    // Método para obtener todos los IDs de torres
    public List<Integer> obtenerIdsTorres() {
        List<Integer> idsTorres = new ArrayList<>();
        String sql = "SELECT ID_torre FROM Torre";
        Connection conn = null;

        try {
            conn = conexion.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                idsTorres.add(rs.getInt("ID_torre"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener IDs de torres: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);
        }
        return idsTorres;
    }

    // Método para crear un apartamento (
    public void crearApartamento(Apartamento apartamento) {
        String sql = "INSERT INTO Apartamento (ID_apartamento, ID_torre, Numero_apartamento, valorApartamento, tipoUnidad, area, matricula, estadoVenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, apartamento.getID_apartamento());
            pstmt.setInt(2, apartamento.getID_torre());
            pstmt.setInt(3, apartamento.getNumero_apartamento());
            pstmt.setDouble(4, apartamento.getValorApartamento());
            pstmt.setString(5, apartamento.getTipoUnidad());
            pstmt.setString(6, apartamento.getArea());
            pstmt.setString(7, apartamento.getMatricula());
            pstmt.setString(8, apartamento.getEstadoVenta());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al crear apartamento: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);
        }
    }

    // Método para actualizar un apartamento 
    public void actualizarApartamento(Apartamento apartamento) {
        String sql = "UPDATE Apartamento SET ID_torre = ?, Numero_apartamento = ?, valorApartamento = ?, tipoUnidad = ?, area = ?, matricula = ?, estadoVenta = ? WHERE ID_apartamento = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, apartamento.getID_torre());
            pstmt.setInt(2, apartamento.getNumero_apartamento());
            pstmt.setDouble(3, apartamento.getValorApartamento());
            pstmt.setString(4, apartamento.getTipoUnidad());
            pstmt.setString(5, apartamento.getArea());
            pstmt.setString(6, apartamento.getMatricula());
            pstmt.setString(7, apartamento.getEstadoVenta());
            pstmt.setInt(8, apartamento.getID_apartamento());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar apartamento con ID: " + apartamento.getID_apartamento());
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);
        }
    }

    // Método para eliminar un apartamento
    public void eliminarApartamento(int ID_apartamento) {
        String sql = "DELETE FROM Apartamento WHERE ID_apartamento = ?";
        Connection conn = null;

        try {
            conn = conexion.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ID_apartamento);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar apartamento con ID: " + ID_apartamento);
            e.printStackTrace();
        } finally {
            conexion.desconectar(conn);
        }
    }
}
