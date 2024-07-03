package Views;

import Controllers.UserController;
import Models.User;

import java.util.Scanner;

public class UserView {
    User user;
    UserController userController;

    public UserView(User user){
        this.user = user;
    }

    public void registrationForCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Registration page");

        System.out.println("Enter Your UserName : ");
        String username = scanner.nextLine();

        System.out.println("Enter Your Email : ");
        String email = scanner.nextLine();

        if (email.equals("")){
            // Consume the newline character left by nextInt()
            scanner.nextLine();
        }

        System.out.println("Enter Your password : ");
        String password = scanner.nextLine();

        User user = new User(username, password, email);
        userController.createUser(user);
    }


    public void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Login Page");

        System.out.println("Enter Your Username : ");
        String username = scanner.nextLine();

        System.out.println("Enter Your Password : ");
        String password = scanner.nextLine();

        User user = userController.login(username, password);

        if (user != null){
            this.user = user;
            this.HomePage();
        }
    }

    public void logout(){
        userController.logout(this.user);
        MainView.runApplication();
    }

    public void HomePage(){
        int response;
        System.out.println("Hello in Toffee Project");
        while (true) {
            System.out.println("--------------------");
            System.out.println("1- Add Task");
            System.out.println("2- Get All Tasks");
            System.out.println("0- Logout");
            System.out.println("--------------------");
            System.out.print("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                redirectInput(response);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }

    private void redirectInput(int response){
        switch (response) {
            case 0 -> this.logout();
        }
    }
}
