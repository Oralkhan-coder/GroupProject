package repositories.interfaces;

import models.Assignment;

import java.time.LocalDateTime;
import java.util.List;

public interface IAssignmentRepository {
    boolean addAssignment(String title, String description, LocalDateTime deadline, Long courseId);
    boolean deleteAssignment(Long id);
    Assignment getAssignmentById(Long id);
    List<Assignment> getAllAssignmentByCourseId(Long courseId);
}
