package Views;

import Controllers.TaskController;
import Models.Task;
import Models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TasksView {
    private User user;
    private TaskController taskController;

    TasksView(User user){
        this.user = user;
        taskController = new TaskController(user);
    }

    public void createTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Create New Task page");

        System.out.print("Enter Your Task Name : ");
        String taskName = scanner.nextLine();

        if (taskName.equals("")){
            System.out.println("Must task have name");
            createTask();
        }

        System.out.print("Enter description for it : ");
        String description = scanner.nextLine();

        System.out.print("Enter description for it : ");
        String priorityString = scanner.nextLine();

        if (!MainView.isNumeric(priorityString)){
            System.out.println("Must enter numeric value for priority");
            createTask();
        }

        Integer priority = Integer.parseInt(priorityString);

        Task task =  new Task(user, taskName, priority, false, description, LocalDateTime.now());
        taskController.createTask(task);
    }

    public void showAllTasks(){
        List<Task> tasks = taskController.getAllTaskForUser();
        for (Task task: tasks){
            System.out.println("Task Name : " + task.getName() + "Priority : " + Task.Priorities.getPriorityByValue(task.getPriority()) + "Status : " + task.getStatus());
        }
    }


    public void getCompletedTasks(){
        List<Task> tasks = taskController.getCompletedTask();
        if (tasks.size() == 0){
            System.out.println("No Tasks Completed.");
            return;
        }
        int i = 0;
        for (Task task: tasks){
            i++;
            System.out.println("--------------------");
            System.out.println("Task " + i + ":");
            System.out.println(task);
            System.out.println("--------------------");
        }
    }

    public void getUnCompletedTasks(){
        List<Task> tasks = taskController.getCompletedTask();
        if (tasks.size() == 0){
            System.out.println("No Tasks Completed.");
            return;
        }
        int i = 0;
        for (Task task: tasks){
            i++;
            System.out.println("--------------------");
            System.out.println("Task " + i + ":");
            System.out.println(task);
            System.out.println("--------------------");
        }
    }


    public void getTasksWithSpecificPriority(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Task New Priority: ");
        String priorityString = scanner.nextLine();
        if (!MainView.isNumeric(priorityString)){
            System.out.println("Please enter valid value.");
            return;
        }
        int priority = Integer.parseInt(priorityString);

        if (priority < 0 || priority > 4){
            System.out.println("please Enter Valid Priority Level From 0 to 4.");
            new UserView(user).HomePage();
        }
    }

    public void getTasksWithSearchInName_Description(){

    }
}
