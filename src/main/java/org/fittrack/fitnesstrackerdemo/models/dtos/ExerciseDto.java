package org.fittrack.fitnesstrackerdemo.models.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;


@Builder
public record ExerciseDto(@NotNull @Pattern(regexp = "[a-zA-Z]+") String name,
                          @NotNull @Pattern(regexp = "[a-zA-Z]+") String muscleGroups,
                          @NotNull @Pattern(regexp = "[a-zA-Z]+") String trainingCategories,
                          @NotNull  boolean repetitionBased) { }






/*@NotNull Boolean beginner,
@NotNull Boolean intermediate,
@NotNull Boolean expert*/
