package Models.DAO;

import Models.Task;
import Models.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String [] args) throws SQLException {
        TaskDAO taskDAO = new TaskDAO();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(5);
        List<Task> tasks = taskDAO.getTaskWithFiltration(user, "task",  null, true, null);
//        taskDAO.getAllTaskForUser(user);
        System.out.println(user);
        for (Task t :tasks){
            System.out.println(t);
        }
//        System.out.println(taskDAO.getAllTaskForUser(user));
    }
}
