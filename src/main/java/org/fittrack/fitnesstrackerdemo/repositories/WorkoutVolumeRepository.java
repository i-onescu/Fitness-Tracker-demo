package org.fittrack.fitnesstrackerdemo.repositories;

import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.models.entities.WorkoutVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface WorkoutVolumeRepository extends JpaRepository<WorkoutVolume, Long> {

    @Query("SELECT wv FROM WorkoutVolume wv WHERE wv.workout = :workout")
    Set<WorkoutVolume> findWorkoutVolumeByWorkout(Workout workout);

}
