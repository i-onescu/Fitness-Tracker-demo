package org.fittrack.fitnesstrackerdemo.models.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;

@Builder
public record UserDto(@Nullable @Pattern(regexp = "[a-zA-Z]+") String firstName,
                      @Nullable @Pattern(regexp = "[a-zA-Z]+") String lastName,
                      @Nullable @Pattern(regexp = ".+@.+\\..+") String email,
                      @Nullable @Range(min = 18, max = 75) Integer age){
}
