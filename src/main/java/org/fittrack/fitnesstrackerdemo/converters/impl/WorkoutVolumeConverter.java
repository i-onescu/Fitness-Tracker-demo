package org.fittrack.fitnesstrackerdemo.converters.impl;

import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.models.dtos.WorkoutVolumeDto;
import org.fittrack.fitnesstrackerdemo.models.entities.RepetitionWorkoutVolume;
import org.fittrack.fitnesstrackerdemo.models.entities.TimeWorkoutVolume;
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

        if (workoutVolume instanceof RepetitionWorkoutVolume repetitionWorkoutVolume) {
            return WorkoutVolumeDto.builder()
                    .exercise(repetitionWorkoutVolume.getExercise()
                            .getName())
                    .setRange(
                            String.format("Do %d to %d sets of exercise",
                                    repetitionWorkoutVolume.getSetRangeMin(),
                                    repetitionWorkoutVolume.getSetRangeMax())
                    )
                    .repRange(
                            String.format("Each set being of %d to %d repetitions",
                                    repetitionWorkoutVolume.getRepRangeMin(),
                                    repetitionWorkoutVolume.getRepRangeMax())
                    )
                    .build();
        } else if (workoutVolume instanceof TimeWorkoutVolume timeWorkoutVolume) {
            return WorkoutVolumeDto.builder()
                    .exercise(timeWorkoutVolume.getExercise()
                            .getName())
                    .setRange(
                            String.format("Do %d to %d sets of exercise",
                                    timeWorkoutVolume.getSetRangeMin(),
                                    timeWorkoutVolume.getSetRangeMax())
                    )
                    .repRange(
                            String.format("Each set of %d to %d repetitions",
                                    timeWorkoutVolume.getTimeValueMin(),
                                    timeWorkoutVolume.getTimeValueMax())
                    )
                    .build();
        }
        return null;
    }
}
