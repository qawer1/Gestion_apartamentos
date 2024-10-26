package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Torre;

public class TorreDAO {

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

    public void insertarTorre(Torre torre) {
        String sql = "INSERT INTO Torre(numeroTorre, numeroApartamentos, proyectoId) VALUES(?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torre.getNumeroTorre());
            pstmt.setInt(2, torre.getNumeroApartamentos());
            pstmt.setInt(3, torre.getProyectoId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Torre> obtenerTorresPorProyecto(int proyectoId) {
        List<Torre> torres = new ArrayList<>();
        String sql = "SELECT * FROM Torre WHERE proyectoId = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, proyectoId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Torre torre = new Torre(
                    rs.getInt("ID_torre"),
                    rs.getInt("numeroTorre"),
                    rs.getInt("numeroApartamentos"),
                    rs.getInt("proyectoId")
                );
                torres.add(torre);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return torres;
    }

    public void actualizarTorre(Torre torre) {
        String sql = "UPDATE Torre SET numeroTorre = ?, numeroApartamentos = ? WHERE ID_torre = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torre.getNumeroTorre());
            pstmt.setInt(2, torre.getNumeroApartamentos());
            pstmt.setInt(3, torre.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarTorre(int id) {
        String sql = "DELETE FROM Torre WHERE ID_torre = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
