package org.fittrack.fitnesstrackerdemo.repositories;

import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {

    Optional<MuscleGroup> findMuscleGroupById(Long id);

    @Query("SELECT mg FROM MuscleGroup mg WHERE mg.name ILIKE :nameStr")
    Optional<MuscleGroup> findMuscleGroupByName(String nameStr);

//    @Query(value = "INSERT INTO muscle_group_exercises (muscle_group_id, exercise_id) VALUES (:id, :exerciseId)", nativeQuery = true)
//    void setExerciseForMuscleGroupById(Long id, Long exerciseId);

}
