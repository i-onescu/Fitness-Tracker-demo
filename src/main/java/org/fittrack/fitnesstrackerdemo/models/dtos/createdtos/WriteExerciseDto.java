package org.fittrack.fitnesstrackerdemo.models.dtos.createdtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.fittrack.fitnesstrackerdemo.converters.deserializers.ReadExerciseDeserializer;

import java.util.Set;

@Builder
public record WriteExerciseDto(@NotNull @Pattern(regexp = "[a-zA-Z]+") String name,
                               @NotNull Set<@Pattern(regexp = "[a-zA-Z]+") String> muscleGroups,
                               @NotNull Set<@Pattern(regexp = "[a-zA-Z]+") String> trainingCategories,
                               @NotNull  Boolean repetitionBased) { }






/*@NotNull Boolean beginner,
@NotNull Boolean intermediate,
@NotNull Boolean expert*/
