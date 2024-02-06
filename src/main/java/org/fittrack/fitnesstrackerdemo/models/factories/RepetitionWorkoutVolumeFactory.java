package org.fittrack.fitnesstrackerdemo.models.factories;


import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.RepetitionWorkoutVolume;
import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.models.entities.WorkoutVolume;

public class RepetitionWorkoutVolumeFactory implements WorkoutVolumeFactory {

    @Override
    public WorkoutVolume createWorkoutVolume(Workout workout, Exercise exercise) {
        RepetitionWorkoutVolume volume = new RepetitionWorkoutVolume();
        volume.setWorkout(workout);

        boolean isCardio = workout.getTrainingCategory().isCardioTraining();
        boolean isEndurance = workout.getTrainingCategory().isEnduranceTraining();
        boolean isWeight = workout.getTrainingCategory().isWeightTraining();
        int intensity = workout.getIntensity().getValue();

        volume.setExercise(exercise);
        if (isWeight) {
            if (isEndurance) {
                switch (intensity) {
                    case 1 -> {
                        volume.setRepRangeMin(20);
                        volume.setRepRangeMax(40);
                        volume.setWeight(60);
                        volume.setSetRangeMin(4);
                        volume.setSetRangeMax(6);
                    }
                    case 2 -> {
                        volume.setRepRangeMin(40);
                        volume.setRepRangeMax(60);
                        volume.setWeight(40);
                        volume.setSetRangeMin(3);
                        volume.setSetRangeMax(5);
                    }
                    case 3 -> {
                        volume.setRepRangeMin(60);
                        volume.setRepRangeMax(100);
                        volume.setWeight(25);
                        volume.setSetRangeMin(2);
                        volume.setSetRangeMax(4);
                    }
                }
                return volume;
            } else {
                switch (intensity) {
                    case 1 -> {
                        volume.setRepRangeMin(10);
                        volume.setRepRangeMax(15);
                        volume.setWeight(50);
                        volume.setSetRangeMin(3);
                        volume.setSetRangeMax(4);
                    }
                    case 2 -> {
                        volume.setRepRangeMin(8);
                        volume.setRepRangeMax(12);
                        volume.setWeight(70);
                        volume.setSetRangeMin(2);
                        volume.setSetRangeMax(3);
                    }
                    case 3 -> {
                        volume.setRepRangeMin(6);
                        volume.setRepRangeMax(10);
                        volume.setWeight(85);
                        volume.setSetRangeMin(1);
                        volume.setSetRangeMax(2);
                    }
                }
                return volume;
            }
        }
        return volume;
    }
}
