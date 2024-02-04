package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.converters.impl.UserConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.UserDto;
import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.fittrack.fitnesstrackerdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public UserDto getUserById(Long id) {
        return userConverter.convertFirstToSecond(userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    public void save(@Valid UserDto userDto) {
        User user = userConverter.convertSecondToFirst(userDto);

        userRepository.save(user);
    }



}
