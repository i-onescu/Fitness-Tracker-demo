package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.temporal.ChronoUnit;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "2")
public class TimeWorkoutVolume extends WorkoutVolume {

    //  indicates number of time units the exercise must be done for in one set
    @Range(max = 100)
    @Column(name = "time_value_min")
    private Integer timeValueMin;

    //  indicates number of time units the exercise must be done for in one set
    @Range(max = 300)
    @Column(name = "time_value_max")
    private Integer timeValueMax;

    //  indicates time unit
    @Column(name = "time_Unit")
    @Enumerated(value = EnumType.STRING)
    private ChronoUnit timeUnit;

}

