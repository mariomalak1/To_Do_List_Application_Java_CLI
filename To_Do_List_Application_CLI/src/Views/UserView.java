package Views;

import Controllers.UserController;
import Models.User;

import java.util.Scanner;

public class UserView {
    public static void registrationForCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Registration page");

        System.out.println("Enter Your UserName : ");
        String username = scanner.nextLine();

        System.out.println("Enter Your Email : ");
        String email = scanner.nextLine();

        System.out.println("Enter Your password : ");
        String password = scanner.nextLine();

        UserController customerController = new UserController();
        User user = new User(username, email, password);
        user = customerController.createUser(user);

        if (user == null){
            System.out.println("UserName is already taken by someone else, please enter another one");
        }else {
            System.out.println("Registration Done Successfully, You Can Login Now");
            System.out.println("You UserName : " + user.getUserName() + " Remember It As You Will Login With It");
        }
    }


    public static void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Login Page");

        System.out.println("Enter Your Username : ");
        String username = scanner.nextLine();

        System.out.println("Enter Your Password : ");
        String password = scanner.nextLine();

        UserController userController = new UserController();
        User user = userController.login(username, password);

        if (user != null){
            HomePage(user);
        }
    }

    public static void Logout(Models.User user){
        UserController.logout(user);
        MainView.runApplication();
    }

    public static void HomePage(User user){

    }

}
