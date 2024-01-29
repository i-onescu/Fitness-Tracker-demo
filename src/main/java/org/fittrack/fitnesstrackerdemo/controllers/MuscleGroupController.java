package org.fittrack.fitnesstrackerdemo.controllers;

import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.controllers.util.ResponseBuilder;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ResponsePayload;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.services.MuscleGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/muscleGroups")
@RequiredArgsConstructor
public class MuscleGroupController {

    private final MuscleGroupService muscleGroupService;

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponsePayload> getMuscleGroupByName(@PathVariable String name) {
        try {
            return ResponseBuilder.buildResponsePayload(muscleGroupService.getMuscleGroupByName(name),
                    HttpStatus.FOUND);
        } catch (MuscleGroupNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No muscle group with name %s found!", name),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponsePayload> getMuscleGroupById(@PathVariable Long id) {
        try {
            return ResponseBuilder.buildResponsePayload(muscleGroupService.getMuscleGroupById(id),
                    HttpStatus.FOUND);
        } catch (MuscleGroupNotFoundException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No muscle group with id %s found!", id),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping
    public ResponseEntity<ResponsePayload> saveMuscleGroup(@RequestBody MuscleGroup muscleGroup) {
        try {
            muscleGroupService.save(muscleGroup);

            return ResponseBuilder.buildResponsePayload("Muscle group created!",
                    HttpStatus.CREATED);
        } catch (MuscleGroupNotFoundException e) {
            return ResponseBuilder.buildResponsePayload("Wrong request!",
                    HttpStatus.BAD_REQUEST);
        }
    }


}
