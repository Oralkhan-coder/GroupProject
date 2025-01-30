package controllers.interfaces;

import java.util.Date;

public interface ICourseController {
    String createCourse(String name, String description, Long lecturerId);
    String deleteCourse(Long courseId, String email);
    String updateCourse(Long courseId, String name, String description);
    String getCourseById(Long courseId);
    String getAllCourses();
    String getAllCoursesByEmail(String email);
}
