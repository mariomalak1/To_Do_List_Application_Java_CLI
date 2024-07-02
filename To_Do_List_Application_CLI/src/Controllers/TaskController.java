package Controllers;

import Models.DAO.TaskDAO;
import Models.Task;

public class TaskController {
    private static final TaskDAO taskDAO;

    static {
        taskDAO = new TaskDAO();
    }

    public Task createTask(Task task){
        Task task1 = taskDAO.add(task);
        if (task1 == null){
            System.out.println("There's an error while save task, try agian.");
            return null;
        }
        return task1;
    }
}
