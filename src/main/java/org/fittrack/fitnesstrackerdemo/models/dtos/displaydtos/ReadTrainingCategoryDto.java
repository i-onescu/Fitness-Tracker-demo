package org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Builder
public record ReadTrainingCategoryDto(@Nullable @Pattern(regexp = "[a-zA-Z]+") String name,
                                      @Nullable @Range(min = 1, max = 5) Integer exhaustionFactor,
                                      @Nullable Set<@Valid ReadExerciseDto> exercises) { }
