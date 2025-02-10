package models;

import java.util.Objects;

public class Video {
    private Long id;
    private String title;
    private String file;
    private Long courseId;

    public Video() {
    }

    public Video(String title, String file, Long courseId) {
        this.title = title;
        this.file = file;
        this.courseId = courseId;
    }

    public Video(Long id, String title, String file, Long courseId) {
        this.id = id;
        this.title = title;
        this.file = file;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return id + ") " + title + " for course " + courseId + "\n";
    }

}
