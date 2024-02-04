package org.fittrack.fitnesstrackerdemo.converters.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.MuscleGroupDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MuscleGroupConverter implements ObjectConverter<MuscleGroup, MuscleGroupDto> {
    @Override
    public MuscleGroup convertSecondToFirst(MuscleGroupDto muscleGroupDto){
        MuscleGroup muscleGroup = new MuscleGroup();

        muscleGroup.setName(muscleGroupDto.name());
        muscleGroup.setSize(muscleGroupDto.size());

        return muscleGroup;
    }

    @Override
    public MuscleGroupDto convertFirstToSecond(MuscleGroup muscleGroup) {
//        ObjectMapper objMapper = new ObjectMapper();
//
//        ObjectNode node = objMapper.valueToTree(muscleGroup);
//
//        node.remove("exercises");
//        ArrayNode array = objMapper.valueToTree(muscleGroup.getExercises().stream()
//                .map(Exercise::getName)
//                .collect(Collectors.toSet()));
//
//        node.set("exerciseNames", array);
//        try {
//            String finalJson = objMapper.writeValueAsString(node);
//            return objMapper.readValue(finalJson, MuscleGroupDto.class);
//        } catch (JsonProcessingException e) {
//            throw new MuscleGroupNotFoundException();
//        }

        return MuscleGroupDto.builder()
                .name(muscleGroup.getName())
                .size(muscleGroup.getSize())
                .exerciseNames(muscleGroup.getExercises().stream()
                        .map(Exercise::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
