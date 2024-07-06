package Models.DAO;

import java.sql.Connection;

public class DataBaseConnection {
    public static void main(String [] args) {
        // test DB connection
        try{
            DataBaseManager manager = DataBaseManager.getDataBaseManager();
            Connection connection = manager.getConnection();
            if (connection == null){
                System.out.println("DB Connection isn't open.");
                System.exit(0);
            }
        }
        catch (Exception e){
            System.out.println("DB Connection isn't open.");
            System.exit(0);
        }
    }
}
