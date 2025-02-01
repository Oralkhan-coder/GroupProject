package repositories.interfaces;

import models.User;

import java.util.List;

public interface IAdminRepository {
    List<User> getUsersByRole(String role);
    boolean deleteAnUser(int id);
    boolean deleteACourse(Long id);
}