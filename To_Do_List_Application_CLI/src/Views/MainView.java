package Views;

import java.util.Scanner;

public class MainView {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static void redirectInput(int response){
        Authorization authorization = new Authorization();
        switch (response) {
            case 1 -> authorization.registrationForCustomer();
            case 2 -> authorization.login();
            case 3 -> System.exit(0);
        }
    }

    public static void runApplication(){
        int response;
        System.out.println("Welcome in Task Manager Application");
        while (true) {
            System.out.println("--------------------");
            System.out.println("1- Registration");
            System.out.println("2- Login");
            System.out.println("3- Exit");
            System.out.println("--------------------");
            System.out.print("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                redirectInput(response);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }
}
