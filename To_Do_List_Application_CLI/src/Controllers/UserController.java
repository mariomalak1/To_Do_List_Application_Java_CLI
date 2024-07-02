package Controllers;

import Models.User;
import Models.DAO.UserDAO;
import Models.Validation.ValidateEntity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private static final UserDAO userDAO;

    static {
        userDAO = new UserDAO();
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Password Encryption Failed");
            return password;
        }
    }

    private static boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }

    public User createUser(User user) {
        if (user == null){
            return null;
        }

        // check that the required fields are not empty
        List<String> emptyRequiredFields = ValidateEntity.validate(user, "Password", "UserName");
        if (emptyRequiredFields.size() > 0){
            System.out.println(ValidateEntity.RequiredFieldsInString(emptyRequiredFields));
            return null;
        }

        if (userDAO.UserNameExist(user.getUserName())){
            System.out.println("This username is already taken before");
            return null;
        }

        // hash password
        user.setPassword(hashPassword(user.getPassword()));
        user = userDAO.add(user);


        if (user == null){
            System.out.println("there's some error");
            return null;
        }
        System.out.println("Registration Done Successfully, You Can Login Now");
        System.out.println("You UserName : " + user.getUserName() + " Remember It As You Will Login With It");

        return user;
    }

    public User login(String username, String password){
        User user = userDAO.getUserByUserName(username);
        if (user == null){
            System.out.println("No User with this username, you can go to register page and register new one with this username, or try agian with different username.");
            return null;
        }

        if (verifyPassword(password, user.getPassword())){
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
