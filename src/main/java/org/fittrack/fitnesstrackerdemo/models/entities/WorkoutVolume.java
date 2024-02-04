package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "workout_volume")
public class WorkoutVolume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    // set of exercises picked by workout generator
    // to be done in this particular workout
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

    //  minimum value in rep range
    //  determined by intensity level
    @Column(name = "rep_range_min")
    private Integer repRangeMin;

    //  maximum value in rep range
    //  determined by intensity level
    @Column(name = "rep_range_max")
    private Integer repRangeMax;




}
