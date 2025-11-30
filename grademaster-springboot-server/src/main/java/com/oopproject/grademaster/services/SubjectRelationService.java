package com.oopproject.grademaster.services;

import com.oopproject.grademaster.entities.SubjectRelation;
import com.oopproject.grademaster.repositories.StudentRepository;
import com.oopproject.grademaster.repositories.SubjectRelationRepository;
import com.oopproject.grademaster.repositories.SubjectRepository;
import com.oopproject.grademaster.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional // Associative Entity
public class SubjectRelationService {
    private static final Logger logger = Logger.getLogger(SubjectRelationService.class.getName());

    @Autowired
    private SubjectRelationRepository subjectRelationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // GET
    public int getTotalSubjectsCount(int userId) {
        logger.info("Getting total subject count with userId: " + userId);

        return subjectRelationRepository.countByUserId(userId);
    }

    public List<SubjectRelation> getSubjectsForUser(int userId) {
        logger.info("Getting subjects for current user: " + userId);

        return subjectRelationRepository.findByUserId(userId);
    }

    // POST
}
