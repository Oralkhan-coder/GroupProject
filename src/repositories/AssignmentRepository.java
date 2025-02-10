package repositories;

import data.interfaces.JB;
import models.Assignment;
import repositories.interfaces.IAssignmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentRepository implements IAssignmentRepository {
    private final JB database;

    public AssignmentRepository(JB database) {
        this.database = database;
    }



    @Override
    public boolean addAssignment(String title, String description, LocalDateTime deadline, Long courseId) {

        try {
            Connection connection = database.getConnection();
            String sql = "INSERT INTO assignments (title, description, deadline, course_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setObject(3, deadline);
            statement.setLong(4, courseId);

            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAssignment(Long id) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM assignments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public Assignment getAssignmentById(Long id) {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM assignments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Assignment(
                        result.getLong("id"),
                        result.getString("title"),
                        result.getString("description"),
                        (LocalDateTime) result.getObject("deadline"),
                        result.getLong("course_id")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Assignment> getAllAssignmentByCourseId(Long courseId) {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments WHERE course_id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, courseId);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    assignments.add(new Assignment(
                            result.getLong("id"),
                            result.getString("title"),
                            result.getString("description"),
                            (LocalDateTime) result.getObject("deadline"),
                            result.getLong("course_id")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }

        return assignments.isEmpty() ? Collections.emptyList() : assignments;
    }
}
