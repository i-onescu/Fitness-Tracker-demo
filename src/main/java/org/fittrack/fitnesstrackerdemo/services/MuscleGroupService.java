package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.converters.impl.MuscleGroupConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.MuscleGroupNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadMuscleGroupDto;
import org.fittrack.fitnesstrackerdemo.models.dtos.createdtos.WriteMuscleGroupDto;
import org.fittrack.fitnesstrackerdemo.models.entities.MuscleGroup;
import org.fittrack.fitnesstrackerdemo.repositories.MuscleGroupRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;
    private final MuscleGroupConverter muscleGroupConverter;

    public ReadMuscleGroupDto getMuscleGroupById(Long id) {
        return muscleGroupConverter.convertFirstToSecond(
                muscleGroupRepository.findMuscleGroupById(id)
                        .orElseThrow(MuscleGroupNotFoundException::new));
    }

    public ReadMuscleGroupDto getMuscleGroupByName(String name) {
        return muscleGroupConverter.convertFirstToSecond(
                muscleGroupRepository.findMuscleGroupByName(name)
                        .orElseThrow(MuscleGroupNotFoundException::new));
    }

    public void save(@Valid WriteMuscleGroupDto writeMuscleGroupDto) {
        MuscleGroup muscleGroup = muscleGroupConverter.convertSecondToFirst(writeMuscleGroupDto);
        muscleGroupRepository.save(muscleGroup);
    }

}

