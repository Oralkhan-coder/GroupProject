package controllers;

import controllers.interfaces.IVideoController;
import models.Video;
import repositories.interfaces.IVideoRepository;

import java.util.List;

public class VideoController implements IVideoController {
    private final IVideoRepository videoRepo;
    public VideoController(IVideoRepository videoRepo) {
        this.videoRepo = videoRepo;
    }


    @Override
    public String createNewVideo(Long courseId, String title, String file) {
        boolean result = videoRepo.createNewVideoForCourse(courseId, title, file);
        return result ? "Video successfully created" : "Failed";
    }

    @Override
    public String deleteVideo(Long id) {
        Video video = videoRepo.getById(id);

        if (video != null) {
            return "Video NOT_FOUND";
        }
        boolean result = videoRepo.deleteVideo(id);
        return result ? "Video successfully deleted" : "Failed";
    }

    @Override
    public String getVideo(Long id) {
        Video video = videoRepo.getById(id);
        if (video == null) {
            return "Video NOT_FOUND";
        }
        return video.getFile();
    }

    @Override
    public String getAllCourseVideos(Long courseId) {
        List<Video> videos = videoRepo.getAllByCourseId(courseId);
        if (videos.isEmpty()) {
            return "No videos found";
        }
        StringBuilder result = new StringBuilder();
        for (Video video : videos) {
            result.append(video.toString()).append("\n");
        }

        return result.toString();
    }
}
