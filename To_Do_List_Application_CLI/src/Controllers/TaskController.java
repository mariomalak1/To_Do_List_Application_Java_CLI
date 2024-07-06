package Controllers;

import Models.DAO.TaskDAO;
import Models.Task;
import Models.User;

import java.util.List;

public class TaskController {
    private static final TaskDAO taskDAO;
    private final User user;

    public TaskController(User user){
        this.user = user;
    }

    static {
        taskDAO = new TaskDAO();
    }

    public Task createTask(Task task) {
        Task task1 = taskDAO.add(task);
        if (task1 == null){
            System.out.println("There's an error while save task, try agian.");
            return null;
        }
        return task1;
    }

    public Task getTaskByID(int taskID){
        Task task = taskDAO.get(taskID);
        if (task == null){
            System.out.println("No Task With This ID.");
            return null;
        }
        if (!user.getID().equals(task.getUser().getID())){
            System.out.println("No Task With This ID, for you.");
            return null;
        }
        return task;
    }

    public List<Task> getAllTaskForUser(){
        return taskDAO.getAllTaskForUser(this.user);
    }

    public List<Task> getCompletedTask(){
        return taskDAO.getTaskWithFiltration(this.user, null, null, true, null);
    }

    public List<Task> getUnCompletedTask(){
        return taskDAO.getTaskWithFiltration(this.user, null, null, false, null);
    }

    public List<Task> getTasksWithSpecificPriority(int priority){
        if (priority < 0 || priority > 4){
            System.out.println("priority must in range 1 to 4.");
            return null;
        }
        return taskDAO.getTaskWithFiltration(this.user, null, null, null, priority);
    }


    public void updateTask(Integer taskID, Task task2){
        Task task1 = taskDAO.get(taskID);
        if (task1 == null){
            System.out.println("No task with this id");
        }
        taskDAO.update(taskID, task2);
    }

}
