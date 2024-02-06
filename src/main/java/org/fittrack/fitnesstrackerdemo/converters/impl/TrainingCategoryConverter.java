package org.fittrack.fitnesstrackerdemo.converters.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadTrainingCategoryDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.createdtos.WriteTrainingCategoryDto;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.springframework.stereotype.Component;

@Component
public class TrainingCategoryConverter {

    public TrainingCategory convertSecondToFirst(WriteTrainingCategoryDto writeMuscleGroupDto) {
        ObjectMapper mapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String s = mapper.writeValueAsString(writeMuscleGroupDto);
            return mapper.readValue(s, TrainingCategory.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    //    @Override
    public ReadTrainingCategoryDto convertFirstToSecond(TrainingCategory trainingCategory) {
        ObjectMapper mapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String s = mapper.writeValueAsString(trainingCategory);
            return mapper.readValue(s, ReadTrainingCategoryDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
