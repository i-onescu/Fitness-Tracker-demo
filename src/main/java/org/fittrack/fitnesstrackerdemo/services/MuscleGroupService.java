package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
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

