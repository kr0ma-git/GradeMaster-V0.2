// Service Layer. Receives from the database, sends to the Controller

package com.oopproject.grademaster.services;

import com.oopproject.grademaster.entities.User;
import com.oopproject.grademaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service // Marks this class as a service
public class UserService {
    @Autowired // Singleton spring principle where a single object is used by all throughout the lifespan of the project
                // An analogy could be looking for things in a box, if it doesn't exist, spring will look for an existing one and grab it to place it inside the box
    private UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(UserService.class.getName()); // Useful for debugging, shows prompts and completions/errors in terminal

    // GET Methods
    public Optional<User> getUserById(int userId) {
        logger.info("Getting the user by id: " + userId);

        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        logger.info("Getting the user by email: " + email);

        return userRepository.findByEmail(email);
    }

    // POST Methods
    public User createUser(String name, String username, String email, String password, String role) {
        logger.info("Creating new user, name: " + name);
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user); // Saves the user entity into the database (pre-defined method called save())
    }
}
