package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.annotation.Generated;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "exercise_name")
    private String name;

    /*
     list of the muscle groups the exercise may target,
     ex: push-ups muscleGroups = {chest, triceps}

     note:
     in order for an exercise to target a muscle group it must
     effectively apply stress the muscle group in an effective and efficient way
     (this depends of course on the style of training, and is kept in mind by admin entering exercise)
    */
    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
    private Set<MuscleGroup> muscleGroups;

    /*
     the category of training the exercise is part of,
     ex: push-up - calisthenics,
         dead-lift - powerlifting, bodybuilding
     will probably make this a list tho
    */
    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
    private Set<TrainingCategory> trainingCategories;

    /*
    exhaustion points should be extrapolated from a formula
    of muscleGroup.size() and trainingCategory.exhaustionFactor()
    will see later
    */
    @NotNull
    @Column(name = "exhaustion_points")
    private Integer exhaustionPoints;

    /*
    boolean values showing level of experience needed for each exercise
    will be useful when creating custom workout
    */

    @Column(name = "is_beginner_exercise")
    private Boolean isBeginner;

    @Column(name = "is_intermediate_exercise")
    private Boolean isIntermediate;

    @Column(name = "is_expert_exercise")
    private Boolean isExpert;

}
