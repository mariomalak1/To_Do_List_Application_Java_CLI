package Models.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseManager {
    private String url;
    private String username;
    private String password;
    private Connection connection;
    private static DataBaseManager dataBaseManager = null;


    public static DataBaseManager getDataBaseManager(){
        if (dataBaseManager == null){
            dataBaseManager = new DataBaseManager();
        }
        return dataBaseManager;
    }

    private DataBaseManager() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("\\db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return;
            }

            // Load a properties file from class path
            properties.load(input);
            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(this.url, this.username, this.password);
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't connect with database");
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("No Database found.");
            System.exit(0);
        }
    }

    public Connection getConnection(){
        try {
            System.out.println("is connection closed : " + connection.isClosed());
        } catch (SQLException e) {
            System.out.println("from get connection.........................");
            e.printStackTrace();
        }
        return connection;
    }
}
