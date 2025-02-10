package controllers.interfaces;

public interface ICourseController {
    String createCourse(String email, String name, String description);
    String deleteCourse(Long courseId, String email);
    String updateCourse(String email, Long courseId, String name, String description);
    String getCourseById(Long courseId);
    String getAllCourses();
    String getAllCoursesByEmail(String email);
}
