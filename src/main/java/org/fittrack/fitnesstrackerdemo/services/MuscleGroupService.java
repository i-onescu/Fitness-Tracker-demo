package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.ExerciseDto;
import org.fittrack.fitnesstrackerdemo.models.entities.Exercise;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;


    public MuscleGroup getMuscleGroupById(Long id) {
        return muscleGroupRepository.findMuscleGroupById(id)
                .orElseThrow(MuscleGroupNotFoundException::new);
    }

    public MuscleGroup getMuscleGroupByName(String name) {
        return muscleGroupRepository.findMuscleGroupByName(name)
                .orElseThrow(MuscleGroupNotFoundException::new);
    }

    public void save(@Valid MuscleGroup muscleGroup) {
        muscleGroupRepository.save(muscleGroup);
    }

}

