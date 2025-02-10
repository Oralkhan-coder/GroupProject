package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Long courseId;

    public Assignment() {
    }

    public Assignment(String title, String description, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public Assignment(String title, String description, LocalDateTime deadline, Long courseId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.courseId = courseId;
    }

    public Assignment(Long id, String title, String description, LocalDateTime deadline, Long courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return id + ") " + title + " \n" + description + " \n \t \t \t" + " deadline:" + deadline
                + " \n" + "For course" + courseId + "\n";
    }
}
