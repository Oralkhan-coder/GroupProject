package controllers;

import controllers.interfaces.ISubmissionController;
import models.Course;
import models.Submission;
import models.User;
import repositories.interfaces.ICourseRepository;
import repositories.interfaces.ISubmissionRepository;
import repositories.interfaces.IUserRepository;

import java.time.LocalDateTime;
import java.util.List;

public class SubmissionController implements ISubmissionController {
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;
    private final ISubmissionRepository submissionRepository;

    public SubmissionController(IUserRepository userRepository, ICourseRepository courseRepository, ISubmissionRepository submissionRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public String addSubmission(String email, Long assignmentId, String file, LocalDateTime date) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return "User not found";
        }
        List<Course> courses = courseRepository.getAllCoursesByLecturerId(user.getId());
        if (courses.isEmpty()) {
            return "Course not found";
        }
        boolean isHavaAccess = false;
        for (Course course : courses) {
            if (course.getLecturerId() == user.getId()) {
                isHavaAccess = true;
                break;
            }
        }
        if (!isHavaAccess) {
            return "You are not registered in this course. \n Therefor you do not have access to this assignment!";
        }
        return submissionRepository.addSubmission(assignmentId, user.getId(), file, date) ?
                "Submission added successfully" : "Failed";
    }

    @Override
    public String deleteSubmission(Long id) {
        Submission submission = submissionRepository.getSubmissionById(id);
        if (submission == null) {
            return "Submission NOT_FOUND";
        }
        return submissionRepository.deleteSubmission(id) ?
                "Submission deleted successfully" : "Failed";
    }

    @Override
    public String updateSubmission(String email, Long id, String file, LocalDateTime date) {
        User user = userRepository.getUserByEmail(email);
        List<Course> courses = courseRepository.getAllCoursesByLecturerId(user.getId());
        boolean isHavaAccess = false;
        for (Course course : courses) {
            if (course.getLecturerId() == user.getId()) {
                isHavaAccess = true;
                break;
            }
        }
        if (!isHavaAccess) {
            return "You are not registered in this course. \n Therefor you do not have access to this assignment!";
        }
        return submissionRepository.updateSubmission(id, file, date) ?
                "Submission updated successfully" : "Failed";
    }

    @Override
    public String giveGradeAndFeedback(String email, Long id, float grade, String feedback) {
        User user = userRepository.getUserByEmail(email);
        List<Course> courses = courseRepository.getAllCoursesByLecturerId(user.getId());
        boolean isHavaAccess = false;
        for (Course course : courses) {
            if (course.getLecturerId() == user.getId()) {
                isHavaAccess = true;
                break;
            }
        }
        if (!isHavaAccess) {
            return "You are not registered in this course. \n Therefor you do not have access to this assignment!";
        }
        return submissionRepository.updateSubmission(id, grade, feedback) ?
                "Submission updated successfully" : "Failed";
    }

    @Override
    public String getSubmissionById(Long id) {
        Submission submission = submissionRepository.getSubmissionById(id);
        if (submission == null) {
            return "Submission NOT_FOUND";
        }
        return submission.toString();
    }

    @Override
    public String getSubmissionsByAssignmentId(Long assignmentId) {
        List<Submission> submissions = submissionRepository.getSubmissionsByAssignmentId(assignmentId);
        if (submissions.isEmpty()) {
            return "No Submissions found";
        }
        StringBuilder result = new StringBuilder();
        for (Submission submission : submissions) {
            result.append(submission.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public String getSubmissionsByStudentId(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return "User not found";
        }
        List<Submission> submissions = submissionRepository.getSubmissionsByStudentId(user.getId());
        if (submissions.isEmpty()) {
            return "No Submissions found";
        }
        StringBuilder result = new StringBuilder();
        for (Submission submission : submissions) {
            result.append(submission.toString()).append("\n");
        }
        return result.toString();
    }
}
