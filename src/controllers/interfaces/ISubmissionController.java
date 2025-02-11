package controllers.interfaces;

import java.time.LocalDateTime;

public interface ISubmissionController {
    String addSubmission(String email, Long assignmentId, String file, LocalDateTime date);
    String deleteSubmission(Long id);
    String updateSubmission(String email, Long id,  String file, LocalDateTime date);
    String giveGradeAndFeedback(String email, Long id, float grade, String feedback);
    String getSubmissionById(Long id);
    String getSubmissionsByAssignmentId(Long assignmentId);
    String getSubmissionsByStudentId(String email);
}
