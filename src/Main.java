import applications.AdminApplication;
import applications.CourseApplication;
import applications.MyApplication;
import controllers.AdminController;
import controllers.CourseController;
import controllers.RegistrationController;
import controllers.UserController;
import controllers.interfaces.IAdminController;
import controllers.interfaces.ICourseController;
import controllers.interfaces.IRegistrationController;
import controllers.interfaces.IUserController;
import data.Postgre;
import data.interfaces.JB;
import repositories.AdminRepository;
import repositories.CourseRepository;
import repositories.RegistrationRepository;
import repositories.UserRepository;
import repositories.interfaces.IAdminRepository;
import repositories.interfaces.ICourseRepository;
import repositories.interfaces.IRegistrationRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        JB database = new Postgre("jdbc:postgresql://localhost:5432",
                "postgres", "kometa0707", "users");

        IUserRepository repo = new UserRepository(database);
        ICourseRepository courseRepo = new CourseRepository(database);
        IRegistrationRepository regRepo = new RegistrationRepository(database);
        IAdminRepository adminRepo = new AdminRepository(database);

        UserController controller = new UserController(repo);
        CourseController courseController = new CourseController(courseRepo, repo);
        IRegistrationController regController = new RegistrationController(courseRepo, repo, regRepo);
        IAdminController adminController = new AdminController(repo, adminRepo, courseRepo);

        CourseApplication courseApp = new CourseApplication(controller, courseController, regController);
        AdminApplication adminApplication = new AdminApplication(adminController);
        MyApplication app = new MyApplication(controller, courseApp, adminApplication);
        app.start();
        database.close();
    }
}

