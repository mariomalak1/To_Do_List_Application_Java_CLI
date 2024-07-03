package Models.DAO;

import Models.Task;
import Models.User;

import java.util.List;

public class Main {
    public static void main(String [] args) {
        TaskDAO taskDAO = new TaskDAO();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(8);
        if (user == null){
            System.out.println("no user with this id");
            System.exit(0);
        }
        List<Task> tasks = taskDAO.getAllTaskForUser(user);
        Task task = tasks.stream().findFirst().orElse(null);
        if (task == null) {
            System.out.println("no task found");
            System.exit(0);
        }
        task.setStatus(false);
        task.setPriority(4);
        taskDAO.update(task.getID(), task);
        System.out.println("done");
//        List<Task> tasks = taskDAO.getTaskWithFiltration(user, "task",  null, true, null);
//        System.out.println(user);
//        for (Task t :tasks){
//            System.out.println(t);
        }
//        System.out.println(taskDAO.getAllTaskForUser(user));
//    }
}
