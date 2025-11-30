package com.oopproject.grademaster.services;

import com.oopproject.grademaster.entities.Student;
import com.oopproject.grademaster.repositories.StudentRepository;
import com.oopproject.grademaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private static final Logger logger = Logger.getLogger(StudentService.class.getName());
    @Autowired
    private UserRepository userRepository;

    // GET Methods
    public Optional<Student> getStudentById(int studentId) {
        logger.info("Getting the student by id: " + studentId);

        return studentRepository.findById(studentId);
    }

    public Optional<Student> getStudentByEmail(String studentEmail) {
        logger.info("Getting the student by email: " + studentEmail);

        return studentRepository.findByStudentEmail(studentEmail);
    }

    public List<Student> getAllStudents() {
        logger.info("Getting all students");

        return studentRepository.findAll();
    }


    // POST Methods
    public Student createStudent(String firstName, String middleName, String lastName, String email, Date dateOfBirth, String address, String contactNumber, String gender) {
        logger.info("Creating new Student, firstname: " + firstName);
        Student student = new Student();
        student.setFirstName(firstName);
        student.setMiddleName(middleName);
        student.setLastName(lastName);
        student.setStudentEmail(email);
        student.setDateOfBirth(dateOfBirth);
        student.setAddress(address);
        student.setContactNumber(contactNumber);
        student.setGender(gender);
        student.setCreatedAt(LocalDateTime.now());

        return studentRepository.save(student);
    }
}
