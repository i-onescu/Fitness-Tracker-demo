package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.fittrack.fitnesstrackerdemo.enums.Intensity;
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
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    //  target muscle groups for this workout (chosen by user at workout generation)
    @ManyToOne
    @JoinColumn(name = "muscle_group_id",
            referencedColumnName = "id")
    private MuscleGroup targetMuscleGroup;

    //  represents name of the workout type (chosen by user at workout generation)
    @ManyToOne
    @JoinColumn(name = "training_category_id",
            referencedColumnName = "id")
    private TrainingCategory trainingCategory;

    //  workout duration in minutes (chosen by user at workout generation)
    @NotNull
    @Range(min = 30, max = 90)
    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    //  workout intensity level (chosen by user at workout generation)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "workout_intensity")
    private Intensity intensity;

    //  workout creation date and time
    @NotNull
    @Column(name = "created_date_time", columnDefinition = "DATE")
    private LocalDateTime createdDateTime;

}
