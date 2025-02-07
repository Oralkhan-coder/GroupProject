package controllers.interfaces;

public interface IRegistrationController {
    String registerForCourse(Long courseId, String email);
    String getAllCoursesByUserEmail(String email);
    String logoutFromCourse(String email, Long courseId);
    String getStudentFullInfo(int studentId);
}