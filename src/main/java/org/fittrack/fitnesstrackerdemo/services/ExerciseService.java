package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.impl.ExerciseConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.ExerciseNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.InvalidExerciseException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.repositories.ExerciseRepository;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseConverter exerciseConverter;
    private final MuscleGroupRepository muscleGroupRepository;
    private final TrainingCategoryRepository trainingCategoryRepository;


    public void save(@Valid ExerciseDto exerciseDto) throws InvalidExerciseException {
        Exercise exercise = exerciseConverter.convertSecondToFirst(exerciseDto);

        exerciseRepository.save(exercise);

        /* After saving the exercise its id is retrieved
        in order to persist its relationship with the parent entities */
        long exerciseId = new Exercise(exerciseRepository.findExerciseByName(exercise.getName())
                .orElseThrow(InvalidExerciseException::new)).getId();

        /* The new exercise is saved in the join table manually by its id */
/*
        muscleGroupRepository.setExerciseForMuscleGroupById(muscleGroup.getId(), exerciseId);

        trainingCategoryRepository.setExerciseForTrainingCategoryById(trainingCategory.getId(), exerciseId);
*/
    }


    public ExerciseDto getExerciseByName(String name) throws ExerciseNotFoundException {
        return exerciseConverter.convertFirstToSecond(
                exerciseRepository.findExerciseByName(name)
                        .orElseThrow(ExerciseNotFoundException::new));
    }


}
