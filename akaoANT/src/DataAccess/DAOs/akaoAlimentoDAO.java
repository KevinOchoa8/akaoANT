package DataAccess.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataAccess.Helpers.akaoSQLiteDataHelper;

public class akaoAlimentoDAO {

    public boolean akaoCreateAlimento(String tipoAlimento) {

        String sqlGetId = "SELECT IdAlimentoTipo FROM akaoAlimentoTipo WHERE Nombre = ?";

        String sqlInsert = "INSERT INTO akaoAlimento (IdAlimentoTipo, Cantidad, Estado) VALUES (?, 1, 'A')";

        try (Connection conn = akaoSQLiteDataHelper.akaoGetConnection();
             PreparedStatement pstmtGet = conn.prepareStatement(sqlGetId);
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {

            pstmtGet.setString(1, tipoAlimento);
            ResultSet rs = pstmtGet.executeQuery();

            if (rs.next()) {
                int idTipo = rs.getInt("IdAlimentoTipo");

                pstmtInsert.setInt(1, idTipo);
                pstmtInsert.executeUpdate();
                return true;
            }
            return false; 

        } catch (SQLException e) {
            System.out.println("Error SQL Alimento: " + e.getMessage());
            return false;
        }
    }
}
