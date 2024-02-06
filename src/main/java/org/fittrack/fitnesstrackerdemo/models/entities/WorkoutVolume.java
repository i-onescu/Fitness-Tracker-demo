package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "workout_volumes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "volume_type",
        discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("")
public class WorkoutVolume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  workout to whom this is associated
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    //  set of exercises picked by workout generator to be done in this particular workout
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    //  number of sets per exercise
    //  determined by trainingCategory
    @Column(name = "set_range_min")
    private Integer setRangeMin;

    //  number of sets per exercise
    //  determined by trainingCategory
    @Column(name = "set_range_max")
    private Integer setRangeMax;

}
