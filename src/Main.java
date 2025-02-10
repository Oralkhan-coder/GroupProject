import applications.AdminApplication;
import applications.HomeApplication;
import applications.MainApplication;
import applications.service.AssigmentService;
import applications.service.CourseService;
import applications.service.VideoService;
import controllers.*;
import data.Postgre;
import data.interfaces.JB;
import repositories.*;
import repositories.interfaces.*;


public class Main {
    public static void main(String[] args) {
        JB database = new Postgre("jdbc:postgresql://localhost:5432",
                "postgres", "Oral2007", "moodle");


        IUserRepository userRepository = new UserRepository(database);
        ICourseRepository courseRepository = new CourseRepository(database);
        IAdminRepository adminRepository = new AdminRepository(database);
        IRegistrationRepository regRepository = new RegistrationRepository(database);
        IVideoRepository videoRepository = new VideoRepository(database);
        IAssignmentRepository assignmentRepository = new AssignmentRepository(database);


        UserController userController = new UserController(userRepository);
        CourseController courseController = new CourseController(userRepository, courseRepository);
        AdminController adminController = new AdminController(userRepository, courseRepository, adminRepository);
        RegistrationController registrationController = new RegistrationController(userRepository, courseRepository, regRepository);
        VideoController videoController = new VideoController(videoRepository);
        AssignmentController assignmentController = new AssignmentController(assignmentRepository);


        CourseService courseService = new CourseService(courseController, registrationController);
        VideoService videoService = new VideoService(videoController);
        AssigmentService assigmentService = new AssigmentService(assignmentController);


        AdminApplication adminApplication = new AdminApplication(adminController);
        HomeApplication homeApplication = new HomeApplication(userController, adminApplication, courseService, videoService, assigmentService);
        MainApplication application = new MainApplication(userController, homeApplication);


        application.startMainMenu();
    }
}

