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
                    System.out.println("No ID get to task, as it failed to save it.");
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
        return getTaskWithFiltration(user, null, null, null, null);
    }

    @Override
    public List<Task> getTaskWithFiltration(User user, String name, String description, Boolean status, Integer priority) {
        Connection connection = dataBaseManager.getConnection();
        try {
            String query ="select * from tasks where userID = ?";
            int parameterNumber = 1;
            if (name != null){
                query += " and name LIKE ?";
            }
            if (description != null){
                query += " and description LIKE ?";
            }
            if (status != null){
                query += " and status = ?";
            }
            if (priority != null){
                query += " and priority = ?";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getID());
            if (name != null){
                parameterNumber++;
                preparedStatement.setString(parameterNumber,"%" + name + "%");
            }
            if (description != null){
                parameterNumber++;
                preparedStatement.setString(parameterNumber, "%" + description + "%");
            }
            if (status != null){
                parameterNumber++;
                preparedStatement.setBoolean(parameterNumber, status);
            }
            if (priority != null){
                parameterNumber++;
                preparedStatement.setInt(parameterNumber, priority);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
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
