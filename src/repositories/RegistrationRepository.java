package repositories;

import data.interfaces.JB;
import models.User;
import models.Course;
import repositories.interfaces.IRegistrationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationRepository implements IRegistrationRepository {
    private final JB database;

    public RegistrationRepository(JB database) {
        this.database = database;
    }

    @Override
    public boolean registerForCourse(Long courseId,int userId) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO student_courses (user_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, courseId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Course> getCoursesByUserId(int userId) {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "SELECT c.id, c.name FROM courses c " +
                             "JOIN student_courses sc ON c.id = sc.course_id " +
                             "WHERE sc.student_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                courses.add(new
                        Course(result.getString("name"), result.getString("discription"), result.getLong("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @Override
    public boolean logoutFromCourse(int userId, Long courseId) {
        return false;
    }
}
