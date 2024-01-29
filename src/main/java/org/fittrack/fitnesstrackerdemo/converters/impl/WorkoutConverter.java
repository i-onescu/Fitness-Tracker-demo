package org.fittrack.fitnesstrackerdemo.converters.impl;

import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutConverter implements ObjectConverter<Workout, WorkoutDto> {

    private final UserRepository userRepository;

    @Override
    public Workout convertSecondToFirst(WorkoutDto workoutDto) {
        Workout workout = new Workout();

        workout.setTargetMuscleGroups(workoutDto.targetMuscleGroups());
        workout.setTrainingCategory(workoutDto.trainingCategory());
        workout.setIntensityLevel(workoutDto.intensityLevel());
        workout.setDurationInMinutes(workoutDto.durationInMinutes());

        return workout;
    }

    @Override
    public WorkoutDto convertFirstToSecond(Workout workout) {
        return null;
    }
}
