package controllers;

import controllers.interfaces.IRegistrationController;
import models.Course;
import models.User;
import repositories.interfaces.ICourseRepository;
import repositories.interfaces.IRegistrationRepository;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class RegistrationController implements IRegistrationController {
    private final ICourseRepository courseRepository;
    private final IUserRepository userRepository;
    private final IRegistrationRepository regRepository;

    public RegistrationController(ICourseRepository courseRepository, IUserRepository userRepository,
                                  IRegistrationRepository regRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.regRepository = regRepository;
    }

    @Override
    public String registerForCourse(Long courseId, String email) {
        if (courseRepository.getCourseById(courseId) == null || courseId <= 0) {
            return "Wrong course ID";
        }
        User user = userRepository.getUserByEmail(email);
        if (userRepository.getUserByEmail(email) == null) {
            return "Wrong user email";
        }
        return regRepository.registerForCourse(courseId, user.getId()) ? "Course Registered" : "Something went wrong";
    }

    @Override
    public String getStudentFullInfo(int studentId) {
        User user = userRepository.getUserById(studentId);
        if (user == null) {
            return "Student not found.";
        }
        if (!user.getRole().equals("admin") && !user.getRole().equals("lecturer")) {
            return "Access denied. Only admins and lecturers can request student information.";
        }

        List<Course> courses = regRepository.getCoursesByUserId(studentId);
        StringBuilder studentFullInfo = new StringBuilder();
        for (Course course : courses) {
            studentFullInfo.append(course.toString()).append("\n");
        }

        return studentFullInfo.toString();
    }


    @Override
    public String getAllCoursesByUserEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return "Wrong user email";
        }
        StringBuilder string = new StringBuilder();
        List<Course> courses = regRepository.getCoursesByUserId(user.getId());
        for (Course course : courses) {
            string.append(course.getName()).append("\n");
        }
        if (string.toString().isEmpty()) {
            return "You do not have any registered course";
        }
        return string.toString();
    }

    @Override
    public String logoutFromCourse(String email, Long courseId) {
        if (courseRepository.getCourseById(courseId) == null || courseId <= 0) {
            return "Wrong course ID";
        }
        User user = userRepository.getUserByEmail(email);
        if (userRepository.getUserByEmail(email) == null) {
            return "Wrong user email";
        }
        boolean res =  regRepository.logoutFromCourse(user.getId(), courseId);
        return res ? "Course Logged Out" : "Something went wrong";
    }
}