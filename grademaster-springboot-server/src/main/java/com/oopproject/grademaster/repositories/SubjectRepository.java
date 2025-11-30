package com.oopproject.grademaster.repositories;

import com.oopproject.grademaster.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("SELECT s FROM Subject s WHERE s.code = :subjectCode") // Manual Query
    Optional<Subject> findBySubjectCode(String subjectCode);
}
