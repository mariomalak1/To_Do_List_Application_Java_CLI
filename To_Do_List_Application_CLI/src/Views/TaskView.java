package Views;

import Controllers.TaskController;
import Models.Task;
import Models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TaskView {
    private User user;
    private TaskController taskController;

    public TaskView(User u){
        this.user = u;
        taskController = new TaskController(this.user);
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

    public void showSpecificTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Task ID: ");
        String IdString = scanner.nextLine();
        if (!MainView.isNumeric(IdString)){
            System.out.println("Please enter valid value.");
            return;
        }
        int id = Integer.parseInt(IdString);

        Task task = taskController.getTaskByID(id);
        if (task != null){
            this.changeSpecificTask(task);
        }
    }

    public void changeTaskStatus(Task task){
        task.setStatus(!task.getStatus());
        taskController.updateTask(task.getID(), task);
    }

    public void changeTaskPriority(Task task){
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
            changeSpecificTask(task);
            return;
        }

        task.setPriority(priority);
        taskController.updateTask(task.getID(), task);
        System.out.println("task Changed.");
    }

    public void changeTaskDescription(Task task){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Task New Description: ");
        String description = scanner.nextLine();
        task.setDescription(description);
        taskController.updateTask(task.getID(), task);
        System.out.println("task Changed.");
    }

    public void changeTaskName(Task task){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Task New Name : ");
        String name = scanner.nextLine();
        if (name.equals("")){
            System.out.println("Not Allowed To Make Task Name Empty.");
            changeTaskName(task);
            return;
        }
        task.setName(name);
        taskController.updateTask(task.getID(), task);
        System.out.println("task Changed.");
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

    public void changeSpecificTask(Task task){
        int response;
        System.out.println(task);
        while (true) {
            System.out.println("--------------------");
            System.out.println("1- Display Task Details");
            System.out.println("2- Change Task Status");
            System.out.println("3- Change Task Priority");
            System.out.println("4- Change Task Description");
            System.out.println("5- Change Task Name");
            System.out.println("6- Go Home");
            System.out.println("--------------------");
            System.out.print("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                redirectInput(response, task);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }

    private void redirectInput(int response, Task task){
        switch (response) {
            case 1 -> System.out.println(task);
            case 2 -> this.changeTaskStatus(task);
            case 3 -> this.changeTaskPriority(task);
            case 4 -> this.changeTaskDescription(task);
            case 5 -> this.changeTaskName(task);
            case 6 -> new UserView(user).HomePage();
        }
    }
}

