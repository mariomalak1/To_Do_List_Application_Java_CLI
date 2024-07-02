package Views;

import Controllers.UserController;
import Models.User;

import java.util.Scanner;

public class UserView {
    User user;

    public UserView(User user){
        this.user = user;
    }

    public void registrationForCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Registration page");

        System.out.println("Enter Your UserName : ");
        String username = scanner.nextLine();

        if (username.equals("")){
            System.out.println("username can't be empty, try agian");
            registrationForCustomer();
        }

        System.out.println("Enter Your Email : ");
        String email = scanner.nextLine();

        System.out.println("Enter Your password : ");
        String password = scanner.nextLine();

        if (password.equals("")){
            System.out.println("password can't be empty, try agian");
            registrationForCustomer();
        }

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


    public void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Login Page");

        System.out.println("Enter Your Username : ");
        String username = scanner.nextLine();

        System.out.println("Enter Your Password : ");
        String password = scanner.nextLine();

        UserController userController = new UserController();
        User user = userController.login(username, password);

        if (user != null){
            this.user = user;
            this.HomePage();
        }
    }

    public void logout(){
        UserController.logout(this.user);
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
