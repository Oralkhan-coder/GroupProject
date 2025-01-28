import controllers.UserController;
import controllers.interfaces.IUserController;
import data.Postgre;
import data.interfaces.JB;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JB databsase = new Postgre("jdbc:postgresql://localhost:5432",
                "postgres", "kometa0707", "users");
        IUserRepository repo = new UserRepository(databsase);
        IUserController controller = new UserController(repo);
        MyApplication app = new MyApplication(controller);
        app.start();

        databsase.close();
    }
}

