package Models.DAO;

import Models.Task;

import java.sql.Connection;

public class DataBaseConnection {
    public static void main(String [] args) {
        TaskDAO taskDAO = new TaskDAO();

        Task task = taskDAO.get(45);
        System.out.println(task);
//        // test DB connection
//        try{
//            DataBaseManager manager = DataBaseManager.getDataBaseManager();
//            Connection connection = manager.getConnection();
//            if (connection == null){
//                System.out.println("DB Connection isn't open.");
//                System.exit(0);
//            }
//        }
//        catch (Exception e){
//            System.out.println("DB Connection isn't open.");
//            System.exit(0);
//        }
    }
}
