package Models.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException {
        // connection test
        DataBaseManager dataBaseManager = DataBaseManager.getDataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        connection.close();
        System.out.println("Connection Done");
    }
}
