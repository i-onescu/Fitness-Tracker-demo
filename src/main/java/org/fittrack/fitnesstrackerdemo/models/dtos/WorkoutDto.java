package org.fittrack.fitnesstrackerdemo.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

@Builder
public record WorkoutDto(@NotNull @Pattern(regexp = "[a-zA-Z]+") String intensity,
                         @NotNull @Pattern(regexp = "[0-9]+") Long trainingCategoryId,
                         @NotNull @Pattern(regexp = "[0-9]+") Long muscleGroupId,
                         @NotNull @Range(min = 30, max = 90) Integer durationInMinutes) { }
