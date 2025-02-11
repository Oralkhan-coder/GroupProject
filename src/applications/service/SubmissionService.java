package applications.service;

import controllers.AssignmentController;
import controllers.SubmissionController;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubmissionService {
    private final AssignmentController assignmentController;
    private final SubmissionController submissionController;
    private final Scanner scanner = new Scanner(System.in);

    public SubmissionService(AssignmentController assignmentController, SubmissionController submissionController) {
        this.submissionController = submissionController;
        this.assignmentController = assignmentController;
    }

    private void menuAssignmentSubmission() {
        System.out.println();
        System.out.println("Here you can submit your assignment");
        System.out.println("Select one of the following options:");
        System.out.println("1. Show all my submissions");
        System.out.println("2. Show all assignments of a specific course");
        System.out.println("3. Submit an assignment");
        System.out.println("4. Delete an assignment");
        System.out.println("5. Update an assignment");
        System.out.println("0. Exit");
    }

    public void startAssignmentSubmission() {
        while (true) {
            try {
                menuAssignmentSubmission();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        getAllAssignmentByUserId();
                        break;
                    case 2:
                        getAllAssignmentByCourseId();
                        break;
                    case 3:
                        addNewAssignment();
                        break;
                    case 4:
                        deleteAssignment();
                        break;
                    case 5:
                        updateAssignment();
                        break;
                    default:
                        return;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid option!");
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }
    }

    private void getAllAssignmentByUserId() {
        System.out.println();
        System.out.println("Please enter your email address:");
        String email = scanner.nextLine();
        System.out.println(submissionController.getSubmissionsByStudentId(email));
    }
    private void getAllAssignmentByCourseId() {
        System.out.println();
        System.out.println("Please enter the course ID:");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println(assignmentController.getAllByCourseId(courseId));
    }
    private void addNewAssignment() {
        System.out.println();
        System.out.println("Please enter your email address:");
        String email = scanner.nextLine();
        System.out.println("Please enter the assignment ID which you want to submit:");
        Long assignmentId = scanner.nextLong();
        System.out.println("Please enter the file path:");
        String file = scanner.nextLine();
        System.out.println(submissionController.addSubmission(email, assignmentId, file, LocalDateTime.now()));
    }
    private void deleteAssignment() {
        System.out.println();
        System.out.println("Please enter submission ID which you want to delete:");
        Long submissionId = scanner.nextLong();
        System.out.println(submissionController.deleteSubmission(submissionId));
    }
    private void updateAssignment() {
        System.out.println();
        System.out.println("Please enter your email address:");
        String email = scanner.nextLine();
        System.out.println("Please enter the submission ID which you want to update:");
        Long submissionId = scanner.nextLong();
        System.out.println("Please enter new file path:");
        String file = scanner.nextLine();
        System.out.println(submissionController.updateSubmission(email, submissionId, file, LocalDateTime.now()));
    }
}
