package Views;

import Controllers.TaskController;
import Models.Task;
import Models.User;

import java.util.List;
import java.util.Scanner;

public class TaskView {
    private User user;
    TaskController taskController;

    public TaskView(User u){
        this.user = u;
    }


    public void createTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Create New Task page");

        System.out.println("Enter Your Task Name : ");
        String taskName = scanner.nextLine();

        if (taskName.equals("")){
            System.out.println("Must task have name");
            createTask();
        }

        System.out.println("Enter description for it : ");
        String description = scanner.nextLine();

        System.out.println("Enter description for it : ");
        String priorityString = scanner.nextLine();

        if (!MainView.isNumeric(priorityString)){
            System.out.println("Must enter numeric value for priority");
            createTask();
        }

        Integer priority = Integer.parseInt(priorityString);

        Task task =  new Task(user, taskName, priority, false, description);
        taskController.createTask(task);
    }

    public void showAllTasks(){
        List<Task> tasks = taskController.getAllTaskForUser();
        for (Task task: tasks){
            System.out.println("Task Name : " + task.getName() + "Priority : " + Task.Priorities.getPriorityByValue(task.getPriority()) + "Status : " + task.getStatus());
        }
    }




}
