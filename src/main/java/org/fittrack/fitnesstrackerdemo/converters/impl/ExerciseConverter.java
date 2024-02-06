package org.fittrack.fitnesstrackerdemo.converters.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.exceptions.TrainingCategoryNotFoundException;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.createdtos.WriteExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.models.entities.TrainingCategory;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.fittrack.fitnesstrackerdemo.repositories.TrainingCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExerciseConverter
//        implements ObjectConverter<Exercise, WriteExerciseDto>
{

    private final MuscleGroupRepository muscleGroupRepository;
    private final TrainingCategoryRepository trainingCategoryRepository;


    //    @Override
    public Exercise convertSecondToFirst(WriteExerciseDto writeExerciseDto) {
        Exercise exercise = new Exercise();

        exercise.setName(writeExerciseDto.name());
        exercise.setRepetitionBased(writeExerciseDto.repetitionBased());
        exercise.setMuscleGroups(
                writeExerciseDto.muscleGroups().stream()
                        .map(s -> {
                            MuscleGroup mg = muscleGroupRepository.findMuscleGroupByName(s)
                                    .orElseThrow(MuscleGroupNotFoundException::new);
                            mg.getExercises().add(exercise);
                            return mg;
                        })
                        .collect(Collectors.toSet())
        );
        exercise.setTrainingCategories(
                writeExerciseDto.trainingCategories().stream()
                        .map(string -> {
                            TrainingCategory tc = trainingCategoryRepository.findTrainingCategoryByName(string)
                                    .orElseThrow(TrainingCategoryNotFoundException::new);
                            tc.getExercises().add(exercise);
                            return tc;
                        })
                        .collect(Collectors.toSet())
        );

        return exercise;
    }

    //    @Override
    public ReadExerciseDto convertFirstToSecond(Exercise exercise) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(exercise);

        return mapper.readValue(s, ReadExerciseDto.class);
    }
}
