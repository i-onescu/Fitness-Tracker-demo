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


    /*
     http://localhost:8080/api/v1/workouts/{userId}

     application json

     {
        "intensityLevel": 2,  --selected in drop down
        "trainingCategory": "BODYBUILDING",  --selected in drop down list
        "targetMuscleGroups": "CHEST", --selected in drop down list
        "durationInMinutes": 30  --selected in drop down list
     }
    */

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getWorkoutById(@PathVariable Long id) {
        try {
            return ResponseBuilder.buildResponsePayload(workoutService.getWorkoutVolumes(id),
                    HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("Workout with id %d not found!", id),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> deleteWorkoutById(@PathVariable Long id) {
        try {
            workoutService.deleteWorkoutById(id);

            return ResponseBuilder.buildResponsePayload("Deleted workout!",
                    HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("Workout with id %d not found!", id),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
