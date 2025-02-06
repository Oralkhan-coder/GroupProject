package applications;

import controllers.CourseController;
import controllers.interfaces.IRegistrationController;
import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseApplication {
    private final IUserController userController;
    private final CourseController controller;
    private final IRegistrationController regController;
    private final Scanner scanner = new Scanner(System.in);
    public CourseApplication(IUserController userController, CourseController controller, IRegistrationController regController) {
        this.userController = userController;
        this.controller = controller;
        this.regController = regController;
    }

    public void mainMenuForUsers() {
        System.out.println();
        System.out.println("Welcome to Course Catalog!");
        System.out.println("Select one of the following options: ");
        System.out.println("1. See all courses");
        System.out.println("2. Search one specific courses");
        System.out.println("3. I want to be Creator courses");
        System.out.println("0. Logout");
        System.out.print("Enter your choice: ");
    }

    public void startForCourse() {
        while (true) {
            mainMenuForUsers();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        getAllCourses();
                        break;
                    case 2:
                        getCourseById();
                        break;
                    case 3:
                        startCreatingCourse();
                        break;
                    default:
                        return;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid option!");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }

    public void getAllCourses() {
        System.out.println(controller.getAllCourses());
    }

    public void getCourseById() {
        System.out.println();
        System.out.println("Enter course ID");
        Long courseId = scanner.nextLong();
        System.out.println(controller.getCourseById(courseId));
    }

    public boolean isLecturer() {
        System.out.println();
        System.out.println("Enter Your email to confirm your ROLE:");
        String email = scanner.nextLine();
        return userController.isLecturer(email);
    }

    public void createCourseMenu() {
        System.out.println();
        System.out.println("Hello Lecturer! Good luck for creating a new course!");
        System.out.println("Select one of the following options: ");
        System.out.println("1. Create a new course");
        System.out.println("2. Update an existing course");
        System.out.println("3. Delete an existing course");
        System.out.println("4. See all my courses");
        System.out.println("0. Logout");
        System.out.print("Enter your choice: ");
    }

    public void startCreatingCourse() {
        if (isLecturer()) {
            while (true) {
                try {
                    createCourseMenu();
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            createCourse();
                            break;
                        case 2:
                            updateCourse();
                            break;
                        case 3:
                            deleteCourse();
                            break;
                        case 4:
                            getAllCoursesByEmail();
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


    private void createCourse() {
        System.out.println();
        System.out.println("Hello Lecturer!");
        System.out.println("Enter your course name");
        String name = scanner.nextLine();
        System.out.println("Enter your course description");
        String description = scanner.nextLine();
        System.out.println("Enter your ID:");
        Long lecturerId = scanner.nextLong();
        scanner.nextLine();

        System.out.println(controller.createCourse(name, description, lecturerId));
    }

    private void updateCourse() {
        System.out.println();
        System.out.println("Hello Lecturer!");
        System.out.println("You can update your course here!");
        System.out.println("Enter your course name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your course description");
        String description = scanner.nextLine();
        System.out.println("Enter your course ID: ");
        Long courseId = scanner.nextLong();
        scanner.nextLine();

        System.out.println(controller.updateCourse(courseId, name, description));
    }

    private void deleteCourse() {
        System.out.println();
        System.out.println("Hello Lecturer!");
        System.out.println("You can delete your course here!");
        System.out.println("Enter your course ID: ");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        scanner.nextLine();

        System.out.println(controller.deleteCourse(courseId, email));
    }

    private void getAllCoursesByEmail() {
        System.out.println();
        System.out.println("Hello Lecturer!");
        System.out.println("You can all your courses here!");
        System.out.println("Enter your email to search your courses: ");
        String email = scanner.nextLine();

        System.out.println(controller.getAllCoursesByEmail(email));
    }
    private void registrationForCourseMenu() {
        System.out.println();
        System.out.println("Would you like to register a new course?");
        System.out.println("Choose one of the following options: ");
        System.out.println("1. Register a new course");
        System.out.println("2. Logout from course");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void startRegistrationForCourse() {
        while (true) {
            registrationForCourseMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        registerForCourse();
                        break;
                    case 2:
                        logoutFromCourse();
                        break;
                    default:return;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a valid option!");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void registerForCourse() {
        System.out.println();
        System.out.println("You can register a new course here!");
        System.out.println("Enter your course ID: ");
        Long courseId  = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter your email: ");
        String email  = scanner.nextLine();
        System.out.println();

        System.out.println(regController.registerForCourse(courseId, email));
    }

    private void seeAllCoursesByEmail() {
        System.out.println();
        System.out.println("Here you can see all your courses you registered: ");
        System.out.println("Enter your email to see all your courses: ");
        String email = scanner.nextLine();
        System.out.println(regController.getAllCoursesByUserEmail(email));
    }

    private void logoutFromCourse() {
        System.out.println();
        System.out.println("Here you can logout from your course: ");
        System.out.println("Enter your email to logout: ");
        String email = scanner.nextLine();
        System.out.println("Enter your course ID for logout: ");
        Long courseId = scanner.nextLong();
        scanner.nextLine();

        System.out.println(regController.logoutFromCourse(email, courseId));
    }
}
