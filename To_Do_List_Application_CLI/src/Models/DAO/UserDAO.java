package Models.DAO;

import Models.Task;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDAO implements IUserDAO{

    private final DataBaseManager dataBaseManager;

    public UserDAO(){
        dataBaseManager = DataBaseManager.getDataBaseManager();
    }

    @Override
    public void add(User user) {
        Connection connection = dataBaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            int recordsAffected = preparedStatement.executeUpdate();
            if (recordsAffected == 0){
                System.out.println("Can't add this user right now, please check your database connection.");
            }
        } catch (SQLException e) {
            System.out.println("Can't add this user right now, please check your database connection.");
        }
    }

    @Override
    public User get(int id) {
        Connection connection = dataBaseManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where id = " + id);

            if (!resultSet.next()){
                System.out.println("No User With This ID.");
                return null;
            }
            return extractUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        Connection connection = dataBaseManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                users.add(extractUserFromResultSet(resultSet));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean UserNameExist(String username) {
        Connection connection = dataBaseManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where username = " + "'" + username + "'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("Password");
        Boolean logged = resultSet.getBoolean("logged");

        User user = new User(id, logged, username, email, password);
        user.setID(id);
        return user;
    }
}
