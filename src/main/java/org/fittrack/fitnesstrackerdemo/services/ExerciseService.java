package org.fittrack.fitnesstrackerdemo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.impl.ExerciseConverter;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.createdtos.WriteExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseConverter exerciseConverter;


    public Set<ReadExerciseDto> getAllExercises() {
        Set<ReadExerciseDto> set = new HashSet<>();

        Set<ReadExerciseDto> exercises = exerciseRepository.findAll().stream()
                .map(exercise -> {
                    try {
                        return exerciseConverter.convertFirstToSecond(exercise);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
        return exercises;
    }

    public void save(@Valid WriteExerciseDto writeExerciseDto) {
        Exercise exercise = exerciseConverter.convertSecondToFirst(writeExerciseDto);
        exerciseRepository.save(exercise);

//        ObjectMapper mapper =
//                new ObjectMapper()
//                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        String s = null;
//        Exercise exercise = new Exercise();
//
//        try {
//            s = mapper.writeValueAsString(writeExerciseDto);
//            exercise = mapper.readValue(s, Exercise.class);
//            exerciseRepository.save(exercise);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

    }

    public ReadExerciseDto getExerciseByName(String name) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(exerciseRepository.findExerciseByName(name));

        return mapper.readValue(s, ReadExerciseDto.class);
    }

    public ReadExerciseDto getExerciseById(Long id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(exerciseRepository.findExerciseById(id).get());

        return mapper.readValue(s, ReadExerciseDto.class);
    }
}
