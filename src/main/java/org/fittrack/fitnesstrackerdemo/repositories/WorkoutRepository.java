package org.fittrack.fitnesstrackerdemo.repositories;

import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.fittrack.fitnesstrackerdemo.models.entities.Workout;
import org.fittrack.fitnesstrackerdemo.models.entities.WorkoutVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Optional<Workout> findWorkoutById(Long id);

    @Query("SELECT w FROM Workout w WHERE w.user = :userId")
    Optional<Workout> findWorkoutsByUser(Long userId);

    @Query("SELECT wv FROM WorkoutVolume wv WHERE wv.workout = :id")
    Set<WorkoutVolume> findWorkoutVolumesByWorkoutId(Long id);

    @Query("SELECT w FROM Workout w WHERE w.user = :userId and w.createdDateTime = :dateTime")
    Optional<Workout> findWorkoutByUserAndDateTime(Long userId, LocalDateTime dateTime);

}
