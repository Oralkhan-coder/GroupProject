package models;

import java.time.LocalDate;

public class RegisteredCourse {
    private Long id;
    private Long courseId;
    private int userId;
    private final LocalDate registrationDate = LocalDate.now();
    private String status = "in_progress";

    public RegisteredCourse() {
    }

    public RegisteredCourse(Long courseId, int userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public RegisteredCourse(Long id, Long courseId, int userId) {
        this(courseId, userId);
        this.id = id;
    }

    public RegisteredCourse(Long courseId, int userId, String status) {
        this(courseId, userId);
        this.status = status;
    }

    public RegisteredCourse(Long id, Long courseId, int userId, String status) {
        this(courseId, userId);
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}