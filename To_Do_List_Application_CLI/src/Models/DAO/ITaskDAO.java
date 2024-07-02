package Models.DAO;

import Models.Task;
import Models.User;

import java.sql.*;
import java.util.List;

public interface ITaskDAO {
    public void add(Task task);

    public Task get(int id);

    public Boolean delete(Task task);

    public Boolean update(Task task1, Task task2);

    List<Task> getAllTaskForUser(User user);
}
