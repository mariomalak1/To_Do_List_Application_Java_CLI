package Controllers;

import Models.User;
import Models.DAO.UserDAO;

import java.sql.SQLException;

public class UserController {
    private static final UserDAO userDAO;

    static {
        userDAO = new UserDAO();
    }

    public User createUser(User user) {
        if (userDAO.UserNameExist(user.getUserName())){
            return null;
        }
        user = userDAO.add(user);
        return user;
    }

    public User login(String username, String password){
        User user = userDAO.getUserByUserName(username);
        if (user == null){
            System.out.println("No User with this username, you can go to register page and register new one with this username, or try agian with different username.");
            return null;
        }
        if (user.getPassword().equals(password)){
            user.setLogged(true);
            if (userDAO.update(user.getUserName(), user)){
                System.out.println("Successfully Login.");
                return user;
            }else{
                System.out.println("Something wrong in data.");
            }
        }
        else{
            System.out.println("Password is invalid.");
        }
        return null;
    }

    public static Boolean logout(User user){
        user.setLogged(false);
        System.out.println("Logout successfully.");
        userDAO.update(user.getUserName(), user);
        return true;
    }

}
