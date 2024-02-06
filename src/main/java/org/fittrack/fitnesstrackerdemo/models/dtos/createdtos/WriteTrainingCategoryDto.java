package org.fittrack.fitnesstrackerdemo.models.dtos.createdtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

@Builder
public record WriteTrainingCategoryDto(@Nullable @Pattern(regexp = "[a-zA-Z]+") String name,
                                       @Nullable @Range(min = 1, max = 5) Integer exhaustionFactor,
                                       @Nullable Boolean cardioTraining,
                                       @Nullable Boolean weightTraining,
                                       @Nullable Boolean enduranceTraining) { }
