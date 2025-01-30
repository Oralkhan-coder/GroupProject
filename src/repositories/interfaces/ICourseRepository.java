package repositories.interfaces;

import models.Course;

import java.util.List;

public interface ICourseRepository {
    boolean updateCourse(Long courseId, String name, String description);
    boolean deleteCourse(Long courseId, String email);
    boolean createCourse(Course course);
    Course getCourseById(Long courseId);
    List<Course> getAllCourses();
    List<Course> getAllCoursesByLecturerId(int lecturerId);
}
