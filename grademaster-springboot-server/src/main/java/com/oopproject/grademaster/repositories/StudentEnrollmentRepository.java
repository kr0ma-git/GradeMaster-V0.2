package com.oopproject.grademaster.repositories;

import com.oopproject.grademaster.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Integer> {
    @Query("SELECT COUNT(se) FROM StudentEnrollment se WHERE se.subjectRelation.user.id = :userId")
    int countStudentsUnderUser(int userId);
}
