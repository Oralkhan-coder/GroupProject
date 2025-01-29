package repositories.interfaces;

import controllers.UserController;
import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByRole(String role);

    boolean updateUser(int id, String username, String password, String email, String role);
}
