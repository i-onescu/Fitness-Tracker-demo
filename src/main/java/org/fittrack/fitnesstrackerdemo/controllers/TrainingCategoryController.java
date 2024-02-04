package org.fittrack.fitnesstrackerdemo.controllers;

import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.controllers.util.ResponseBuilder;
import org.fittrack.fitnesstrackerdemo.models.dtos.ResponsePayload;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.services.TrainingCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trainingCategories")
@RequiredArgsConstructor
public class TrainingCategoryController {

    private final TrainingCategoryService trainingCategoryService;

    @GetMapping("/{name}")
    public ResponseEntity<ResponsePayload> getTrainingCategoryByName(@PathVariable String name) {
        try {
            return ResponseBuilder.buildResponsePayload(trainingCategoryService.getTrainingCategoryByName(name),
                    HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No training category with name %s found!", name),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getTrainingCategoryByName(@PathVariable Long id) {
        try {
            return ResponseBuilder.buildResponsePayload(trainingCategoryService.getTrainingCategoryById(id),
                    HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseBuilder.buildResponsePayload(String.format("No training category with id %s found!", id),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponsePayload> saveTrainingCategory(@RequestBody TrainingCategory trainingCategory) {
        try {
            trainingCategoryService.save(trainingCategory);

            return ResponseBuilder.buildResponsePayload("Created training category!",
                    HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseBuilder.buildResponsePayload("Wrong request!",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
