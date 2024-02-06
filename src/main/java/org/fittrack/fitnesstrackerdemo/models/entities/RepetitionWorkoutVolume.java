package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "1")
public class RepetitionWorkoutVolume extends WorkoutVolume {

    //  ideal weight for workout expressed as percentage of one rep max
    //  determined by intensity level
    @Range(min = 1, max = 100)
    @Column(name = "weight")
    private Integer weight;

    //  minimum value in rep range
    //  determined by intensity level
    @Range(max = 10)
    @Column(name = "rep_range_min")
    private Integer repRangeMin;

    //  maximum value in rep range
    //  determined by intensity level
    @Range(max = 100)
    @Column(name = "rep_range_max")
    private Integer repRangeMax;

}
