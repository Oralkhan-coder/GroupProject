package controllers.interfaces;

import java.time.LocalDateTime;

public interface IAssignmentController {
    String addAssignment(String title, String description, LocalDateTime deadline, Long courseId);
    String deleteAssignment(Long id);
    String getById(Long id);
    String getAllByCourseId(Long courseId);
}
