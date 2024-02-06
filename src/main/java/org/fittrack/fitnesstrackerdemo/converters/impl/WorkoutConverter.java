package org.fittrack.fitnesstrackerdemo.converters.impl;

import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.enums.Intensity;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.fittrack.fitnesstrackerdemo.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutConverter implements ObjectConverter<Workout, WorkoutDto> {

    private final TrainingCategoryRepository trainingCategoryRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    @Override
    public Workout convertSecondToFirst(WorkoutDto workoutDto) {
        Workout workout = new Workout();

        workout.setIntensity(Intensity.valueOf(workoutDto.intensity()));
        workout.setTargetMuscleGroup(muscleGroupRepository.findMuscleGroupById(workoutDto.muscleGroupId())
                .orElseThrow());
        workout.setTrainingCategory(trainingCategoryRepository.findTrainingCategoryById(workoutDto.trainingCategoryId())
                .orElseThrow());
        workout.setDurationInMinutes(workoutDto.durationInMinutes());

        return workout;
    }

    @Override
    public WorkoutDto convertFirstToSecond(Workout workout) {
        return null;
    }
}
