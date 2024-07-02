package Models.DAO;

import Models.User;

import java.util.List;

public interface IUserDAO {
    User add(User user);
    User get(int id);

    User getUserByUserName(String username);

    Boolean delete(User o);

    Boolean update(String Username, User user);
    List<User> getAllUsers();
    Boolean UserNameExist(String username);
}
