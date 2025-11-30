// Access all CRUD Operations within an Interface using JPA Repository

package com.oopproject.grademaster.repositories;

import com.oopproject.grademaster.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> { // Passed in the Entity and the type of Primary Key used for that model in the database
    Optional<User> findByEmail(String email); // Returns an optional user object, similar to: "SELECT * FROM user WHERE email = 'email@emailexample.com'"
    void deleteByEmail(String email); // Delete something in the user table by the email
}