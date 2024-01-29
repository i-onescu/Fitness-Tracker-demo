package org.fittrack.fitnesstrackerdemo.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

@Builder
public record WorkoutDto(@NotNull @Range(min = 1, max = 5) Integer intensityLevel,
                         @NotNull @Pattern(regexp = "[a-zA-Z]+") String trainingCategory,
                         @NotNull @Pattern(regexp = "[a-zA-Z]+") String targetMuscleGroups,
                         @NotNull @Range(min = 30, max = 90) Integer durationInMinutes) { }
