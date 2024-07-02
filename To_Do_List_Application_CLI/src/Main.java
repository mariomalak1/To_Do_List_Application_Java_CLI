import Models.DAO.UserDAO;
import Models.User;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        userDAO.add(new User("marioMalak", "password", "email"));
//        userDAO.add(new User("alabdSefen", "password", "email"));
        System.out.println(userDAO.getAllUsers());
        System.out.println(userDAO.UserNameExist("marioMala"));
    }
}