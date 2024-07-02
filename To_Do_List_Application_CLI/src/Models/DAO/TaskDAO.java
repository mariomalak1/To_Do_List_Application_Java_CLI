package Models.DAO;

import Models.Task;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO {
    private final DataBaseManager dataBaseManager;

    public TaskDAO(){
        dataBaseManager = DataBaseManager.getDataBaseManager();
    }

    @Override
    public Task add(Task task) {
        Connection connection = dataBaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks (name, description, status, priority, userID) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setBoolean(3, task.getStatus());
            preparedStatement.setInt(4, task.getPriority());
            preparedStatement.setInt(5, task.getUser().getID());
            int recordsAffected = preparedStatement.executeUpdate();
            if (recordsAffected == 0){
                System.out.println("Can't add this task right now, please check your database connection.");
                return null;
            }
            // to get generated ID for task
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    task.setID(generatedID);
                    return task;
                } else {
                    System.out.println("No ID get to task from, as it failed to save it.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't add this task right now, please check your database connection.");
        }
        return null;
    }

    @Override
    public Task get(int id) {
        Connection connection = dataBaseManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tasks where id = " + id);

            if (!resultSet.next()){
                System.out.println("No Task With This ID.");
                return null;
            }

            Task task = new Task();
            task.setID(resultSet.getInt("ID"));
            task.setName(resultSet.getString("name"));
            task.setDescription(resultSet.getString("description"));
            task.setPriority(resultSet.getInt("priority"));
            task.setStatus(resultSet.getBoolean("status"));
            return task;
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Can't add this task right now, please check your database connection.");
        }
        return null;
    }

    @Override
    public Boolean delete(Task task) {
        return null;
    }

    @Override
    public Boolean update(int taskID, Task task2) {
        return null;
    }

    @Override
    public List<Task> getAllTaskForUser(User user) {
        Connection connection = dataBaseManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tasks where userID = " + user.getID());
            List<Task> tasks = new ArrayList<>();
            while (resultSet.next()){
                Task task = new Task();
                task.setID(resultSet.getInt("ID"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setPriority(resultSet.getInt("priority"));
                task.setStatus(resultSet.getBoolean("status"));
                task.setUser(user);
                tasks.add(task);
            }


            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
