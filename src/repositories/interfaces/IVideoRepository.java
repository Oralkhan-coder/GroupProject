package repositories.interfaces;

import models.Video;

import java.util.List;

public interface IVideoRepository {
    Video getById(Long id);
    List<Video> getAllByCourseId(Long courseId);
    boolean createNewVideoForCourse(Long courseId, String title, String file);
    boolean deleteVideo(Long id);
}
