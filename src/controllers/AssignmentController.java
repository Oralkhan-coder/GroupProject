package controllers;

import controllers.interfaces.IAssignmentController;
import models.Assignment;
import repositories.interfaces.IAssignmentRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AssignmentController implements IAssignmentController {
    private final IAssignmentRepository assignmentRepository;
    public AssignmentController(IAssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    @Override
    public String addAssignment(String title, String description, LocalDateTime deadline, Long courseId) {
        try {
            boolean result = assignmentRepository.addAssignment(title, description, deadline, courseId);
            return result ? "Assignment added" : "Failed";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Failed";
    }

    @Override
    public String deleteAssignment(Long id) {
        try {
            boolean result = assignmentRepository.deleteAssignment(id);
            return result ? "Assignment deleted" : "Failed";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Failed";
    }


    @Override
    public String getById(Long id) {
        Assignment assignment = assignmentRepository.getAssignmentById(id);

        if (assignment == null) {
            return "Assignment NOT_FOUND";
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = assignment.getDeadline();
        Duration duration = Duration.between(now, deadline);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        return assignment + "\n \t \t (Remaining time: " + days + " days, " + hours + " hours, " + minutes + " minutes)";
    }


    @Override
    public String getAllByCourseId(Long courseId) {
        List<Assignment> assignments = assignmentRepository.getAllAssignmentByCourseId(courseId);
        if (assignments.isEmpty()) {
            return "No videos found";
        }
        StringBuilder result = new StringBuilder();
        for (Assignment assignment : assignments) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime deadline = assignment.getDeadline();
            Duration duration = Duration.between(now, deadline);

            long days = duration.toDays();
            long hours = duration.toHours() % 24;
            long minutes = duration.toMinutes() % 60;

            result.append(assignment)
                    .append("\n \t \t (Remaining time: " + days + " days, " + hours + " hours, " + minutes + " minutes)")
                    .append("\n").append("\n").append("\n");
        }



        return result.toString();
    }
}
