package repositories;

import data.interfaces.JB;
import models.Course;
import repositories.interfaces.ICourseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseRepository implements ICourseRepository {
    private final JB database;

    public CourseRepository(JB database) {
        this.database = database;
    }


    @Override
    public boolean updateCourse(Long courseId, String name, String description) {
        try {
            Connection connection = database.getConnection();
            String sql = "UPDATE public.courses SET name = ?, description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, courseId);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCourse(Long courseId, String email) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM courses USING users WHERE courses.id = ? " +
                    "AND users.id = courses.lecturer_id AND users.email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, courseId);
            statement.setString(2, email);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean createCourse(Course course) {
        try {
            Connection connection = database.getConnection();
            String sql = "INSERT INTO courses (name, description, lecturer_id, created_at) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setLong(3, course.getLecturerId());
            statement.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));

            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Course getCourseById(Long courseId) {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM courses WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, courseId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Course(result.getLong("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getLong("lecturer_id"),
                        result.getTimestamp("created_at"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM courses";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<Course> courses = new ArrayList<>();
            while (result.next()) {
                Course course = new Course(result.getLong("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getLong("lecturer_id"),
                        result.getTimestamp("created_at"));
                courses.add(course);
            }
            return courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Course> getAllCoursesByLecturerId(int lecturerId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM public.courses WHERE lecturer_id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, lecturerId);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    courses.add(new Course(
                            result.getLong("id"),
                            result.getString("name"),
                            result.getString("description"),
                            result.getLong("lecturer_id"),
                            result.getTimestamp("created_at")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }

        return courses.isEmpty() ? Collections.emptyList() : courses;
    }
}
