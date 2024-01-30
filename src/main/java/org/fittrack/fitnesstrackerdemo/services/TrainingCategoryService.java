package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingCategoryService {

    private final TrainingCategoryRepository trainingCategoryRepository;

    Logger logger = LoggerFactory.getLogger(MuscleGroupService.class);


    public TrainingCategory getTrainingCategoryByName(@Valid String name) {
        return trainingCategoryRepository.findTrainingCategoryByName(name)
                .orElseThrow(RuntimeException::new);
    }

    public void save(TrainingCategory trainingCategory) {
        trainingCategoryRepository.save(trainingCategory);
    }

}
