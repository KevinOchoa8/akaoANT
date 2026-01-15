package DataAccess.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class akaoSQLiteDataHelper {
    
    private static final String DB_URL = "jdbc:sqlite:storage\\Databases\\antCiberDron.sqlite"; 

    public static Connection akaoGetConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
