import applications.CourseApplication;
import applications.MyApplication;
import controllers.CourseController;
import controllers.UserController;
import controllers.interfaces.IUserController;
import data.Postgre;
import data.interfaces.JB;
import repositories.CourseRepository;
import repositories.UserRepository;
import repositories.interfaces.ICourseRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        JB database = new Postgre("jdbc:postgresql://localhost:5432",
                "postgres", "Oral2007", "moodle");
        IUserRepository repo = new UserRepository(database);
        IUserController controller = new UserController(repo);
        ICourseRepository courseRepo = new CourseRepository(database);
        CourseController courseController = new CourseController(courseRepo, repo);
        CourseApplication courseApp = new CourseApplication(controller, courseController);
        MyApplication app = new MyApplication(controller, courseApp);
        app.start();
        database.close();
    }
}

