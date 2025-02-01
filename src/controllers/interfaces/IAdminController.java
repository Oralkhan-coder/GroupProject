package controllers.interfaces;

public interface IAdminController {
    String deleteAnUser(int id);
    String deleteACourse(Long id);
    String getUsersByRole(String role);
    boolean isAdmin(String email);
}