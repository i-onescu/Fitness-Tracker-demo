package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  user that generated the workout
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // target muscle groups for this workout picked by user at workout generation
    // String of muscleGroup names separated by spaces
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "target_muscle_groups")
    private String targetMuscleGroups;

    //  represents name of the workout type , bodybuilding, cardio, yoga, etc.
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "training_category")
    private String trainingCategory;

    //  workout duration in minutes
    @NotNull
    @Range(min = 30, max = 90)
    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    // workout intensity level
    // picked by user at workout generation
    @NotNull
    @Range(min = 1, max = 5)
    @Column(name = "workout_intensity")
    private Integer intensityLevel;

    //  workout creation date and time
    @NotNull
    @Column(name = "created_date_time", columnDefinition = "DATE")
    private LocalDateTime createdDateTime;

}
