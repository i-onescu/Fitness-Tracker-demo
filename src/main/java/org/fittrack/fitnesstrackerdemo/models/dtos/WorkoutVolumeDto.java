package org.fittrack.fitnesstrackerdemo.models.dtos;

import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
public record WorkoutVolumeDto (@Nullable String exercise,
                                @Nullable String setRange,
                                @Nullable String repRange) { }
