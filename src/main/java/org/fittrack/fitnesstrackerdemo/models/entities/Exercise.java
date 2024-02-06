package org.fittrack.fitnesstrackerdemo.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "exercises")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "name")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  name identifier for exercise
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "exercise_name", unique = true)
    private String name;

    // list of the muscle groups the exercise may target,
    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
    private Set<MuscleGroup> muscleGroups;

    // the category of training the exercise is part of
    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
    private Set<TrainingCategory> trainingCategories;

    @NotNull
    @Column(name = "repetition_based")
    private boolean repetitionBased;

    public Exercise() { }

    public Exercise(String name,
                    Set<MuscleGroup> muscleGroups,
                    Set<TrainingCategory> trainingCategories,
                    Boolean repetitionBased) {
        this.name = name;
        this.repetitionBased = repetitionBased;
        this.muscleGroups = muscleGroups;
        this.trainingCategories = trainingCategories;
    }
}
