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
            String sql = "INSERT INTO course_registrations (user_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setLong(2, courseId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Error in RegistrationRepository");
        }
        return false;
    }

    @Override
    public List<Course> getCoursesByUserId(int userId) {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "SELECT * FROM courses c " +
                             "JOIN course_registrations sc ON c.id = sc.course_id " +
                             "WHERE sc.user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                courses.add(new Course(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getInt("lecturer_id"),
                        result.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @Override
    public boolean logoutFromCourse(int userId, Long courseId) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM course_registrations WHERE user_id = ? AND course_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);
            statement.setLong(2, courseId);
            statement.execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
