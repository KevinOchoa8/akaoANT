package DataAccess.DAOs;

import DataAccess.Helpers.akaoSQLiteDataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class akaoHormigaDAO {

public boolean akaoCreateHormiga(String tipoHormiga) {
        String sqlGetId = "SELECT IdHormigaTipo FROM akaoHormigaTipo WHERE Nombre = ?";

        String sqlInsert = "INSERT INTO akaoHormiga (IdHormigaTipo, IdSexo, IdEstado, Nombre, Estado) VALUES (?, ?, 1, 'Hormiga_' || hex(randomblob(4)), 'A')"; 

        try (Connection conn = akaoSQLiteDataHelper.akaoGetConnection();
             PreparedStatement pstmtGet = conn.prepareStatement(sqlGetId);
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {

            pstmtGet.setString(1, tipoHormiga);
            ResultSet rs = pstmtGet.executeQuery();

            if (rs.next()) {
                int idTipo = rs.getInt("IdHormigaTipo");

                int idSexo = 3; // Por defecto Asexual (1=Macho, 2=Hembra, 3=Asexual)
                
                if (tipoHormiga.equals("HZángano")) {
                    idSexo = 1; 
                } else if (tipoHormiga.equals("HReina")) {
                    idSexo = 2; 
                } 
               
                pstmtInsert.setInt(1, idTipo);
                pstmtInsert.setInt(2, idSexo); 
                pstmtInsert.executeUpdate();
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return false;
        }
    }
    
    public java.util.List<BusinessLogic.Entities.akaoHormigaBL> akaoReadAll() {
        java.util.List<BusinessLogic.Entities.akaoHormigaBL> lista = new java.util.ArrayList<>();
        String sql = "SELECT h.IdHormiga, t.Nombre as Tipo, s.Nombre as Sexo, e.Nombre as Estado " +
                     "FROM akaoHormiga h " +
                     "JOIN akaoHormigaTipo t ON h.IdHormigaTipo = t.IdHormigaTipo " +
                     "JOIN akaoSexo s ON h.IdSexo = s.IdSexo " +
                     "JOIN akaoEstado e ON h.IdEstado = e.IdEstado " +
                     "WHERE h.Estado = 'A'"; 

        try (Connection conn = akaoSQLiteDataHelper.akaoGetConnection();
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BusinessLogic.Entities.akaoHormigaBL ant = new BusinessLogic.Entities.akaoHormigaBL(
                    rs.getInt("IdHormiga"),
                    rs.getString("Tipo"),
                    rs.getString("Sexo"),
                    rs.getString("Estado")
                );
                lista.add(ant);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer hormigas: " + e.getMessage());
        }
        return lista;
    }
}
