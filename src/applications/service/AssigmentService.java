package applications.service;

import controllers.AssignmentController;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AssigmentService {
    private final Scanner scanner = new Scanner(System.in);
    private final AssignmentController assignmentController;

    public AssigmentService(AssignmentController assignmentController) {
        this.assignmentController = assignmentController;
    }

    public void startAssignmentServiceForStudent() {
        System.out.println("Here will be an assigment service");
    }




    private void menuAssignmentForTeacher() {
        System.out.println();
        System.out.println("Here you can manipulate your course's assignments");
        System.out.println("Select one of the following options:");
        System.out.println("1. Add a new assignment for a course");
        System.out.println("2. Delete an assignment from a course");
        System.out.println("3. See all assignments in a course");
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
}
