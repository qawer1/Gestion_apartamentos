// ApartamentoDAO.java
package dao;

import conexion.conexion;
import modelo.Apartamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {

    public void crearApartamento(Apartamento apartamento) {
        String sql = "INSERT INTO Apartamento (ID_torre, Numero_apartamento, valorApartamento, tipoUnidad, area, matricula, Id_vendedor, fechaEscritura) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apartamento.getID_torre());
            pstmt.setInt(2, apartamento.getNumero_apartamento());
            pstmt.setDouble(3, apartamento.getValorApartamento());
            pstmt.setString(4, apartamento.getTipoUnidad());
            pstmt.setDouble(5, apartamento.getArea());
            pstmt.setString(6, apartamento.getMatricula());
            pstmt.setString(7, apartamento.getId_vendedor());
            pstmt.setString(8, apartamento.getFechaEscritura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear apartamento: " + e.getMessage());
        }
    }

    public List<Apartamento> obtenerApartamentos() {
        String sql = "SELECT * FROM Apartamento";
        List<Apartamento> apartamentos = new ArrayList<>();
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setID_apartamento(rs.getInt("ID_apartamento"));
                apartamento.setID_torre(rs.getInt("ID_torre"));
                apartamento.setNumero_apartamento(rs.getInt("Numero_apartamento"));
                apartamento.setValorApartamento(rs.getDouble("valorApartamento"));
                apartamento.setTipoUnidad(rs.getString("tipoUnidad"));
                apartamento.setArea(rs.getDouble("area"));
                apartamento.setMatricula(rs.getString("matricula"));
                apartamento.setId_vendedor(rs.getString("Id_vendedor"));
                apartamento.setFechaEscritura(rs.getString("fechaEscritura"));
                apartamentos.add(apartamento);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener apartamentos: " + e.getMessage());
        }
        return apartamentos;
    }

    public void actualizarApartamento(Apartamento apartamento) {
        String sql = "UPDATE Apartamento SET ID_torre = ?, Numero_apartamento = ?, valorApartamento = ?, tipoUnidad = ?, area = ?, matricula = ?, Id_vendedor = ?, fechaEscritura = ? WHERE ID_apartamento = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apartamento.getID_torre());
            pstmt.setInt(2, apartamento.getNumero_apartamento());
            pstmt.setDouble(3, apartamento.getValorApartamento());
            pstmt.setString(4, apartamento.getTipoUnidad());
            pstmt.setDouble(5, apartamento.getArea());
            pstmt.setString(6, apartamento.getMatricula());
            pstmt.setString(7, apartamento.getId_vendedor());
            pstmt.setString(8, apartamento.getFechaEscritura());
            pstmt.setInt(9, apartamento.getID_apartamento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar apartamento: " + e.getMessage());
        }
    }

    public void eliminarApartamento(int ID_apartamento) {
        String sql = "DELETE FROM Apartamento WHERE ID_apartamento = ?";
        try (Connection conn = conexion.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_apartamento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar apartamento: " + e.getMessage());
        }
    }
}
