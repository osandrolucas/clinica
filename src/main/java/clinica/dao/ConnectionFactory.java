package clinica.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
           Class.forName("com.mysql.jdbc.Driver");
           String urlBD="jdbc:mysql://localhost:3306/clinica";
           return DriverManager.getConnection(urlBD, "root", "");
    }
}
