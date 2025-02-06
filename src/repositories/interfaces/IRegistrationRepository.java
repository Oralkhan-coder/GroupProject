package repositories.interfaces;

import models.Course;

import java.util.List;

public interface IRegistrationRepository {
    boolean registerForCourse(Long courseId, int userId);
    List<Course> getCoursesByUserId(int userId);
    boolean logoutFromCourse(int userId, Long courseId);
}