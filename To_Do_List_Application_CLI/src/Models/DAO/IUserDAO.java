package Models.DAO;

import Models.User;

import java.util.List;

public interface IUserDAO extends IModelDAO<User> {
    List<User> getAllUsers();
    Boolean UserNameExist(String username);
}
