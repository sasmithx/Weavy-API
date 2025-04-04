package com.sasmithx.pro.controller;

import com.sasmithx.pro.service.UserService;
import com.sasmithx.pro.service.impl.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserServiceIMPL userServiceIMPL) {
        this.userService = userServiceIMPL;
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody String jsonPayload) {
        try {
            String response = userService.createUser(jsonPayload);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cause creating user: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getUserDetails(@PathVariable("id") String id) {
        try {
            String response = userService.getUserDetails(id);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cause retrieving user details: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody String jsonPayload) {
        try {
            String response = userService.updateUser(id, jsonPayload);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cause updating user: " + e.getMessage());
        }
    }


    @GetMapping()
    public ResponseEntity<String> listUsers(@RequestParam("take") int take) {
        try {
            String response = userService.listUsers(take);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cause listing users: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        try {
            String response = userService.deleteUser(id);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cause deleting user: " + e.getMessage());
        }
    }

}
