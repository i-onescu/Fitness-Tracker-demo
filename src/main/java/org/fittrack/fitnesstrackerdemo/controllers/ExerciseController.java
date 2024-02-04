package org.fittrack.fitnesstrackerdemo.controllers;


import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.controllers.util.ResponseBuilder;
import org.fittrack.fitnesstrackerdemo.exceptions.ExerciseNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.ResponsePayload;
import org.fittrack.fitnesstrackerdemo.services.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/{name}")
    public ResponseEntity<ResponsePayload> getExerciseByName(@PathVariable String name) {
        try {
            return ResponseBuilder.buildResponsePayload(exerciseService.getExerciseByName(name),
                    HttpStatus.FOUND);
        } catch (ExerciseNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No exercise with name %s!", name),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getExerciseByName(@PathVariable Long id) {
        try {
            return ResponseBuilder.buildResponsePayload(exerciseService.getExerciseById(id),
                    HttpStatus.FOUND);
        } catch (ExerciseNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No exercise with id %s!", id),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping
    public ResponseEntity<ResponsePayload> saveExercise(@RequestBody ExerciseDto exerciseDto) {
        try {
            exerciseService.save(exerciseDto);

            return ResponseBuilder.buildResponsePayload("Exercise created!",
                    HttpStatus.CREATED);
        } catch (ValidationException e) {
            return ResponseBuilder.buildResponsePayload("Wrong request!",
                    HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return ResponseBuilder.buildResponsePayload(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }




}
