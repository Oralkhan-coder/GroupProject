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
        User user=userRepository.getUserByEmail(email);

        if(user==null){
            return "User not found";
        }
        if (!user.getEmail().equals(email) && !user.getPassword().equals(password)) {
            return "Wrong DATA";
        }

        return "Login successful";
    }

    @Override
    public String createUser(String username, String password, String email, String role) {
        User newUser = new User(username, password, email, role);
        boolean result = userRepository.createUser(newUser);
        return result ? "User created" : "User not created";
    }

    @Override
    public String updateUser(int id, String username, String password, String email, String role) {
        return "";
    }

    @Override
    public String getUserById(int userId) {
        User user = userRepository.getUserById(userId);
        return (user != null) ? user.toString() : "User NOT_FOUND";
    }

    @Override
    public String getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(user.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public Boolean isLecturer(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        if (user.getRole().equalsIgnoreCase("lecturer")) {
            return false;
        }
        return true;
    }

    @Override
    public String getUserByRole(String role) {
        return "";
    }

}
