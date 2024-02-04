package org.fittrack.fitnesstrackerdemo.models.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Builder
public record MuscleGroupDto(@Nullable @Pattern(regexp = "[a-zA-Z]+") String name,
                             @Nullable @Range(min = 1, max = 25) Integer size,
                             @Nullable Set<@Pattern(regexp = "[a-zA-Z]+") String> exerciseNames) { }
