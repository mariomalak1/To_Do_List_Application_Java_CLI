package Models;

import Models.Validation.RequiredField;

import java.util.Objects;

public class Task {

    @RequiredField
    private Integer ID;
    @RequiredField
    private User user;

    @RequiredField
    private String Name;

    // priority is in 4 levels, as 1 is the important && necessary , 4 is unimportant && unnecessary
    @RequiredField
    private Integer Priority;

    // if status is done it will be true
    @RequiredField
    private Boolean Status;

    private String Description;


    public Task(String name, String description, Integer priority, Boolean status, User user) {
        Name = name;
        Description = description;
        Priority = priority;
        Status = status;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Priority=" + Priority +
                ", Status=" + Status +
                ", User_ID=" + (user != null? user.getID() : "" ) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return getID().equals(task.getID()) && getName().equals(task.getName()) && Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getPriority(), task.getPriority()) && Objects.equals(getStatus(), task.getStatus()) && Objects.equals(getUser(), task.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName(), getDescription(), getPriority(), getStatus());
    }

    public Task(Integer ID, String name, String description, Boolean status, Integer priority, User user) {
        this.ID = ID;
        Name = name;
        Description = description;
        Status = status;
        Priority = priority;
        this.user = user;
    }

    public Task(){
        this(null, null, null, null, null);
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
