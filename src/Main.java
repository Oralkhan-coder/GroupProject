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
        UserController controller = new UserController(repo);
        ICourseRepository courseRepo = new CourseRepository(database);
        CourseController courseController = new CourseController(courseRepo, repo);
        IRegistrationRepository regRepo = new RegistrationRepository(database);
        IRegistrationController regController = new RegistrationController(courseRepo, repo, regRepo);
        CourseApplication courseApp = new CourseApplication(controller, courseController, regController);
        IAdminRepository adminRepo = new AdminRepository(database);
        IAdminController adminController = new AdminController(repo, adminRepo, courseRepo);
        AdminApplication adminApplication = new AdminApplication(adminController);
        MyApplication app = new MyApplication(controller, courseApp, adminApplication);
        CourseApplication courseApp = new CourseApplication(controller, courseController);
        IAdminRepository adminRepo = new AdminRepository(database);
        AdminController adminController = new AdminController(repo,  adminRepo, courseRepo);
        AdminApplication adminApp = new AdminApplication(adminController);
        MyApplication app = new MyApplication(controller, courseApp, adminApp);
        app.start();
        database.close();
    }
}

