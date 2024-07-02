package Models.DAO;

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
    public User add(User user) {
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
            // to get generated ID for task
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    user.setID(generatedID);
                    return user;
                } else {
                    System.out.println("No ID get to user, as it failed to save it.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't add this user right now, please check your database connection.");
        }
        return null;
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
    public User getUserByUserName(String username) {
        Connection connection = dataBaseManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where username = " + "'" + username + "'");

            if (!resultSet.next()){
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
    public Boolean update(String username, User user1) {
        Connection connection = dataBaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ?, email = ?, password = ?, logged = ? WHERE username = ?");
            preparedStatement.setString(1, user1.getUserName());
            preparedStatement.setString(2, user1.getEmail());
            preparedStatement.setString(3, user1.getPassword());
            preparedStatement.setBoolean(4, user1.getLogged());
            preparedStatement.setString(5, username);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
