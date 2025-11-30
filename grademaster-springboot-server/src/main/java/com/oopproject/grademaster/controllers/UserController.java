// Controller Layer. Receives from the Service Layer

package com.oopproject.grademaster.controllers;

import com.oopproject.grademaster.entities.User;
import com.oopproject.grademaster.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController // Tells spring boot that this class will handle http requests of API calls and/or responding to clients
@RequestMapping("/api/v1/user") // Defines the URL path for the controller class, helps handle http requests to specific handle methods in the controllers, which sets up the routes for the API
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    private UserService userService;

    /*
    @GetMapping // GET Requests will call this method with this annotation
    public ResponseEntity<User> getUserById(@RequestParam int userId) { // RequestParam is used to extract the parameters from the GET URL
        logger.info("Getting user by id: " + userId);
        Optional<User> userOptional = userService.getUserById(userId); // Fetches the optional object from the service layer

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // User not found in the database, send a Response entity with an error status
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get()); // User found send the actual User object
        }
    }
    */

    @GetMapping
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        logger.info("Getting user by email: " + email);
        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
    }

    @GetMapping("/role")
    public ResponseEntity<User> getUserRole(@RequestParam String email) {
        logger.info("Getting user role by email: " + email);
        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
    }

    @PostMapping("/login") // POST Requests will call this method with this annotation and the addition of a route specifies where the POST request should go (/api/v1/user/login)
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
        logger.info("Getting the by email: " + email);

        Optional<User>  userOptional = userService.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            // User not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // User found

        // Credential Check

        if (!password.equals(userOptional.get().getPassword())) {
            // Wrong Credentials

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Correct Credentials

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) { // Passing a JSON body to create a new user in the database
       logger.info("Creating New User");

       User newUser = userService.createUser(user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());

       return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }
}