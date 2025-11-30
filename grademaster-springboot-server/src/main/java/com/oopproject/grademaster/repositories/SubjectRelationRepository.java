package com.oopproject.grademaster.repositories;

import com.oopproject.grademaster.entities.Subject;
import com.oopproject.grademaster.entities.SubjectRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRelationRepository extends JpaRepository<SubjectRelation, Integer> {
    int countByUserId(Integer userId);
    List<SubjectRelation> findByUserId(int userId);
}
