package repositories.interfaces;

import models.Submission;

import java.time.LocalDateTime;
import java.util.List;

public interface ISubmissionRepository {
    boolean addSubmission(Long assignmentId, int studentId, String file, LocalDateTime date);
    boolean deleteSubmission(Long submissionId);
    boolean updateSubmission(Long submissionId,  String file, LocalDateTime date);
    boolean updateSubmission(Long submissionId, float  grade, String feedback);
    Submission getSubmissionById(Long submissionId);
    List<Submission> getSubmissionsByAssignmentId(Long assignmentId);
    List<Submission> getSubmissionsByStudentId(int studentId);

}
