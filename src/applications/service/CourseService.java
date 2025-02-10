package applications.service;

import controllers.CourseController;
import controllers.RegistrationController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseService {
    private final Scanner scanner = new Scanner(System.in);

    private final CourseController courseController;
    private final RegistrationController registrationController;

    public CourseService(CourseController courseController, RegistrationController registrationController) {
        this.courseController = courseController;
        this.registrationController = registrationController;
    }


    private void menuCoursesForStudent() {
        System.out.println();
        System.out.println("Here you can register or logout in courses");
        System.out.println("Select one of the following options:");
        System.out.println("1. Show all courses that i can register");
        System.out.println("2. Show all courses that i have registered");
        System.out.println("3. Register a new course");
        System.out.println("4. Logout from a course");
        System.out.println("0. Exit");
    }

    public void startCourseServiceForStudent() {
        while (true) {
            try {
                menuCoursesForStudent();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        getAllCourses();
                        break;
                    case 2:
                        getAllStudentCourses();
                        break;
                    case 3:
                        registerCourse();
                        break;
                    case 4:
                        logoutFromCourse();
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

    private void getAllCourses() {
        System.out.println();
        System.out.println(courseController.getAllCourses());
    }
    private void getAllStudentCourses() {
        System.out.println();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println(registrationController.getAllCoursesByUserEmail(email));
    }
    private void registerCourse() {
        System.out.println();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter course ID");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println(registrationController.registerForCourse(courseId, email));
    }
    private void logoutFromCourse() {
        System.out.println();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter course ID");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println(registrationController.logoutFromCourse(email, courseId));
    }





    private void menuCoursesForTeacher() {
        System.out.println();
        System.out.println("Here you can manipulate your courses");
        System.out.println("Select one of the following options: ");
        System.out.println("1. Show all my courses");
        System.out.println("2. Create a new course");
        System.out.println("3 Delete a course");
        System.out.println("4. Update a course");
        System.out.println("0. Exit");
    }

    public void startCourseServiceForTeacher() {
        while (true) {
            try {
                menuCoursesForTeacher();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        getAllLecturerCourses();
                        break;
                    case 2:
                        createCourse();
                        break;
                    case 3:
                        deleteCourse();
                        break;
                    case 4:
                        updateCourse();
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


    private void getAllLecturerCourses() {
        System.out.println();
        System.out.println("Please enter your email do get your courses");
        String email = scanner.nextLine();
        System.out.println(courseController.getAllCoursesByEmail(email));
    }
    private void createCourse() {
        System.out.println();
        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your course name");
        String courseName = scanner.nextLine();
        System.out.println("Enter your course description");
        String courseDescription = scanner.nextLine();
        System.out.println(courseController.createCourse(email, courseName, courseDescription));
    }
    private void deleteCourse() {
        System.out.println();
        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your course id");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println(courseController.deleteCourse(courseId, email));
    }
    private void updateCourse() {
        System.out.println();
        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your course id");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter your changed course name");
        String courseName = scanner.nextLine();
        System.out.println("Enter your changed course description");
        String courseDescription = scanner.nextLine();
        System.out.println(courseController.updateCourse(email, courseId, courseName, courseDescription));
    }
}
