package Models.DAO;

import Models.Task;
import Models.User;

import java.sql.*;
import java.util.List;

public interface ITaskDAO {
    Task add(Task task);

    Task get(int id);

    Boolean delete(Task task);

    Boolean update(int taskID, Task task2);

    List<Task> getAllTaskForUser(User user);

    List<Task> getTaskWithFiltration(User user, String name, String description, Boolean status, Integer priority);
}
