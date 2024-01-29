package org.fittrack.fitnesstrackerdemo.controllers;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.controllers.util.ResponseBuilder;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ResponsePayload;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutDto;
import org.fittrack.fitnesstrackerdemo.services.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/{userId}")
    public ResponseEntity<ResponsePayload> createWorkoutForUser(@PathVariable Long userId,
                                                                @RequestBody WorkoutDto workoutDto) {
        try {
            return ResponseBuilder.buildResponsePayload(workoutService.createWorkoutForUserById(userId, workoutDto),
                    HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("User with id %d does not exist!", userId),
                    HttpStatus.BAD_REQUEST);
        } catch (ValidationException e) {
            return ResponseBuilder.buildResponsePayload(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return ResponseBuilder.buildResponsePayload("Wrong request!",
                    HttpStatus.BAD_REQUEST);
        }
    }

}
