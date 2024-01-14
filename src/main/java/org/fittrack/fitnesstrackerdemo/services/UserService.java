package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.UserDto;
import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.fittrack.fitnesstrackerdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ObjectConverter<User, UserDto> userConverter;

    public UserService(UserRepository userRepository,
                       ObjectConverter<User, UserDto> userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }


    public UserDto getUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);

        return userConverter.convertFirstToSecond(user);
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    public void saveUser(@Valid UserDto userDto) {
        User user = userConverter.convertSecondToFirst(userDto);

        userRepository.save(user);
    }



}
