package org.fittrack.fitnesstrackerdemo.converters.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadMuscleGroupDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.createdtos.WriteMuscleGroupDto;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.springframework.stereotype.Component;

@Component
public class MuscleGroupConverter {

    public MuscleGroup convertSecondToFirst(WriteMuscleGroupDto writeMuscleGroupDto) {
        ObjectMapper mapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String s = mapper.writeValueAsString(writeMuscleGroupDto);
            return mapper.readValue(s, MuscleGroup.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ReadMuscleGroupDto convertFirstToSecond(MuscleGroup muscleGroup) {
        ObjectMapper mapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String s = mapper.writeValueAsString(muscleGroup);
            return mapper.readValue(s, ReadMuscleGroupDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
