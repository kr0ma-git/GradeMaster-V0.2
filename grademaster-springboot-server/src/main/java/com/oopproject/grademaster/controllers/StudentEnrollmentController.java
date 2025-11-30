package com.oopproject.grademaster.controllers;

import com.oopproject.grademaster.entities.StudentEnrollment;
import com.oopproject.grademaster.services.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/student-enrollment")
public class StudentEnrollmentController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    private StudentEnrollmentService studentEnrollmentService;

    @GetMapping("/count")
    public ResponseEntity<Integer> getStudentCountUnderUser(@RequestParam int userId) {
        logger.info("Getting student count for user ID: " + userId);

        int count = studentEnrollmentService.getStudentCountUnderUser(userId);

        return ResponseEntity.ok(count);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentEnrollment> createStudentEnrollment(@RequestBody StudentEnrollment studentEnrollment) {
        logger.info("Creating new Student Enrollment for Student ID: " + studentEnrollment.getStudent().getId());

        studentEnrollmentService.createStudentEnrollment(studentEnrollment.getStudent().getId(), studentEnrollment.getSubjectRelation().getId()); // Pass in both IDs

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
