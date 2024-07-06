package Views;

import Controllers.UserController;
import Models.User;

import java.util.Scanner;

public class Authorization {
    private UserController userController;

    Authorization(){
        userController = new UserController();
    }

    public void registrationForCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Registration page");

        System.out.println("Enter Your UserName : ");
        String username = scanner.nextLine();

        System.out.println("Enter Your Email : ");
        String email = scanner.nextLine();

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
            new UserView(user).HomePage();
        }
    }
}
