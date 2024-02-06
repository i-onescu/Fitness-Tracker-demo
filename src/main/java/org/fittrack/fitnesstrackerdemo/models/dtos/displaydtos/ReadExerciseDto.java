package org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.fittrack.fitnesstrackerdemo.converters.deserializers.ReadExerciseDeserializer;

import java.util.Set;

@JsonDeserialize(using = ReadExerciseDeserializer.class)
@Builder
public record ReadExerciseDto(@Nullable @Pattern(regexp = "[a-zA-Z]+") String name,
                              @Nullable Set<@Pattern(regexp = "[a-zA-Z]+") String> muscleGroups,
                              @Nullable Set<@Pattern(regexp = "[a-zA-Z]+") String> trainingCategories) { }
