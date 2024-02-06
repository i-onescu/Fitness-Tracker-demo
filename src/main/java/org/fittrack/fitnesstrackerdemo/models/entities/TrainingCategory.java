package org.fittrack.fitnesstrackerdemo.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "training_categories")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name")
public class TrainingCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  name identifier for the training category, ex: bodybuilding, running
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

    /*
        boolean values that determine type of training,
        the facets of fitness that is focuses on in particular,
        they come in useful when creating workout
    */
    @NotNull
    @Column(name = "is_cardio_training")
    private boolean cardioTraining;

    @NotNull
    @Column(name = "is_weight_training")
    private boolean weightTraining;

    @NotNull
    @Column(name = "is_body_weight_training")
    private boolean enduranceTraining;

    //  list of exercises for said training category
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_exercises",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    Set<Exercise> exercises;

}
