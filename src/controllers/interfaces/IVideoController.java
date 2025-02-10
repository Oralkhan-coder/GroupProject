package controllers.interfaces;

import models.Video;

public interface IVideoController {
    String createNewVideo(Long courseId, String title, String file);
    String deleteVideo(Long id);
    String getAllCourseVideos(Long courseId);
    String getVideo(Long id);
}
