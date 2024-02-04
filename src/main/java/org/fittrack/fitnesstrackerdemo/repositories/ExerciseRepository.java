package org.fittrack.fitnesstrackerdemo.repositories;

import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findExerciseByName(String name);

    Optional<Exercise> findExerciseById(Long id);

    @Query("select e from Exercise e join e.trainingCategories etc where etc.id = ?1")
    List<Exercise> findExercisesByTrainingCategory(Long trainingCategoryId);

    @Query("select e from Exercise e join e.muscleGroups emg where emg.id = ?1")
    Set<Exercise> findExercisesByMuscleGroup(MuscleGroup muscleGroup);

}