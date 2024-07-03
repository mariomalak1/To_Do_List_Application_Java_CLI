package Models;

import java.util.Objects;
import java.time.LocalDateTime;

public class Task {
    private Integer ID;

    private User user;

    private String Name;

    // priority is in 4 levels, as 1 is the important && necessary , 4 is unimportant && unnecessary
    private Integer Priority;

    // if status is done it will be true
    private Boolean Status;

    private String Description;

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", user=" + user +
                ", Name='" + Name + '\'' +
                ", Priority=" + Priority +
                ", Status=" + Status +
                ", Description='" + Description + '\'' +
                ", DateTime=" + DateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return getID().equals(task.getID()) && Objects.equals(getUser(), task.getUser()) && Objects.equals(getName(), task.getName()) && Objects.equals(getPriority(), task.getPriority()) && Objects.equals(getStatus(), task.getStatus()) && Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getDateTime(), task.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getUser(), getName(), getPriority(), getStatus(), getDescription(), getDateTime());
    }

    private LocalDateTime DateTime;

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public static enum Priorities{
        IMPORTANT_NECESSARY(1),
        IMPORTANT_UNNECESSARY(2),
        UNIMPORTANT_NECESSARY(3),
        UNIMPORTANT_UNNECESSARY(4);

        private final int value;

        Priorities(int value) {
            this.value = value;
        }

        public static Priorities getPriorityByValue(int v){
            for (Priorities priority : Priorities.values()) {
                if (priority.getValue() == v) {
                    return priority;
                }
            }
            return null;
        }

        public int getValue() {
            return value;
        }
    }

    public Task(){
        this(null, null, null, null, null, null);
    }

    public Task(User user, String name, Integer priority, Boolean status, String description, LocalDateTime dateTime) {
        this.user = user;
        Name = name;
        Priority = priority;
        Status = status;
        Description = description;
        DateTime = dateTime;
    }

    public Task(Integer ID, String name, String description, Boolean status, Integer priority, User user, LocalDateTime dateTime) {
        this.ID = ID;
        Name = name;
        Description = description;
        Status = status;
        Priority = priority;
        this.user = user;
        DateTime = dateTime;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        if (priority < 0){
            priority = 1;
        }
        if (priority > 4){
            priority = 4;
        }

        Priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
