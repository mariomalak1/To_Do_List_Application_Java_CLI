package Models;

import java.util.Objects;

public class Task {
    private Integer ID;
    private String Name;
    private String Description;

    // priority is in 4 levels, as 1 is the important && necessary , 4 is unimportant && unnecessary
    private Integer Priority;

    // if status is done it will be true
    private Boolean Status;


    public Task(String name, String description, Integer priority, Boolean status) {
        Name = name;
        Description = description;
        Priority = priority;
        Status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Priority=" + Priority +
                ", Status=" + Status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return getID().equals(task.getID()) && getName().equals(task.getName()) && Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getPriority(), task.getPriority()) && Objects.equals(getStatus(), task.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName(), getDescription(), getPriority(), getStatus());
    }

    public Task(Integer ID, String name, String description, Boolean status, Integer priority) {
        this.ID = ID;
        Name = name;
        Description = description;
        Status = status;
        Priority = priority;
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

}
