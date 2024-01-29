package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.impl.ExerciseConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.ExerciseNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.repositories.ExerciseRepository;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseConverter exerciseConverter;
    private final MuscleGroupRepository muscleGroupRepository;

    public void save(@Valid ExerciseDto exerciseDto) {
        Exercise exercise = exerciseConverter.convertSecondToFirst(exerciseDto);

        if (exercise != null) {
            exerciseRepository.save(exercise);
//            exercise.getMuscleGroups().forEach(muscleGroup -> muscleGroupRepository.setExerciseForMuscleGroupById(muscleGroup.getId(), exercise.getId()));
        }
    }


    public ExerciseDto getExerciseByName(String name) {
        return exerciseConverter.convertFirstToSecond(
                exerciseRepository.findExerciseByName(name)
                        .orElseThrow(ExerciseNotFoundException::new)
        );
    }

}
