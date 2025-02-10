package repositories;

import data.interfaces.JB;
import models.Video;
import repositories.interfaces.IVideoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoRepository implements IVideoRepository {

    private final JB database;

    public VideoRepository(JB database) {
        this.database = database;
    }

    @Override
    public Video getById(Long id) {
        try {
            Connection connection = database.getConnection();
            String sql = "SELECT * FROM videos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Video(
                    result.getLong("id"),
                    result.getString("title"),
                    result.getString("file_path"),
                    result.getLong("course_id")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Video> getAllByCourseId(Long courseId) {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT * FROM videos WHERE course_id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, courseId);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    videos.add(new Video(
                            result.getLong("id"),
                            result.getString("title"),
                            result.getString("file_path"),
                            result.getLong("course_id")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }

        return videos.isEmpty() ? Collections.emptyList() : videos;
    }

    @Override
    public boolean createNewVideoForCourse(Long courseId, String title, String file) {

        try {
            Connection connection = database.getConnection();
            String sql = "INSERT INTO videos (title, file_path, course_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, title);
            statement.setString(2, file);
            statement.setLong(3, courseId);

            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteVideo(Long id) {
        try {
            Connection connection = database.getConnection();
            String sql = "DELETE FROM videos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
