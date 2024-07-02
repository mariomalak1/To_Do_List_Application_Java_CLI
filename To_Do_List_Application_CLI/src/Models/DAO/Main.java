package Models.DAO;

import Models.Task;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException {
        TaskDAO taskDAO = new TaskDAO();
        Task task = new Task("Mario task1", "", 1, false);
        taskDAO.add(task);
        System.out.println(taskDAO.get(1));
    }
}
