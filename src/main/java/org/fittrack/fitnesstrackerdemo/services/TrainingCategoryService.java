package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.exceptions.TrainingCategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class   TrainingCategoryService {

    private final TrainingCategoryRepository trainingCategoryRepository;

    public TrainingCategory getTrainingCategoryByName(@Valid String name) {
        return trainingCategoryRepository.findTrainingCategoryByName(name)
                .orElseThrow(TrainingCategoryNotFoundException::new);
    }

    public TrainingCategory getTrainingCategoryById(Long id) {
        return trainingCategoryRepository.findTrainingCategoryById(id)
                .orElseThrow(TrainingCategoryNotFoundException::new);
    }

    public void save(TrainingCategory trainingCategory) {
        trainingCategoryRepository.save(trainingCategory);
    }
}
