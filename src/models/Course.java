package models;

import java.util.Date;

public class Course {
//    Fields
    private Long id;
    private String name;
    private String description;
    private int lecturerId;
    private Date createdAt;

    //    Constructors
    public Course() {
    }

    public Course(String name, String description, int lecturerId) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.description = description;
    }

    public Course(String name, String description, Date createdAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Course(String name, String description, int lecturerId, Date createdAt) {
        this.name = name;
        this.description = description;
        this.lecturerId = lecturerId;
        this.createdAt = createdAt;
    }

    public Course(Long id, String name, String description, int lecturerId, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lecturerId = lecturerId;
        this.createdAt = createdAt;
    }

    //    Mutators and Accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//  Methods

    public String toString() {
        return id + ") " + name + ":\n" + "\t" + description
                + "\n" + "Created at: " + createdAt.toString() + "\n";
    }
}
