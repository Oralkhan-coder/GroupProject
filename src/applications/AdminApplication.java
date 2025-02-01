package applications;

import controllers.interfaces.IAdminController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final IAdminController adminController;
    public AdminApplication(IAdminController adminController) {
        this.adminController = adminController;
    }

    private void adminMenu() {
        System.out.println();
        System.out.println("Welcome to Admin Panel");
        System.out.println("Here you can delete a wrong accounts and courses");
        System.out.println("Select one of the following options:");
        System.out.println("1. Delete an user");
        System.out.println("2. Delete a course");
        System.out.println("3. See all Users by Role");
        System.out.println("0. Exit");
    }

    public void startForAdmin() {
        if(isAdmin()) {
            while (true) {
                adminMenu();
                try {
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            deleteAnUser();
                            break;
                        case 2:
                            deleteACourse();
                            break;
                        case 3:
                            startChoosingRole();
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
        } else {
            System.out.println("You are NOT ALLOWED");
            System.out.println("----------------------------------------");
        }
    }

    private boolean isAdmin() {
        System.out.println();
        System.out.println("Enter your email to Enter Admin Panel");
        String email = scanner.nextLine();

        return adminController.isAdmin(email);
    }

    private void deleteAnUser() {
        System.out.println();
        System.out.println("Here you can delete an user");
        System.out.println("Enter a user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(adminController.deleteAnUser(userId));
    }

    private void deleteACourse() {
        System.out.println();
        System.out.println("Here you can delete a course");
        System.out.println("Enter a course ID: ");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println(adminController.deleteACourse(courseId));
    }

    private void roleMenu() {
        System.out.println();
        System.out.println("Here you can see the users by Role");
        System.out.println("Select one of the following options:");
        System.out.println("1. Lecture");
        System.out.println("2. Student");
        System.out.println("0. Exit");
    }
    public void startChoosingRole() {
        while (true) {
            roleMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        System.out.println(adminController.getUsersByRole("lecturer"));
                        break;
                    case 2:
                        System.out.println(adminController.getUsersByRole("student"));
                        break;
                    default:return;
                }
            } catch (InputMismatchException exception){
                System.out.println("Please enter a valid option!" + exception);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}