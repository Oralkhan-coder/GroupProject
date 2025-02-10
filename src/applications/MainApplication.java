package applications;

import controllers.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApplication {
    private final Scanner scanner=new Scanner(System.in);

    private final UserController userController;

    private final HomeApplication homeApplication;

    public MainApplication(UserController userController, HomeApplication homeApplication) {
        this.userController = userController;

        this.homeApplication = homeApplication;
    }



    private void mainMenu(){
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select one of the following options:");
        System.out.println("1. Sign up");
        System.out.println("2. Log in");
        System.out.println("0. Exit");
        System.out.print("Select an option (1-2): ");
    }

    public void startMainMenu(){
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
                    default:return;
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter a valid option!" + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------");
        }
    }

    private void signUp() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your role(student/lecturer): ");
        String role = scanner.nextLine();
        System.out.println("Enter your level: ");
        int level = scanner.nextInt();
        String text = userController.createUser(username, password, email, role,level);
        System.out.println(text);
        if (text.equals("User created")){
            homeApplication.startHomeMenu();
        }
    }
    private void login() {
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        String text = userController.login(email, password);
        System.out.println(text);
        if (text.equals("Login successful")){
            homeApplication.startHomeMenu();
        }
    }

}
