package org.fittrack.fitnesstrackerdemo.controllers;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.fittrack.fitnesstrackerdemo.controllers.util.ResponseBuilder;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ResponsePayload;
import org.fittrack.fitnesstrackerdemo.models.dtos.UserDto;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.fittrack.fitnesstrackerdemo.services.TrainingCategoryService;
import org.fittrack.fitnesstrackerdemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getUserById(@PathVariable Long id) {
        try {
            return ResponseBuilder.buildResponsePayload(userService.getUserById(id),
                    HttpStatus.FOUND);
        } catch (UserNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No user with id  %d found!", id),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponsePayload> saveUser(@RequestBody UserDto userDto) {
        try {
            userService.save(userDto);

            return ResponseBuilder.buildResponsePayload("Created user!",
                    HttpStatus.CREATED);
        } catch (ValidationException e) {
            return ResponseBuilder.buildResponsePayload("Wrong request!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("User with id %d not found!", id),
                    HttpStatus.NOT_FOUND);
        }
    }

}
