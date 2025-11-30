package com.oopproject.grademaster.services;

import com.oopproject.grademaster.entities.Subject;
import com.oopproject.grademaster.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    private static final Logger logger = Logger.getLogger(SubjectService.class.getName());

    // GET Methods
    public Optional<Subject> getSubjectByCode(String subjectCode) {
        logger.info("Getting the subject by id: " + subjectCode);

        return subjectRepository.findBySubjectCode(subjectCode);
    }

    public List<Subject> getAllSubjects() {
        logger.info("Getting all subjects");

        return subjectRepository.findAll();
    }

    // POST Methods
    public Subject createSubject(String code, String title, Float units) {
        Subject subject = new Subject();
        subject.setCode(code);
        subject.setTitle(title);
        subject.setUnits(units);
        subject.setCreatedAt(LocalDateTime.now());

        return subjectRepository.save(subject);
    }
}
