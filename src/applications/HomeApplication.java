package applications;

import applications.service.AssigmentService;
import applications.service.CourseService;
import applications.service.SubmissionService;
import applications.service.VideoService;
import controllers.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final UserController userController;
    private final AdminApplication adminApplication;
    private final CourseService courseService;
    private final VideoService videoService;
    private final AssigmentService assigmentService;
    private final SubmissionService submissionService;

    public HomeApplication(UserController userController, AdminApplication adminApplication,
                           CourseService courseService, VideoService videoService,
                           AssigmentService assigmentService, SubmissionService submissionService) {
        this.userController = userController;

        this.adminApplication = adminApplication;

        this.courseService = courseService;
        this.videoService = videoService;
        this.assigmentService = assigmentService;
        this.submissionService = submissionService;
    }


    private void homeMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select one of the following options:");
        System.out.println("1. Service for Students");
        System.out.println("2. Service for Teachers");
        System.out.println("3. Admin Panel");
        System.out.println("0. Log out");
    }

    public void startHomeMenu() {
        while (true) {
            homeMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        startMenuForStudent();
                        break;
                    case 2:
                        startMenuForTeacher();
                        break;
                    case 3:
                        adminApplication.startMenuForAdmin();
                        break;
                    default:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!" + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }


    //    Some services related to Students
    public void menuForStudent() {
        System.out.println();
        System.out.println("Hello Dear Student");
        System.out.println("Select one of the following options: ");
        System.out.println("1. Course Service");
        System.out.println("2. Video service");
        System.out.println("3. Assignment-Submission service");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void startMenuForStudent() {
        while (true) {
            try {
                menuForStudent();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        courseService.startCourseServiceForStudent();
                        break;
                    case 2:
                        videoService.startVideoServiceForStudent();
                        break;
                    case 3:
                        submissionService.startAssignmentSubmission();
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


//    Some services related to Teachers Work
    public void menuForTeacher () {
        System.out.println();
        System.out.println("Hello Lecturer! Good luck for creating a new course!");
        System.out.println("Select one of the following options: ");
        System.out.println("1. Course service");
        System.out.println("2. Video service");
        System.out.println("3. Assignment service");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void startMenuForTeacher () {
        if (isLecturer()) {
            while (true) {
                try {
                    menuForTeacher();
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            courseService.startCourseServiceForTeacher();
                            break;
                        case 2:
                            videoService.startVideoServiceForTeacher();
                            break;
                        case 3:
                            assigmentService.startAssignmentServiceForTeacher();
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
        } else {
            System.out.println("You are not allowed to create courses");
        }
    }

    private boolean isLecturer () {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Please enter your email to determine your Role");
        String email = scanner.nextLine();
        return userController.isLecturer(email);
    }
}

