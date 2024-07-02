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
}
