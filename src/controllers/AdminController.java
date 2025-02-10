package controllers;

import controllers.interfaces.IAdminController;
import models.Course;
import models.User;
import repositories.interfaces.IAdminRepository;
import repositories.interfaces.ICourseRepository;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class AdminController implements IAdminController {
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;
    private final IAdminRepository adminRepository;
    public AdminController(IUserRepository userRepository, ICourseRepository courseRepository, IAdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.courseRepository = courseRepository;
    }
    @Override
    public String deleteAnUser(int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            return "User do not exist";
        }
        return adminRepository.deleteAnUser(id) ? "User deleted SUCCESSFULLY" : "User NOT_FOUND";
    }

    @Override
    public String deleteACourse(Long id) {
        Course course = courseRepository.getCourseById(id);
        if (course == null) {
            return "Course do not exist";
        }
        return adminRepository.deleteACourse(id) ? "Course deleted SUCCESSFULLY" : "Course NOT_FOUND";
    }

    @Override
    public String getUsersByRole(String role) {
        List<User> users = adminRepository.getUsersByRole(role);
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(user.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isAdmin(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            System.out.println("(user == null) is true");
            return false;
        }
        if (!user.getRole().trim().equalsIgnoreCase("admin")) {
            System.out.println("(user.getRole() != \"admin\")");
            return false;
        }
        return true;
    }
}