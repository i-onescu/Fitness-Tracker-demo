package org.fittrack.fitnesstrackerdemo.repositories;

import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingCategoryRepository extends JpaRepository<TrainingCategory, Long> {

    Optional<TrainingCategory> findTrainingCategoryById(Long id);

    @Query("SELECT tc FROM TrainingCategory tc WHERE tc.name ILIKE :nameStr")
    Optional<TrainingCategory> findTrainingCategoryByName(String nameStr);

}
