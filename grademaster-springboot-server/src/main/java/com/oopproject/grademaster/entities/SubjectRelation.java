package com.oopproject.grademaster.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "subject_relation")
public class SubjectRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_relation_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "teacher_fk")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_fk")
    private Subject subject;

    @Column(name = "section")
    private String section;

    @Column(name = "semester")
    private String semester;

    @Column(name = "school_year")
    private String schoolYear;

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}
