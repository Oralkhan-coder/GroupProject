package controllers.interfaces;

import java.util.List;
import models.User;

public interface IUserController {
    String createUser(String username, String password, String email, String role, int level);
    String login(String email, String password);
    String getAllUsers();
    Boolean isLecturer(String email);
    String updateUser(int id, String username, String password, String email, String role, int level);
    String getUserById(int id);
    String getUserByRole(String role);
    List<User> getAllStudents();
    User getStudentWithCourses(int id);
}
