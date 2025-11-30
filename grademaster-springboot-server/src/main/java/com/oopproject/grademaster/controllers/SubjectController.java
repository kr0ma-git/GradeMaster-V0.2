package com.oopproject.grademaster.controllers;

import com.oopproject.grademaster.services.SubjectService;
import com.oopproject.grademaster.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {
    private static final Logger logger = Logger.getLogger(SubjectController.class.getName());
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/fetch")
    public ResponseEntity<Subject> getSubjectByCode(@RequestParam String subjectCode) {
        logger.info("Getting subject by id: " + subjectCode);
        Optional<Subject> subjectOptional = subjectService.getSubjectByCode(subjectCode);

        if (subjectOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(subjectOptional.get());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();

        if (subjectList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(subjectList);
        }
    }
}
