package com.oopproject.grademaster.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "student_enrollment")
public class StudentEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_enrollment_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_fk")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_relation_fk")
    private SubjectRelation subjectRelation;

    @Column(name = "enrollment_date", insertable = false, updatable = false) // Let the db handle the enrollment_date because it is set to CURRENT_TIMESTAMP
    private LocalDateTime enrollmentDate;

    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SubjectRelation getSubjectRelation() {
        return subjectRelation;
    }

    public void setSubjectRelation(SubjectRelation subjectRelation) {
        this.subjectRelation = subjectRelation;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
