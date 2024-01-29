package org.fittrack.fitnesstrackerdemo.converters.impl;

import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutVolumeDto;
import org.fittrack.fitnesstrackerdemo.models.entities.WorkoutVolume;
import org.springframework.stereotype.Component;

@Component
public class WorkoutVolumeConverter implements ObjectConverter<WorkoutVolume, WorkoutVolumeDto> {

    @Override
    public WorkoutVolume convertSecondToFirst(WorkoutVolumeDto workoutVolumeDto) {
        return null;
    }

    @Override
    public WorkoutVolumeDto convertFirstToSecond(WorkoutVolume workoutVolume) {
        return WorkoutVolumeDto.builder()
                .exercise(workoutVolume.getExercise()
                        .getName())
                .setRange(
                        String.format("Do %d to %d sets of exercise",
                                workoutVolume.getSetRangeMin(),
                                workoutVolume.getSetRangeMax())
                )
                .repRange(
                        String.format("Sets of %d to %d repetitions",
                                workoutVolume.getRepRangeMin(),
                                workoutVolume.getRepRangeMax())
                )
                .build();
    }
}
