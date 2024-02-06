package org.fittrack.fitnesstrackerdemo.models.factories;

import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.TimeWorkoutVolume;
import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.models.entities.WorkoutVolume;

import java.time.temporal.ChronoUnit;

public class TimeWorkoutVolumeFactory implements WorkoutVolumeFactory {
    @Override
    public WorkoutVolume createWorkoutVolume(Workout workout, Exercise exercise) {
        TimeWorkoutVolume volume = new TimeWorkoutVolume();
        volume.setWorkout(workout);

        boolean isCardio = workout.getTrainingCategory().isCardioTraining();
        boolean isEndurance = workout.getTrainingCategory().isEnduranceTraining();
        boolean isWeight = workout.getTrainingCategory().isWeightTraining();
        int intensity = workout.getIntensity().getValue();

        volume.setExercise(exercise);
        if (isCardio) {
            volume.setTimeUnit(ChronoUnit.MINUTES);
            if (isEndurance) {
                switch (intensity) {
                    case 1 -> {
                        volume.setTimeValueMin(30);
                        volume.setTimeValueMax(60);
                    }
                    case 2 -> {
                        volume.setTimeValueMin(60);
                        volume.setTimeValueMax(90);
                    }
                    case 3 -> {
                        volume.setTimeValueMin(90);
                        volume.setTimeValueMax(120);
                    }
                }
                return volume;
            } else {
                switch (intensity) {
                    case 1 -> {
                        volume.setTimeValueMin(15);
                        volume.setTimeValueMax(30);
                    }
                    case 2 -> {
                        volume.setTimeValueMin(30);
                        volume.setTimeValueMax(60);
                    }
                    case 3 -> {
                        volume.setTimeValueMin(60);
                        volume.setTimeValueMax(120);
                    }
                }
            }
            volume.setSetRangeMin(1);
            volume.setSetRangeMax(1);

            return volume;
        } else if (isWeight) {
            if (isEndurance) {
                volume.setTimeUnit(ChronoUnit.MINUTES);
                switch (intensity) {
                    case 1 -> {
                        volume.setTimeValueMin(1);
                        volume.setTimeValueMax(3);
                    }
                    case 2 -> {
                        volume.setTimeValueMin(4);
                        volume.setTimeValueMax(8);
                    }
                    case 3 -> {
                        volume.setTimeValueMin(10);
                        volume.setTimeValueMax(20);
                    }
                }
                return volume;
            } else {
                volume.setTimeUnit(ChronoUnit.SECONDS);
                switch (intensity) {
                    case 1 -> {
                        volume.setTimeValueMin(10);
                        volume.setTimeValueMax(20);
                    }
                    case 2 -> {
                        volume.setTimeValueMin(40);
                        volume.setTimeValueMax(80);
                    }
                    case 3 -> {
                        volume.setTimeValueMin(80);
                        volume.setTimeValueMax(120);
                    }
                }
            }
            volume.setSetRangeMin(2);
            volume.setSetRangeMax(3);

            return volume;
        } else if (isEndurance) {
            volume.setTimeUnit(ChronoUnit.MINUTES);
            switch (intensity) {
                case 1 -> {
                    volume.setTimeValueMin(30);
                    volume.setTimeValueMax(45);
                }
                case 2 -> {
                    volume.setTimeValueMin(60);
                    volume.setTimeValueMax(90);
                }
                case 3 -> {
                    volume.setTimeValueMin(90);
                    volume.setTimeValueMax(135);
                }
            }
            volume.setSetRangeMin(1);
            volume.setSetRangeMax(1);

            return volume;
        }
        return volume;
    }
}
