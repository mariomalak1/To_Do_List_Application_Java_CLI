package Models.DAO;

import Models.User;

import java.util.List;

public interface IUserDAO {
    void add(User user);
    User get(int id);

    Boolean delete(User o);

    Boolean update(User o1, User o2);
    List<User> getAllUsers();
    Boolean UserNameExist(String username);
}
