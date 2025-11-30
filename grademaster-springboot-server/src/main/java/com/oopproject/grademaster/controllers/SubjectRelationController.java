package com.oopproject.grademaster.controllers;

import com.oopproject.grademaster.services.SubjectRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/subject-relation")
public class SubjectRelationController {
    private static final Logger logger = Logger.getLogger(SubjectRelationController.class.getName());
    @Autowired
    private SubjectRelationService subjectRelationService;

    @GetMapping
    public ResponseEntity<?> getSubjectsForUser(@RequestParam int userId) {
        logger.info("Getting subjects for user with userId: " + userId);

        var subjects = subjectRelationService.getSubjectsForUser(userId);

        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getUserSubjectCount(@RequestParam int userId) {
        logger.info("Getting user subject count with user ID: " + userId);

        int count = subjectRelationService.getTotalSubjectsCount(userId);

        return ResponseEntity.ok(count);
    }
}
