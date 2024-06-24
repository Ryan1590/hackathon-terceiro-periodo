package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    public static final String URL = "jdbc:mysql://localhost/hackathon_vacina";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
