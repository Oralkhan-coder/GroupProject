package applications;

import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController controller;
    private final CourseApplication courseApplication;
    private final Scanner scanner=new Scanner(System.in);
    public MyApplication(IUserController controller, CourseApplication courseApplication) {
        this.controller = controller;
        this.courseApplication = courseApplication;
    }

    private void mainMenu(){
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select one of the following options:");
        System.out.println("1. Sign up");
        System.out.println("2. Log in");
        System.out.println("3. See all users");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (1-3): ");
    }

    public void start(){
        while(true){
            mainMenu();
            try{
                int option = scanner.nextInt();
                scanner.nextLine();
                switch(option){
                    case 1: signUp();
                    break;
                    case 2: login();
                    break;
                    case 3: getAllUsersMenu();
                    break;
                    default:return;
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter a valid option!" + e);
                scanner.nextLine(); //to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }


    private void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    private void signUp() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your role: ");
        String role = scanner.nextLine();
        String text = controller.createUser(username, password, email, role);
        System.out.println(text);
        if (text.equals("User created")){
            courseApplication.startForCourse();
        }
    }

    private void login() {
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        String text = controller.login(email, password);
        System.out.println(text);
        if (text.equals("Login successful")){
            courseApplication.startForCourse();
        }
    }
}

