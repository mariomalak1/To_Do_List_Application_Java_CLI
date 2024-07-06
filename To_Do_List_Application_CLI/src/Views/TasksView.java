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

        System.out.print("Enter priority for it : ");
        String priorityString = scanner.nextLine();

        if (!MainView.isNumeric(priorityString)){
            System.out.println("Must enter numeric value for priority");
            return;
        }

        Integer priority = Integer.parseInt(priorityString);

        Task task =  new Task(user, taskName, priority, false, description, LocalDateTime.now());
        taskController.createTask(task);
    }

    public void showAllTasks(){
        List<Task> tasks = taskController.getAllTaskForUser();
        printListOfTasks(tasks);
    }

    public void getCompletedTasks(){
        List<Task> tasks = taskController.getCompletedTask();
        printListOfTasks(tasks);
    }

    public void getUnCompletedTasks(){
        List<Task> tasks = taskController.getUnCompletedTask();
        printListOfTasks(tasks);
    }

    public void getTasksWithSpecificPriority(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter priority you want to search from 1 to 4: ");
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

        List<Task> tasks = taskController.getTasksWithSpecificPriority(priority);

        printListOfTasks(tasks);
    }

    public void getTasksWithSearchInName_Description(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search key you want to search with name or description: ");
        String searchKey = scanner.nextLine();

        List<Task> tasks = taskController.searchWithNameDescription(searchKey);
        printListOfTasks(tasks);
    }

    private void printListOfTasks(List<Task> tasks){
        if (tasks.size() == 0){
            System.out.println("No Tasks with this search key.");
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
}
