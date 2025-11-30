package com.oopproject.grademaster.services;

import com.oopproject.grademaster.entities.*;
import com.oopproject.grademaster.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class StudentEnrollmentService {
    private static final Logger logger = Logger.getLogger(StudentEnrollmentService.class.getName()); // Useful for debugging, shows prompts and completions/errors in terminal

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRelationRepository subjectRelationRepository;

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    // GET
    public int getStudentCountUnderUser(int userId) {
        logger.info("Getting student count under user ID: " + userId);

        return studentEnrollmentRepository.countStudentsUnderUser(userId);
    }

    // POST
    public StudentEnrollment createStudentEnrollment(int studentId, int subjectRelationId) {  // Both enrollment_date and status will be set below
        logger.info("Creating New Student Enrollment with Student ID: " + studentId + " and Subject Relation ID: " + subjectRelationId);

        // Check if the student the subject relation exists
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<SubjectRelation> subjectRelation = subjectRelationRepository.findById(subjectRelationId);

        if (student.isEmpty()) {
            logger.warning("Student not found");
        }

        if (subjectRelation.isEmpty()) {
            logger.warning("Subject Relation not found");
        }

        if (!student.isEmpty() && !subjectRelation.isEmpty()) {
            StudentEnrollment studentEnrollment = new StudentEnrollment();
            studentEnrollment.setStudent(student.get());
            studentEnrollment.setSubjectRelation(subjectRelation.get());
            studentEnrollment.setStatus("ENROLLED");

            return studentEnrollmentRepository.save(studentEnrollment);
        } else {
            return null;
        }
    }

    // TODO:

    // Update Student Enrollment

    // Drop/Soft Delete Student Enrollment
}