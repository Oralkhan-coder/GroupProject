package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            return "User not found";
        }
        if (!user.getPassword().equals(password)) {
            return "Wrong password";
        }
        return "Login successful";
    }

    @Override
    public String createUser(String username, String password, String email, String role, int level) {
        User newUser = new User(0, username, password, email, role, level);
        boolean result = userRepository.createUser(newUser);
        return result ? "User created" : "User not created";
    }

    @Override
    public String updateUser(int id, String username, String password, String email, String role, int level) {
        boolean result = userRepository.updateUser(id, username, password, email, role, level);
        return result ? "User updated successfully" : "User update failed";
    }

    @Override
    public String getUserById(int userId) {
        User user = userRepository.getUserById(userId);
        return (user != null) ? user.toString() : "User NOT_FOUND";
    }

    @Override
    public String getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        if (users.isEmpty()) {
            return "No users found";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(user.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public Boolean isLecturer(String email) {
        User user = userRepository.getUserByEmail(email);
        return user != null && user.getRole().equalsIgnoreCase("LECTURER");
    }

    @Override
    public String getUserByRole(String role) {
        List<User> users = userRepository.getUserByRole(role);
        if (users.isEmpty()) {
            return "No users found with role: " + role;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(user.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public List<User> getAllStudents() {
        return userRepository.getAllStudents();
    }

    public User getStudentWithCourses(int id) {
        return userRepository.getStudentWithCourses(id);
    }
}
