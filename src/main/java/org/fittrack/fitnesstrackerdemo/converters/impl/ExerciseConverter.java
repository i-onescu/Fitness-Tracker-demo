package org.fittrack.fitnesstrackerdemo.converters.impl;

import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.CategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExerciseConverter implements ObjectConverter<Exercise, ExerciseDto> {

    private final MuscleGroupRepository muscleGroupRepository;
    private final TrainingCategoryRepository trainingCategoryRepository;


    @Override
    public Exercise convertSecondToFirst(ExerciseDto exerciseDto) {
        Exercise exercise = new Exercise();

        exercise.setName(exerciseDto.name());
        exercise.setIsBeginner(exerciseDto.beginner());
        exercise.setIsIntermediate(exerciseDto.intermediate());
        exercise.setIsExpert(exerciseDto.expert());

        exercise.setMuscleGroup(
                muscleGroupRepository.findMuscleGroupByName(exerciseDto.muscleGroups())
                        .orElseThrow(MuscleGroupNotFoundException::new)
        );

        exercise.setTrainingCategories(
                trainingCategoryRepository.findTrainingCategoryByName(exerciseDto.trainingCategories())
                        .orElseThrow(CategoryNotFoundException::new)
        );

        exercise.setExhaustionPoints(
                exercise.getTrainingCategories().getExhaustionFactor() *
                        exercise.getMuscleGroup().getSize()
        );

        return exercise;
    }

    @Override
    public ExerciseDto convertFirstToSecond(Exercise exercise) {
        return ExerciseDto.builder()
                .name(exercise.getName())
                .muscleGroups(exercise.getMuscleGroup().getName())
                .trainingCategories(exercise.getTrainingCategories().getName())
                .build();
    }
}
