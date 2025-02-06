package models;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private int level;
    private List<Course> courses;
    private boolean isMentor;

    public User(int id, String username, String password, String email, String role, int level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.level = level;
        this.isMentor = canBeMentor();
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public int getLevel() {
        return level;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean isMentor() {
        return isMentor;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean canBeMentor() {
        return role.equals("STUDENT") && level > 1;
    }
}
