package Models.DAO;

import Models.User;

import java.util.List;
import java.sql.*;

public class UserDAO implements IUserDAO{

    private final DataBaseManager dataBaseManager;

    UserDAO(){
        dataBaseManager = DataBaseManager.getDataBaseManager();
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User get(int id) {
        return new User();
    }

    @Override
    public Boolean delete(User user) {
        return null;
    }

    @Override
    public Boolean update(User user1, User user2) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Boolean UserNameExist(String username) {
        return null;
    }
}
