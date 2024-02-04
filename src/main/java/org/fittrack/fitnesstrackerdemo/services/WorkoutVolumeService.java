package org.fittrack.fitnesstrackerdemo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.converters.impl.WorkoutVolumeConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.TrainingCategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutVolumeDto;
import org.fittrack.fitnesstrackerdemo.models.entities.*;
import org.fittrack.fitnesstrackerdemo.repositories.ExerciseRepository;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.fittrack.fitnesstrackerdemo.repositories.WorkoutVolumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutVolumeService {

    private final WorkoutVolumeRepository workoutVolumeRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingCategoryRepository trainingCategoryRepository;
    private final WorkoutVolumeConverter workoutVolumeConverter;

    Logger logger = LoggerFactory.getLogger(WorkoutVolumeService.class);

    public void createWorkoutVolume(Workout workout) {

        TrainingCategory trainingCategory =
                trainingCategoryRepository.findTrainingCategoryByName(workout.getTrainingCategory())
                        .orElseThrow(TrainingCategoryNotFoundException::new);

        MuscleGroup muscleGroup = muscleGroupRepository.findMuscleGroupByName(workout.getTargetMuscleGroups())
                .orElseThrow(MuscleGroupNotFoundException::new);

        int maxExercises = 4;

        Set<Exercise> exercisesByMuscleGroup = exerciseRepository.findExercisesByMuscleGroup(muscleGroup);
        logger.info(String.format("EXERCISEREPOSTITORY<FINDEXERCISEBYMUSCLEGROUP> %s", exercisesByMuscleGroup.toString()));

        List<Exercise> exercises = new ArrayList<>(exerciseRepository.findExercisesByMuscleGroup(muscleGroup)
                .stream()
                .filter(exercise -> exercise.getTrainingCategories().equals(trainingCategory))
                .toList());

        WorkoutVolume workoutVolume = new WorkoutVolume();
        for (int i = 0; i < maxExercises; i++) {
            workoutVolume.setWorkout(workout);
            workoutVolume.setExercise(exercises.get(i));
            workoutVolume.setSetRangeMin(maxExercises / 2);
            workoutVolume.setSetRangeMax(maxExercises / 2 + 1);
            workoutVolume.setRepRangeMin(8);
            workoutVolume.setRepRangeMax(12);

            workoutVolumeRepository.save(workoutVolume);
        }
    }


    public Set<WorkoutVolumeDto> getWorkoutVolumesByWorkout(Workout workout) {
        Set<WorkoutVolume> volumes = workoutVolumeRepository.findWorkoutVolumeByWorkout(workout);

        return new HashSet<>(
                volumes.stream()
                        .map(workoutVolumeConverter::convertFirstToSecond)
                        .toList()
        );
    }


}
