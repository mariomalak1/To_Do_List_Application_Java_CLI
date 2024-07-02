package Models.DAO;

import Models.Task;

import java.sql.*;

public class TaskDAO implements IModelDAO<Task>{
    private final DataBaseManager dataBaseManager;

    TaskDAO(){
        dataBaseManager = DataBaseManager.getDataBaseManager();
    }

    @Override
    public void add(Task task) {
        Connection connection = dataBaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks (name, description, status, priority) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setBoolean(3, task.getStatus());
            preparedStatement.setInt(4, task.getPriority());
            int recordsAffected = preparedStatement.executeUpdate();
            if (recordsAffected == 0){
                System.out.println("Can't add this task right now, please check your database connection.");
            }
        } catch (SQLException e) {
            System.out.println("Can't add this task right now, please check your database connection.");
        }
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
    public Boolean update(Task task1, Task task2) {
        return null;
    }
}
