package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.impl.WorkoutConverter;
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
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final WorkoutVolumeService workoutVolumeService;
    private final WorkoutConverter workoutConverter;

    public Set<WorkoutVolumeDto> createWorkoutForUserById(Long userId, @Valid WorkoutDto workoutDto) {
        Workout workout = workoutConverter.convertSecondToFirst(workoutDto);
        LocalDateTime dateTimeCreated = LocalDateTime.now();


        workout.setCreatedDateTime(dateTimeCreated);
        workout.setUser(
                userRepository.findUserById(userId)
                        .orElseThrow(UserNotFoundException::new)
        );

        workoutVolumeService.createWorkoutVolume(workout);
        workoutRepository.save(workout);

        return workoutVolumeService.getWorkoutVolumesByWorkout(getWorkoutByUserIdAndDateTime(userId, dateTimeCreated));
    }

    public Workout getWorkoutByUserIdAndDateTime(Long userId,LocalDateTime dateTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss-ns");
//        String formattedDateTime = dateTime.format(formatter);

        return workoutRepository.findWorkoutByUserAndDateTime(userId, dateTime)
                .orElseThrow(WorkoutNotFoundException::new);

    }

    public Set<WorkoutVolumeDto> getWorkoutVolume(Workout workout) {

        return workoutVolumeService.getWorkoutVolumesByWorkout(workout);


    }




}
