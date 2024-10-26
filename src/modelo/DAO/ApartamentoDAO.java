package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Apartamento;

public class ApartamentoDAO {

    // Método para conectar con la base de datos
    private Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:\\Users\\Sebastian\\Downloads\\bd\\base de datos apartamentos.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método para insertar un apartamento en la base de datos
    public void insertarApartamento(Apartamento apartamento) {
        String sql = "INSERT INTO Apartamentos(Numero_apartamento, Valor, Tipo_unidad, Area, Matricula) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apartamento.getNumeroApartamento());
            pstmt.setDouble(2, apartamento.getValor());
            pstmt.setString(3, apartamento.getTipoUnidad());
            pstmt.setDouble(4, apartamento.getArea());
            pstmt.setString(5, apartamento.getMatricula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obtener una lista de apartamentos por el ID de la torre
    public List<Apartamento> obtenerApartamentosPorTorre(int ID_torre) {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT * FROM Apartamentos WHERE ID_torre = ?";  // Asegúrate que "torreId" es "ID_torre"

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_torre);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Apartamento apartamento = new Apartamento(
                    rs.getInt("ID_apartamento"),  // Cambiado "id" a "ID_apartamento"
                    rs.getInt("Numero_apartamento"),  // Cambiado "numero" a "Numero_apartamento"
                    rs.getDouble("Valor"),
                    rs.getString("Tipo_unidad"),
                    rs.getDouble("Area"),
                    rs.getString("Matricula"),
                    rs.getInt("ID_torre")
                );
                apartamentos.add(apartamento);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return apartamentos;
    }

    // Método para actualizar la información de un apartamento
    public void actualizarApartamento(Apartamento apartamento) {
        String sql = "UPDATE Apartamentos SET Numero_apartamento = ?, Valor = ?, Tipo_unidad = ?, Area = ?, Matricula = ?";  // Ajustar nombres de columnas

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, apartamento.getNumeroApartamento());  // Cambiado "getNumero()" a "getNumeroApartamento()"
            pstmt.setDouble(2, apartamento.getValor());
            pstmt.setString(3, apartamento.getTipoUnidad());
            pstmt.setDouble(4, apartamento.getArea());
            pstmt.setString(5, apartamento.getMatricula());
           
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un apartamento
    public void eliminarApartamento(int id) {
        String sql = "DELETE FROM Apartamentos WHERE ID_apartamento = ?";  // Asegúrate de que coincide con tu base de datos

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
