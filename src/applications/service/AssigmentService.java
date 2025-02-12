package applications.service;

import controllers.AssignmentController;
import controllers.SubmissionController;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AssigmentService {
    private final Scanner scanner = new Scanner(System.in);
    private final AssignmentController assignmentController;
    private final SubmissionController submissionController;

    public AssigmentService(AssignmentController assignmentController, SubmissionController submissionController) {
        this.assignmentController = assignmentController;
        this.submissionController = submissionController;
    }



    private void menuAssignmentForTeacher() {
        System.out.println();
        System.out.println("Here you can manipulate your course's assignments");
        System.out.println("Select one of the following options:");
        System.out.println("1. Add a new assignment for a course");
        System.out.println("2. Delete an assignment from a course");
        System.out.println("3. See all assignments in a course");
        System.out.println("4. See all students submissions in an assignment");
        System.out.println("5. Add grade and feedback to some submissions");
        System.out.println("0. Exit");
    }

    public void startAssignmentServiceForTeacher() {
        while (true) {
            try {
                menuAssignmentForTeacher();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        createAssignment();
                        break;
                    case 2:
                        deleteAssignment();
                        break;
                    case 3:
                        getAllAssignments();
                        break;
                    case 4:
                        getAllStudnetsSubmissions();
                        break;
                    case 5:
                        giveGradeAndFeedback();
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

    private void createAssignment() {
        System.out.println();
        System.out.println("Enter the course ID which this assignment will be belong to");
        Long courseID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter title of new assignment");
        String title = scanner.nextLine();
        System.out.println("Enter description of new assignment");
        String description = scanner.nextLine();
        System.out.println("Enter deadline of new assignment in formant YYYY-MM-DD HH:mm:SS");
        LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine());
        System.out.println(assignmentController.addAssignment(title, description, deadline, courseID));
    }
    private void deleteAssignment() {
        System.out.println();
        System.out.println("Enter the ID of the assignment that you want to delete");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println(assignmentController.deleteAssignment(id));
    }
    private void getAllAssignments() {
        System.out.println();
        System.out.println("Enter the course ID to get all assignments");
        Long courseID = scanner.nextLong();
        scanner.nextLine();
        System.out.println(assignmentController.getAllByCourseId(courseID));
    }
    private void getAllStudnetsSubmissions() {
        System.out.println();
        System.out.println("Enter the assignment ID to get all students submissions");
        Long assignmentID = scanner.nextLong();
        scanner.nextLine();
        System.out.println(submissionController.getSubmissionsByAssignmentId(assignmentID));
    }
    private void giveGradeAndFeedback() {
        System.out.println();
        System.out.println("Enter your email address");
        String email = scanner.nextLine();
        System.out.println("Enter the submission ID to get grade and feedback");
        Long submissionID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter the grade which you want to give");
        float grade = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Enter the feedback");
        String feedback = scanner.nextLine();
        System.out.println(submissionController.giveGradeAndFeedback(email, submissionID, grade, feedback));
    }
}
