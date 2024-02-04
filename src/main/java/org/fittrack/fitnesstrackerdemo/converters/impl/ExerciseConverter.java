package org.fittrack.fitnesstrackerdemo.converters.impl;

import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.CategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
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
//        exercise.setIsBeginner(exerciseDto.beginner());
//        exercise.setIsIntermediate(exerciseDto.intermediate());
//        exercise.setIsExpert(exerciseDto.expert());

        exercise.setMuscleGroups(
                Arrays.stream(exerciseDto.muscleGroups().split("\\s"))
                        .map(s -> {
                            MuscleGroup mg = muscleGroupRepository.findMuscleGroupByName(s)
                                    .orElseThrow(MuscleGroupNotFoundException::new);
                            mg.getExercises().add(exercise);
                            return mg;
                        })
                        .collect(Collectors.toSet())
        );

        exercise.setTrainingCategories(
                Arrays.stream(exerciseDto.trainingCategories().split("\\s"))
                        .map(string -> {
                            TrainingCategory tc = trainingCategoryRepository.findTrainingCategoryByName(string)
                                    .orElseThrow(CategoryNotFoundException::new);
                            tc.getExercises().add(exercise);
                            return tc;
                        })
                        .collect(Collectors.toSet())
        );

//        exercise.setExhaustionPoints(
//                exercise.getMuscleGroups()
//                        .stream()
//                        .mapToInt(MuscleGroup::getSize)
//                        .sum()
//        );

        return exercise;
    }

    @Override
    public ExerciseDto convertFirstToSecond(Exercise exercise) {
        StringBuilder muscleGroups = new StringBuilder();
        exercise.getMuscleGroups()
                .forEach(muscleGroup ->
                        muscleGroups.append(String.format("%s ", muscleGroup.getName())));

        StringBuilder trainingCategories = new StringBuilder();
        exercise.getTrainingCategories()
                .forEach(trainingCategory ->
                        trainingCategories.append(String.format("%s ", trainingCategory.getName().toUpperCase())));

        return ExerciseDto.builder()
                .name(exercise.getName())
                .muscleGroups(muscleGroups.toString())
                .trainingCategories(trainingCategories.toString())
                .build();
    }
}
