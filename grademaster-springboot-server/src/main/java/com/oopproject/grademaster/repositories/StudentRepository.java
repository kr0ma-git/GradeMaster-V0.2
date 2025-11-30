package com.oopproject.grademaster.repositories;

import com.oopproject.grademaster.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(int studentId);
    Optional<Student> findByStudentEmail(String studentEmail);
}