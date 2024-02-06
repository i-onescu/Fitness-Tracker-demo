package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.converters.impl.TrainingCategoryConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.TrainingCategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadTrainingCategoryDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.createdtos.WriteTrainingCategoryDto;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class   TrainingCategoryService {

    private final TrainingCategoryRepository trainingCategoryRepository;
    private final TrainingCategoryConverter trainingCategoryConverter;

    public ReadTrainingCategoryDto getTrainingCategoryByName(@Valid String name) {
        return trainingCategoryConverter.convertFirstToSecond(
                trainingCategoryRepository.findTrainingCategoryByName(name)
                        .orElseThrow(TrainingCategoryNotFoundException::new));

    }

    public ReadTrainingCategoryDto getTrainingCategoryById(Long id) {
        return trainingCategoryConverter.convertFirstToSecond(
                trainingCategoryRepository.findTrainingCategoryById(id)
                        .orElseThrow(TrainingCategoryNotFoundException::new));
    }

    public void save(@Valid WriteTrainingCategoryDto writeTrainingCategoryDto) {
        TrainingCategory trainingCategory =
                trainingCategoryConverter.convertSecondToFirst(writeTrainingCategoryDto);

        trainingCategoryRepository.save(trainingCategory);
    }
}
