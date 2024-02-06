package org.fittrack.fitnesstrackerdemo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.Iterators;
import org.fittrack.fitnesstrackerdemo.converters.impl.WorkoutVolumeConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.TrainingCategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutVolumeDto;
import org.fittrack.fitnesstrackerdemo.models.entities.*;
import org.fittrack.fitnesstrackerdemo.models.factories.RepetitionWorkoutVolumeFactory;
import org.fittrack.fitnesstrackerdemo.models.factories.TimeWorkoutVolumeFactory;
import org.fittrack.fitnesstrackerdemo.repositories.ExerciseRepository;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.fittrack.fitnesstrackerdemo.repositories.WorkoutVolumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutVolumeService {

    private final WorkoutVolumeRepository workoutVolumeRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutVolumeConverter workoutVolumeConverter;

    public void generateWorkoutVolumes(Workout workout) {
        Set<Exercise> exercisesByMG = exerciseRepository.findExercisesByMuscleGroup(workout.getTargetMuscleGroup());
        Set<Exercise> exercisesByTC = exerciseRepository.findExercisesByTrainingCategory(workout.getTrainingCategory());

        List<Exercise> possibleExercises =
                exercisesByMG.stream()
                        .filter(exercisesByTC::contains)
                        .toList();


        int nExercises = 0;
        if (workout.getDurationInMinutes() <= 30) {
            nExercises = 2;
        } else if (workout.getDurationInMinutes() <= 45) {
            nExercises = 4;
        } else if (workout.getDurationInMinutes() <= 60){
            nExercises = 6;
        } else if (workout.getDurationInMinutes() <= 90) {
            nExercises = 8;
        }

        log.info("Generating volumes for workout...");
        if (nExercises > possibleExercises.size()) {
            nExercises = possibleExercises.size();
        }
        for (int i = 0; i < nExercises; i++) {
            generateIndividualWorkoutVolume(workout, possibleExercises.get(i));
        }
    }

    private void generateIndividualWorkoutVolume(Workout workout, Exercise exercise) {
        if (exercise.isRepetitionBased()) {
            RepetitionWorkoutVolumeFactory repetitionWorkoutVolumeFactory = new RepetitionWorkoutVolumeFactory();
            workoutVolumeRepository.save(repetitionWorkoutVolumeFactory.createWorkoutVolume(workout, exercise));
            log.info("Created new RepetitionWorkoutVolume!");
        } else {
            TimeWorkoutVolumeFactory timeWorkoutVolumeFactory = new TimeWorkoutVolumeFactory();
            workoutVolumeRepository.save(timeWorkoutVolumeFactory.createWorkoutVolume(workout, exercise));
            log.info("Created new TimeWorkoutVolume!");
        }
    }

    public Set<WorkoutVolumeDto> getWorkoutVolumesByWorkout(Workout workout) {
        Set<WorkoutVolume> volumes = workoutVolumeRepository.findWorkoutVolumesByWorkout(workout);

        return new HashSet<>(
                volumes.stream()
                        .map(workoutVolumeConverter::convertFirstToSecond)
                        .toList()
        );
    }

    public void deleteWorkoutVolumesByWorkout(Workout workout) {
        Set<WorkoutVolume> volumes = workoutVolumeRepository.findWorkoutVolumesByWorkout(workout);

        workoutVolumeRepository.deleteAll(volumes);
    }


}
