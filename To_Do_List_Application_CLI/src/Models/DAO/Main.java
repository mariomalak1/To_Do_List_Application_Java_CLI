package Models.DAO;

import Models.Task;
import Models.User;

import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException {
        TaskDAO taskDAO = new TaskDAO();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(5);
        System.out.println(user);
        System.out.println(taskDAO.getAllTaskForUser(user));
    }
}
