package repositories;

import data.interfaces.JB;
import models.Submission;
import repositories.interfaces.ISubmissionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubmissionRepository implements ISubmissionRepository {

    private final JB database;
    public SubmissionRepository(JB database) {
        this.database = database;
    }

    @Override
    public boolean addSubmission(Long assignmentId, int studentId, String file, LocalDateTime date) {
        try {
            Connection connection = database.getConnection();
            String sql = "INSERT INTO submissions (assignment_id, student_id, file_path, submitted_at)" +
                    " VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, assignmentId);
            statement.setLong(2, studentId);
            statement.setString(3, file);
            statement.setObject(4, date);

            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteSubmission(Long submissionId) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM submissions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, submissionId);
            statement.execute();
            return true;
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateSubmission(Long submissionId, String file, LocalDateTime date) {
        try {
            Connection connection = database.getConnection();
            String sql = "UPDATE submission SET file_path = ?, submitted_at = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, file);
            statement.setObject(2, date);
            statement.setLong(3, submissionId);

            statement.execute();
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateSubmission(Long submissionId, float grade, String feedback) {
        try {
            Connection connection = database.getConnection();
            String sql = "UPDATE submission SET grade = ?, feedback = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setFloat(1, grade);
            statement.setString(2, feedback);
            statement.setLong(3, submissionId);

            statement.execute();
            return true;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    @Override
    public Submission getSubmissionById(Long submissionId) {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM submissions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, submissionId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Submission(
                        result.getLong("id"),
                        result.getLong("assignment_id"),
                        result.getLong("student_id"),
                        result.getInt("grade"),
                        result.getString("file_path"),
                        result.getString("feedback"),
                        result.getTimestamp("submitted_at").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Submission> getSubmissionsByAssignmentId(Long assignmentId) {
        List<Submission> submissions = new ArrayList<>();
        String sql = "SELECT * FROM submissions WHERE assignment_id = ?";
        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, assignmentId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                submissions.add(new Submission(
                        result.getLong("id"),
                        result.getLong("assignment_id"),
                        result.getLong("student_id"),
                        result.getInt("grade"),
                        result.getString("file_path"),
                        result.getString("feedback"),
                        result.getTimestamp("submitted_at").toLocalDateTime()
                ));
            }

        } catch(SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return submissions;
    }

    @Override
    public List<Submission> getSubmissionsByStudentId(int studentId) {
        List<Submission> submissions = new ArrayList<>();
        String sql = "SELECT * FROM submissions WHERE student_id = ?";

        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, studentId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                submissions.add(new Submission(
                        result.getLong("id"),
                        result.getLong("assignment_id"),
                        result.getLong("student_id"),
                        result.getInt("grade"),
                        result.getString("file_path"),
                        result.getString("feedback"),
                        result.getTimestamp("submitted_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return submissions;
    }
}
