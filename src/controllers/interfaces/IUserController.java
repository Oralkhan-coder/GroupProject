package controllers.interfaces;

public interface IUserController {
    String createUser(String username, String password,String email,String role);
    String login(String email, String password);
    String getAllUsers();
    Boolean isLecturer(String email);
    String updateUser(int id, String username, String password, String email, String role);
    String getUserById(int id);
    String getUserByRole(String role);
}
