package com.oopproject.grademaster.controllers;

import com.oopproject.grademaster.entities.Student;
import com.oopproject.grademaster.services.StudentService;
import com.oopproject.grademaster.services.SubjectService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private static final Logger logger = Logger.getLogger(StudentController.class.getName());
    @Autowired
    private StudentService studentService;

    @GetMapping("/id")
    public ResponseEntity<Student> getStudentById(@RequestParam int studentId) { // Query specific student by ID
        logger.info("Getting student by id: " + studentId);
        Optional<Student> studentOptional = studentService.getStudentById(studentId);

        if (studentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(studentOptional.get());
        }
    }

    @GetMapping()
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String studentEmail) {
        logger.info("Getting student by email: " + studentEmail);
        Optional<Student> studentOptional = studentService.getStudentByEmail(studentEmail);

        if (studentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(studentOptional.get());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();

        if (studentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Student> postCreateStudent(@RequestBody Student student) {
        logger.info("Creating New Student");

        Student newStudent = studentService.createStudent(student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getStudentEmail(), student.getDateOfBirth(), student.getAddress(), student.getContactNumber(), student.getGender());

        return ResponseEntity.status(HttpStatus.OK).body(newStudent);
    }
}
