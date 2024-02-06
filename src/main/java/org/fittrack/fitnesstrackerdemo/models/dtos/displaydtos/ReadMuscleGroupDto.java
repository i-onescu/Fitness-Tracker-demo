package org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.fittrack.fitnesstrackerdemo.converters.deserializers.ReadExerciseDeserializer;
import org.fittrack.fitnesstrackerdemo.converters.deserializers.ReadMuscleGroupDeserializer;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@JsonDeserialize(using = ReadMuscleGroupDeserializer.class)
@Builder
public record ReadMuscleGroupDto(@Nullable @Pattern(regexp = "[a-zA-Z]+") String name,
                                 @Nullable @Range(min = 1, max = 25) Integer size,
//                                 @Nullable Set<@Valid ReadExerciseDto> exercises,
                                 @Nullable Set<@Pattern(regexp = "[a-zA-Z]+") String> exercises
) { }
