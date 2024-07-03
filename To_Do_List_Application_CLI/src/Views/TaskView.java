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
        if (task.getStatus()){
            task.setStatus(false);
        }else{
            task.setStatus(true);
        }
        taskController.updateTask(task.getID(), task);
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
            case 3 -> this.changeSpecificTask(task);
//            case 4 -> this.changeSpecificTask(task);
//            case 5 -> this.changeSpecificTask(task);
            case 6 -> new UserView(user).HomePage();
        }
    }
}

