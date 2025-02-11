package repositories.interfaces;

import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    boolean updateUser(int id, String username, String password, String email, String role, int level);
    User getUserByEmail(String email);
    User getUserById(int id);
    User getStudentWithCourses(int id);
    List<User> getAllUsers();
    List<User> getAllStudents();
    List<User> getUserByRole(String role);
}
