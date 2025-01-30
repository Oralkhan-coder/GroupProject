package controllers;

import controllers.interfaces.ICourseController;
import models.Course;
import models.User;
import repositories.interfaces.ICourseRepository;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class CourseController implements ICourseController {
    private final ICourseRepository courseRepo;
    private final IUserRepository userRepo;

    public CourseController(ICourseRepository courseRepo, IUserRepository userRepo) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    @Override
    public String createCourse(String name, String description, Long lecturerId) {
        Course course = new Course(name, description, lecturerId);
        boolean result = courseRepo.createCourse(course);
        return result ? "Course successfully created" : "Failed";
    }

    @Override
    public String deleteCourse(Long courseId, String email) {
        Course course = courseRepo.getCourseById(courseId);
        User user = userRepo.getUserByEmail(email);
        if (course == null) {
            return "Course not found";
        }
        if (course.getLecturerId() != user.getId()) {
            return "You cannot delete this course!";
        }
        return courseRepo.deleteCourse(courseId, email) ? "Successfully deleted" : "Failed";
    }

    @Override
    public String updateCourse(Long courseId, String name, String description) {
        Course course = courseRepo.getCourseById(courseId);
        if (course == null) {
            return "Course not found";
        }
        return courseRepo.updateCourse(courseId, name, description) ? "Successfully updated" : "Failed";
    }

    @Override
    public String getCourseById(Long courseId) {
        Course course = courseRepo.getCourseById(courseId);
        return (course != null) ? course.toString() : "Course NOT_FOUND";
    }

    @Override
    public String getAllCourses() {
        List<Course> courses = courseRepo.getAllCourses();
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : courses) {
            stringBuilder.append(course.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public String getAllCoursesByEmail(String email) {
        User user = userRepo.getUserByEmail(email);
        if (user == null || user.getId() <= 0) {
            return "User NOT_FOUND";
        }

        List<Course> userCourses = courseRepo.getAllCoursesByLecturerId(user.getId());
        if (userCourses.isEmpty()) {
            return "You do not have any course";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : userCourses) {
            stringBuilder.append(course.toString());
        }
        return stringBuilder.toString();
    }
}
