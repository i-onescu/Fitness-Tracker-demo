package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Data
@Entity
@Table(name = "training_categories")
public class TrainingCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name identifier for the training category, ex: bodybuilding, running
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "category_name")
    private String name;

    /*
     integer that will help calculate total exhaustion points of a workout
     determining the level of the workout (from easy to hard)
    */
    @NotNull
    @Range(min = 1, max = 5)
    @Column(name = "exhaustion_factor")
    private Integer exhaustionFactor;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<Exercise> exercises;

    /*
    boolean values that determine type of training,
    the facets of fitness that is focuses on in particular,
    they come in useful when creating workout
    */

    @NotNull
    @Column(name = "is_cardio_training")
    private Boolean isCardioTraining;

    @NotNull
    @Column(name = "is_weight_training")
    private Boolean isWeightTraining;

    @NotNull
    @Column(name = "is_body_weight_training")
    private Boolean containsBodyWeightTraining;

    @NotNull
    @Column(name = "is_workout_done_in_sets")
    private Boolean isWorkoutDoneInSets;

    @NotNull
    @Column(name = "is_single_set_measured_in_reps")
    private Boolean isSingleSetMeasuredInReps;

    @NotNull
    @Column(name = "is_single_set_measured_in_time")
    private Boolean isSingleSetMeasuredInTime;

    @NotNull
    @Column(name = "achievable_with_minimal_equipment")
    private Boolean achievableWithMinimalEquipment;

}
