package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Submission {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private float grade;
    private String file;
    private String feedback;
    private LocalDateTime date;



    public Submission() {
    }

    public Submission(Long id, Long assignmentId, Long studentId, float grade, String file, String feedback, LocalDateTime date) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.grade = grade;
        this.file = file;
        this.feedback = feedback;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return id + ") " + file + "\n Feedback: " + feedback
                + "Grade: " + grade +"\n Date: " + date + "\n";
    }
}
