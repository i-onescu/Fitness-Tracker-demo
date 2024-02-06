package org.fittrack.fitnesstrackerdemo.models.factories;

import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.models.entities.WorkoutVolume;

public interface WorkoutVolumeFactory {

    WorkoutVolume createWorkoutVolume(Workout workout, Exercise exercise);

}
