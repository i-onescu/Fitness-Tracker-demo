package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.converters.impl.WorkoutConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.InvalidWorkoutDetailsException;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.WorkoutNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutVolumeDto;
import org.fittrack.fitnesstrackerdemo.models.entities.*;
import org.fittrack.fitnesstrackerdemo.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final WorkoutVolumeService workoutVolumeService;
    private final WorkoutConverter workoutConverter;

    public Set<WorkoutVolumeDto> createWorkoutForUserById(Long userId,
                                                          @Valid WorkoutDto workoutDto) throws InvalidWorkoutDetailsException {

        Workout workout = workoutConverter.convertSecondToFirst(workoutDto);
        LocalDateTime dateTimeCreated = LocalDateTime.now();

        workout.setCreatedDateTime(dateTimeCreated);
        workout.setUser(
                userRepository.findUserById(userId)
                        .orElseThrow(UserNotFoundException::new)
        );

        log.info("Creating workout on timestamp " + dateTimeCreated +
                " for user " + userId);

        workoutVolumeService.generateWorkoutVolumes(workout);
        workoutRepository.save(workout);

        Long l = getWorkoutByUserIdAndDateTime(userId, dateTimeCreated).getId();
        if (l != 0) {
            log.info(String.format("Saved workout with id %d", l));
        } else {
            log.warn("Save failed!");
        }

        return workoutVolumeService.getWorkoutVolumesByWorkout(
                getWorkoutByUserIdAndDateTime(userId, dateTimeCreated)
        );
    }

    public Workout getWorkoutByUserIdAndDateTime(Long userId,LocalDateTime dateTime) {
        return workoutRepository.findWorkoutByUserAndDateTime(userId, dateTime)
                .orElseThrow(WorkoutNotFoundException::new);
    }

    public Set<WorkoutVolumeDto> getWorkoutVolumes(Long id) {
        return workoutVolumeService.getWorkoutVolumesByWorkout(
                workoutRepository.findWorkoutById(id)
                        .orElseThrow(WorkoutNotFoundException::new));

    }

    public void deleteWorkoutById(Long id) {
        Workout workout = workoutRepository.findWorkoutById(id)
                .orElseThrow(WorkoutNotFoundException::new);
        workoutVolumeService.deleteWorkoutVolumesByWorkout(workout);
        workoutRepository.delete(workout);
    }
}
