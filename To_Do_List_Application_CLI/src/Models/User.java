package Models;

import java.util.Objects;

public class User {
    private Integer ID;
    private Boolean Logged;
    private String Email;
    private String Password;


    public User(Integer ID, Boolean logged, String email, String password) {
        this.ID = ID;
        Logged = logged;
        Email = email;
        Password = password;
    }

    public User() {
        this(null, null, null, null);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Boolean getLogged() {
        return Logged;
    }

    public void setLogged(Boolean logged) {
        Logged = logged;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Logged=" + Logged +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getID().equals(user.getID()) && Objects.equals(getLogged(), user.getLogged()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getLogged(), getEmail(), getPassword());
    }
}
