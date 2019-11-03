package clinica.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String dbName = "clinica";

    public static void setDbName(String name) {
        dbName = name;
    }

    public java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
           Class.forName("com.mysql.jdbc.Driver");
           String urlBD="jdbc:mysql://localhost:3306/" + dbName;
           return DriverManager.getConnection(urlBD, "root", "root");
    }
}
