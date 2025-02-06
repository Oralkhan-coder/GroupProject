package repositories.interfaces;

import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User getUserById(int id);
    List<User> getUserByRole(String role);

    boolean updateUser(int id, String username, String password, String email, String role, int level);


    List<User> getAllStudents();

    User getStudentWithCourses(int id);
}
